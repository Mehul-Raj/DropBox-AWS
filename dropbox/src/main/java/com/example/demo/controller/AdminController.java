package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.AdmsApplication;
import com.example.demo.config.AppConfig;
import com.example.demo.model.Company;
import com.example.demo.model.Department;
import com.example.demo.model.Project;
import com.example.demo.model.Team;
import com.example.demo.model.User;
import com.example.demo.service.AwsS3Create;
import com.example.demo.service.DBService;
import com.example.demo.service.FlieSystemCreate;
import com.example.demo.service.OtpService;
import com.example.demo.service.SendEmailService;

@RestController
public class AdminController {
	private static Logger logger = LogManager.getLogger(AdmsApplication.class);

	@Autowired
	DBService service;

	@Autowired
	HttpServletRequest req;

	@Autowired
	OtpService otpService;

	@Autowired
	SendEmailService sendEmailService;

	@Autowired
	AwsS3Create awsCreate;

	@Autowired
	FlieSystemCreate flieSystemCreate;

	@Autowired
	AppConfig appConfig;

	// Create Company
	@PostMapping("createCompany")
	public ModelAndView getData(Company company, ModelAndView model) {
		boolean status = false;
		int result = service.createCompany(company);
		switch (result) {
		case 1:
			logger.info("Company Created In DataBase");
			if (appConfig.getStorageName().equalsIgnoreCase("fileSystem")) {
				status = flieSystemCreate.createCompany(company);
			} else if (appConfig.getStorageName().equalsIgnoreCase("AwsCloud")) {
				status = awsCreate.createCompany(company);
			}
			// get Updated Company Name
			service.findAllCompany();
			model.setViewName("admin");
			return model;
		case 2:
			model.addObject("err", "Not Created");
			model.setViewName("admin");
			return model;
		}
		return model;
	}

	// Create Department
	@PostMapping("createDept")
	public ModelAndView createDepartment(Department department, ModelAndView model) {
		boolean status = false;
		int result = service.createDepartment(department);
		switch (result) {
		case 1:
			if (appConfig.getStorageName().equalsIgnoreCase("fileSystem")) {
				status = flieSystemCreate.createDepartment(department);
			} else if (appConfig.getStorageName().equalsIgnoreCase("AwsCloud")) {
				status = awsCreate.createDepartment(department);
			}
			// Get Updated Dept List
			service.findAllDepartment();
			model.setViewName("admin");
			return model;
		case 2:
			model.addObject("err", "Not Created");
			model.setViewName("admin");
			return model;
		}
		return model;
	}

	// Create Project
	@PostMapping("createProject")
	public ModelAndView createProject(Project project, ModelAndView model) {
		boolean status = false;
		int result = service.createProject(project);
		switch (result) {
		case 1:
			if (appConfig.getStorageName().equalsIgnoreCase("fileSystem")) {
				status = flieSystemCreate.createProject(project);
			} else if (appConfig.getStorageName().equalsIgnoreCase("AwsCloud")) {
				status = awsCreate.createProject(project);
			}
			if (status) {
				model.addObject("ok", "Created");
			} else {
				model.addObject("err", "Not Created");
			}
			service.findAllProject();
			model.setViewName("admin");
			return model;
		case 2:
			model.addObject("err", "Not Created");
			model.setViewName("admin");
			return model;
		}
		return model;
	}

	// Create Team
	@PostMapping("createTeam")
	public ModelAndView createTeam(Team team, ModelAndView model) {

		boolean status = false;
		int result = service.createTeam(team);
		switch (result) {
		case 1:
			if (appConfig.getStorageName().equalsIgnoreCase("fileSystem")) {
				status = flieSystemCreate.createTeam(team);
			} else if (appConfig.getStorageName().equalsIgnoreCase("AwsCloud")) {
				status = awsCreate.createTeam(team);
			}
			if (status) {
				model.addObject("ok", "Created");
			} else {
				model.addObject("err", "Folder Not Created");
			}
			service.findAllTeam();
			model.setViewName("admin");
			return model;
		case 2:
			model.addObject("err", "DB Not Created");
			model.setViewName("admin");
			return model;
		}
		return model;

	}

	// Create User
	@PostMapping("createUser")
	public ModelAndView createUser(User user, ModelAndView model) {
		String pwd = otpService.CreatePwd();
		user.setUserPwd(pwd);
		int result = service.createUser(user);

		switch (result) {
		case 1:
			boolean otpstatus = sendEmailService.sendEmail(user);
			if (otpstatus) {
				model.addObject("okotp", "Email Sent Created");
			} else {
				model.addObject("errotp", "Email Not Sent");
			}
			model.setViewName("admin");
		case 2:
			model.addObject("err", "User Not Created");
			model.setViewName("admin");
		}
		return model;
	}

}
