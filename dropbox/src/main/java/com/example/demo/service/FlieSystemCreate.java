package com.example.demo.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.AppConfig;
import com.example.demo.model.Company;
import com.example.demo.model.Department;
import com.example.demo.model.Project;
import com.example.demo.model.Team;

@Service
public class FlieSystemCreate {

	@Autowired
	AppConfig appConfig;

	// Create Company Inside File System
	public boolean createCompany(Company company) {
		boolean status = false;
		String path = appConfig.getFilePath() + company.getCompanyName();
		File file = new File(path);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
				status = true;
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		return status;
	}

	// Create Department Inside File System
	public boolean createDepartment(Department department) {
		boolean status = false;
		String path = appConfig.getFilePath() + department.getCompanyName() + "/" + department.getDepartmentName();
		File file = new File(path);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
				status = true;
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		return status;
	}

	// Create Project In File System
	public boolean createProject(Project project) {
		boolean status = false;
		String path = appConfig.getFilePath() + project.getCompanyName() + "/" + project.getDepartmentName() + "/"
				+ project.getProjectName();
		File file = new File(path);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
				status = true;
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		return status;
	}

	public boolean createTeam(Team team) {
		boolean status = false;
		String path = appConfig.getFilePath() + team.getCompanyName() + "/" + team.getDeptartmentName() + "/"
				+ team.getProjectName()+"/"+team.getTeamName();
		File file = new File(path);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
				status = true;
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		return status;
	}
}
