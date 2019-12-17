package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.AdmsApplication;
import com.example.demo.model.User;
import com.example.demo.repository.UserDAO;
import com.example.demo.service.DBService;
import com.example.demo.service.OtpService;

@RestController
public class HomeController {

	private static Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	DBService service;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	OtpService otpService;

	@Autowired
	HttpServletRequest req;

	@Autowired
	UserDAO dao;

	@RequestMapping("/")
	public ModelAndView home(ModelAndView model) {
		model.setViewName("index");
		return model;
	}

	@RequestMapping("login")
	public ModelAndView getLogin(ModelAndView model) {
		model.setViewName("login");
		return model;
	}

	@PostMapping("myData")
	public ModelAndView getData(User user, ModelAndView model) {
		//int id = user.getUserId();
		
		String emailId=user.geteMail();
		String pass = user.getUserPwd();
		session.setAttribute("UserEmail", emailId);
		System.out.println(user.geteMail());
		int result = service.checkLogin(emailId, pass);
		switch (result) {
		case 1:
			service.findAllCompany();
			service.findAllDepartment();
			service.findAllProject();
			service.findAllTeam();
			model.setViewName("admin");
			return model;
		case 2:
			service.findUser(emailId);
			model.addObject("user", new User());
			model.setViewName("user");
			return model;
		case 3:
			model.addObject("err", "Email not found");
			model.setViewName("login");
			return model;

		case 4:
			model.addObject("err", "Password not matched");
			model.setViewName("login");
			return model;
		}
		req.getSession().setAttribute("message", pass);
		return model;
	}

	@GetMapping("destroy")
	public ModelAndView logout(ModelAndView model) {
		req.getSession().invalidate();
		model.setViewName("login");
		return model;
	}
	
	@PostMapping("/forgetPassword")
	public ModelAndView forgetPassword(@RequestParam("emailForOtp") String emailForOtp,ModelAndView model) {
		String pwd = otpService.CreatePwd();
		session.setAttribute("otpGenerated", pwd);
		session.setAttribute("emailForOtp", emailForOtp);
		model.setViewName("login");
		return model;
	}
	
	@PostMapping("validateOtp")
	public ModelAndView validiateOtp(@RequestParam("otp") String otp,@RequestParam("newPwd") String newPwd,ModelAndView model) {
		String emailForOtp=null;
		String otpGenerated=(String) session.getAttribute("userObject");
		emailForOtp=(String) session.getAttribute(emailForOtp);
		if(otpGenerated.equalsIgnoreCase(otp)) {
			List<User> userList=dao.findByeMail(emailForOtp);
			User user=userList.get(0);
			user.setUserPwd(newPwd);
			dao.save(user);
			model.setViewName("login");
		}
		return model;
		
	}

}
