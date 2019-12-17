package com.example.demo.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.otpGenerator.RandomString;

@Service
public class OtpService {

	@Autowired
	RandomString randString;
	@Autowired
	HttpSession session;

	public String CreatePwd() {
		String pwd = randString.getAlphaNumericString();
		System.out.println("1");
		session.setAttribute("otpGenerated", pwd);
		return pwd;

	}
}
