package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file_details")
public class FileDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileId;

	@Column(name = "fileUrl")
	private String fileUrl;

	@Column(name = "fileName")
	private String fileName;

	@Column(name = "fileUploadTime")
	private Date fileUploadTime;

	@Column(name = "fileSize")
	private double fileSize;

	@Column(name = "uploadFileTo")
	private String uploadFileTo;
	
	@Column(name="uploadBy")
	private String uploadBy;
	
	@Column(name="documentTag")
	private String tag;

	
	@Column(name="docCompany")
	private String docCompany;
	
	@Column(name="docDepartment")
	private String docDepartment;
	
	@Column(name="docProject")
	private String docProject;
	
	@Column(name="docTeam")
	private String docTeam;
	
	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getFileUploadTime() {
		return fileUploadTime;
	}

	public void setFileUploadTime(Date fileUploadTime) {
		this.fileUploadTime = fileUploadTime;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public String getUploadFileTo() {
		return uploadFileTo;
	}

	public void setUploadFileTo(String uploadFileTo) {
		this.uploadFileTo = uploadFileTo;
	}

	public String getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDocCompany() {
		return docCompany;
	}

	public void setDocCompany(String docCompany) {
		this.docCompany = docCompany;
	}

	public String getDocDepartment() {
		return docDepartment;
	}

	public void setDocDepartment(String docDepartment) {
		this.docDepartment = docDepartment;
	}

	public String getDocProject() {
		return docProject;
	}

	public void setDocProject(String docProject) {
		this.docProject = docProject;
	}

	public String getDocTeam() {
		return docTeam;
	}

	public void setDocTeam(String docTeam) {
		this.docTeam = docTeam;
	}
}