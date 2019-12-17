package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.demo.AdmsApplication;
import com.example.demo.config.BeanConfig;
import com.example.demo.model.Company;
import com.example.demo.model.Department;
import com.example.demo.model.Project;
import com.example.demo.model.Team;

@Service
public class AwsS3Create {
	
	private static Logger logger = LogManager.getLogger(AwsS3Create.class);

	static String sufix = "/";
	@Autowired
	BeanConfig config;
	
	
	// create company in bucket
	public boolean createCompany(Company company) {
		boolean status = false;
		String compName = company.getCompanyName();
		String bucketName = config.getBucketName();
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(0);
			InputStream inputStream = new ByteArrayInputStream(new byte[0]);
			config.awsS3Client().putObject(bucketName, compName + sufix, inputStream, metadata);
			status = true;
			logger.info("Company Created In Aws for "+compName);
		} catch (Exception ex) {
			logger.info(ex.getMessage());
			status = false;
		}
		return status;
	}

	// create department
	public boolean createDepartment(Department dept) {
		boolean status = false;
		String compName = dept.getCompanyName();
		String deptName = dept.getDepartmentName();
		String bucketName = config.getBucketName();
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(0);
			InputStream inputStream = new ByteArrayInputStream(new byte[0]);
			config.awsS3Client().putObject(bucketName, compName + sufix + deptName + sufix, inputStream, metadata);
			status = true;
		} catch (Exception ex) {
			status = false;
		}
		return status;
	}

	// create Project
	public boolean createProject(Project project) {
		boolean status = false;
		String compName = project.getCompanyName();
		String deptName = project.getDepartmentName();
		String projName = project.getProjectName();
		String bucketName = config.getBucketName();
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(0);
			InputStream inputStream = new ByteArrayInputStream(new byte[0]);
			config.awsS3Client().putObject(bucketName, compName + sufix + deptName + sufix + projName + sufix,
					inputStream, metadata);
			status = true;
		} catch (Exception ex) {
			status = false;
		}
		return status;
	}

	// Create Team
	public boolean createTeam(Team team) {
		boolean status = false;
		String compName = team.getCompanyName();
		String deptName = team.getDeptartmentName();
		String projName = team.getProjectName();
		String teamName = team.getTeamName();
		String bucketName = config.getBucketName();
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(0);
			InputStream inputStream = new ByteArrayInputStream(new byte[0]);
			config.awsS3Client().putObject(bucketName,
					compName + sufix + deptName + sufix + projName + sufix + teamName + sufix , inputStream, metadata);
			status = true;
		} catch (Exception ex) {
			status = false;
		}
		return status;
	}

}
