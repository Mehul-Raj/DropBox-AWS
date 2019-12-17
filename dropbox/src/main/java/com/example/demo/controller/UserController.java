package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.AppConfig;
import com.example.demo.service.AwsS3upload;
import com.example.demo.service.DBService;
import com.example.demo.service.FileSystemService;

@RestController
public class UserController {

	private static Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	AwsS3upload awsUpload;

	@Autowired
	DBService service;

	@Autowired
	AppConfig appConfig;

	@Autowired
	HttpServletRequest req;
	@Autowired
	FileSystemService fileUploadService;

	@PostMapping("uploadFile")
	public ModelAndView uploadFile(@RequestParam("uploadTo") String uploadTo,
			@RequestParam("multiTag") String documentTag, @RequestPart(value = "file") MultipartFile file,
			ModelAndView model) {
		boolean status = false;
		if (appConfig.getStorageName().equalsIgnoreCase("fileSystem")) {
			status = fileUploadService.uploadFile(file, uploadTo, documentTag);
		} else if (appConfig.getStorageName().equalsIgnoreCase("AwsCloud")) {
			status = awsUpload.uploadFile(file, uploadTo, documentTag);
		}
		if (status) {
			model.addObject("ok", "File Uploaded");

		} else {
			model.addObject("error", "File Not Uploaded");
		}
		model.setViewName("user");
		return model;
	}

	@GetMapping("getFileList")
	public ModelAndView getDocument(ModelAndView model) {
		service.getAllFile();
		model.setViewName("user");
		return model;

	}

	@PostMapping("searchByTag")
	public ModelAndView getDocumentByTag(@RequestParam("selectedTag") String tag, ModelAndView model) {
		System.out.println(tag);
		service.getAllFileByTag(tag);
		model.setViewName("user");
		return model;

	}

	@PostMapping("searchByType")
	public ModelAndView getDocumentByType(@RequestParam("tagDocType") String docType, ModelAndView model) {
		System.out.println(docType);
		service.getAllFileByType(docType);
		model.setViewName("user");
		return model;

	}
}
