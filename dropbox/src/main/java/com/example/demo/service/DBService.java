package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.AppConfig;
import com.example.demo.model.Company;
import com.example.demo.model.Department;
import com.example.demo.model.FileDetails;
import com.example.demo.model.Project;
import com.example.demo.model.Team;
import com.example.demo.model.User;
import com.example.demo.repository.CompanyDAO;
import com.example.demo.repository.DepartmentDAO;
import com.example.demo.repository.FileDetailsDAO;
import com.example.demo.repository.ProjectDAO;
import com.example.demo.repository.TeamDAO;
import com.example.demo.repository.UserDAO;

@Service
public class DBService {

	@Autowired
	UserDAO dao;

	@Autowired
	CompanyDAO cdao;

	@Autowired
	DepartmentDAO deptDao;

	@Autowired
	ProjectDAO projDao;

	@Autowired
	TeamDAO teamDao;

	@Autowired
	HttpSession session;

	@Autowired
	FileDetailsDAO filedetailsDao;

	@Autowired
	SendEmailService sendEmail;

	@Autowired
	HttpServletRequest req;

	@Autowired
	AppConfig appConfig;

	FileDetails details;

	User user;

	public int checkLogin(String email, String userPwd) {
		int result = 0;
		User user = dao.findById(email).orElse(new User());
		// int getEId = user.getUserId();
		String getEmail = user.geteMail();
		String getPass = user.getUserPwd();
		String role = user.getUserRole();
		session.setAttribute("user", user);

		// Check For The Default Admin
		if (email.equalsIgnoreCase(appConfig.getAdminEmail()) && userPwd.equals(appConfig.getAdminPassword())) {
			result = 1;
		} else if (getEmail.equalsIgnoreCase(email) && getPass.equals(userPwd)) {
			// Check The Role
			if (role.equalsIgnoreCase("Admin")) {
				result = 1;
			} else if (role.equalsIgnoreCase("U") || role.equalsIgnoreCase("M") || role.equalsIgnoreCase("T")) {
				result = 2;
			}
		} else {
			result = 3;
		}

		return result;
	}

	// create Company
	public int createCompany(Company company) {
		int result = 0;
		try {
			cdao.save(company);
			result = 1;
		} catch (Exception ex) {
			ex.fillInStackTrace();
			result = 2;
		}
		return result;
	}

	// create Department
	public int createDepartment(Department department) {
		int result = 0;

		try {
			deptDao.save(department);
			result = 1;

		} catch (Exception ex) {
			result = 2;
		}

		return result;
	}

	// Create Project
	public int createProject(Project project) {
		int result = 0;
		try {
			projDao.save(project);
			result = 1;
		} catch (Exception ex) {
			result = 2;
		}
		return result;
	}

	// Create Team
	public int createTeam(Team team) {
		int result = 0;
		try {
			teamDao.save(team);
			result = 1;
		} catch (Exception ex) {
			result = 2;
		}
		return result;
	}

	// Create User
	public int createUser(User user) {
		int result = 0;
		try {
			dao.save(user);
			result = 1;
		} catch (Exception ex) {
			result = 2;
		}
		return result;
	}

	// Save FileDetails
	public boolean saveFileDettails(FileDetails fileDetails) {
		boolean flag = false;
		try {
			filedetailsDao.save(fileDetails);
			flag = true;
		} catch (Exception ex) {
			System.out.println("Error in saveing");
		}
		return flag;
	}

	// get Company
	public String findAllCompany() {
		List<Company> compName = cdao.findAll();
		session.setAttribute("companyList", compName);
		return "ok";

	}

	// get Department
	public String findAllDepartment() {
		List<Department> deptName = deptDao.findAll();
		session.setAttribute("departmentList", deptName);
		return "OK";
	}

	// get Project
	public String findAllProject() {
		List<Project> projectList = projDao.findAll();
		session.setAttribute("projectList", projectList);
		return "OK";
	}

	// get Team
	public String findAllTeam() {
		List<Team> teamList = teamDao.findAll();
		session.setAttribute("teamList", teamList);
		return "OK";
	}

	// Get FileDetails
	public List<FileDetails> getAllFile() {
		String getEMailId = (String) session.getAttribute("UserEmail");
		User user = dao.findById(getEMailId).orElse(new User());
		List<FileDetails> filelist = filedetailsDao.findAll();

		// Create ArrayList To Add Elements
		List<FileDetails> filelistForUser = new ArrayList<FileDetails>();
		int listSize = filelist.size();

		String role = user.getUserRole().toUpperCase();

		// Check Role
		// If Role Is user Provide Access To All Document in current Team
		if (role.equalsIgnoreCase("U")) {
			System.out.println("inside User Role");
			for (int leng = 0; leng < listSize; leng++) {
				String team = user.getUserTeamName();
				System.out.println("comapre " + filelist.get(leng).getDocTeam() + "   " + team);
				if (filelist.get(leng).getDocTeam().equalsIgnoreCase(team)) {
					filelistForUser.add(filelist.get(leng));
				}
				// System.out.println("Final Check" + filelistForUser.get(0).getFileUrl());
			}
		}

		// If Role is Team Lead then team and project document can be Accessed;
		else if (role.equalsIgnoreCase("T")) {
			System.out.println("inside Team Lead Role");
			for (int leng = 0; leng < listSize; leng++) {
				String projectName = user.getUserProjectName();
				if (filelist.get(leng).getDocProject().equalsIgnoreCase(projectName)) {
					filelistForUser.add(filelist.get(leng));
				}
			}
		}
		// If Role is Manager then team, project And Department document can be
		// Accessed;
		else if (role.equalsIgnoreCase("M")) {
			for (int leng = 0; leng < listSize; leng++) {
				String department = user.getUserdepartment();
				if (filelist.get(leng).getDocDepartment().equalsIgnoreCase(department)) {
					filelistForUser.add(filelist.get(leng));
				}
			}
		}
		System.out.println(role);
		session.setAttribute("fileDetailsList", filelistForUser);
		return filelistForUser;
	}

	// Get FileDetailsBased on Tag Search
	public List<FileDetails> getAllFileByTag(String tag) {
		String getEMailId = (String) session.getAttribute("UserEmail");
		User user = dao.findById(getEMailId).orElse(new User());
		List<FileDetails> filelist = filedetailsDao.findAll();

		// Create ArrayList To Add Elements
		List<FileDetails> filelistForUser = new ArrayList<FileDetails>();
		int listSize = filelist.size();

		String role = user.getUserRole().toUpperCase();

		// Check Role
		// If Role Is user Provide All Document in current Team with tag
		if (role.equalsIgnoreCase("U")) {
			System.out.println("inside User Role");
			for (int leng = 0; leng < listSize; leng++) {
				String team = user.getUserTeamName();
				System.out.println("comapre " + filelist.get(leng).getDocTeam() + "   " + team);
				if (filelist.get(leng).getDocTeam().equalsIgnoreCase(team)
						&& filelist.get(leng).getTag().contains(tag)) {
					filelistForUser.add(filelist.get(leng));
				}
				// System.out.println("Final Check" + filelistForUser.get(0).getFileUrl());
			}
		}

		// If Role is Team Lead then team and project document can be Accessed;
		else if (role.equalsIgnoreCase("T")) {
			System.out.println("inside Team Lead Role");
			for (int leng = 0; leng < listSize; leng++) {
				String projectName = user.getUserProjectName();
				if (filelist.get(leng).getDocProject().equalsIgnoreCase(projectName)
						&& filelist.get(leng).getTag().contains(tag)) {
					filelistForUser.add(filelist.get(leng));
				}
			}
		}
		// If Role is Manager then team, project And Department document can be
		// Accessed;
		else if (role.equalsIgnoreCase("M")) {
			for (int leng = 0; leng < listSize; leng++) {
				String department = user.getUserdepartment();
				if (filelist.get(leng).getDocDepartment().equalsIgnoreCase(department)
						&& filelist.get(leng).getTag().contains(tag)) {
					filelistForUser.add(filelist.get(leng));
				}
			}
		}
		System.out.println(role);
		session.setAttribute("fileDetailsList", filelistForUser);
		return filelistForUser;
	}

	// Get FileDetailsBased on Document Type Search
	public List<FileDetails> getAllFileByType(String docType) {
		String getEMailId = (String) session.getAttribute("UserEmail");
		User user = dao.findById(getEMailId).orElse(new User());

		List<FileDetails> filelist = filedetailsDao.findAll();

		// Create ArrayList To Add Elements
		List<FileDetails> filelistForUser = new ArrayList<FileDetails>();
		int listSize = filelist.size();

		String role = user.getUserRole().toUpperCase();

		// Check Role
		// If Role Is user Provide All Document in current Team with tag
		if (role.equalsIgnoreCase("U")) {
			System.out.println("inside User Role");
			for (int leng = 0; leng < listSize; leng++) {
				String team = user.getUserTeamName();
				System.out.println("comapre " + filelist.get(leng).getDocTeam() + "   " + team);
				if (docType.equalsIgnoreCase("png")) {
					if (filelist.get(leng).getDocTeam().equalsIgnoreCase(team)
							&& filelist.get(leng).getFileName().contains("." + docType)
							|| filelist.get(leng).getFileName().contains(".jpeg")) {
						filelistForUser.add(filelist.get(leng));
					}
				} else if (filelist.get(leng).getDocTeam().equalsIgnoreCase(team)
						&& filelist.get(leng).getFileName().contains("." + docType)) {
					filelistForUser.add(filelist.get(leng));
				}
			}
		}

		// If Role is Team Lead then team and project document can be Accessed;
		else if (role.equalsIgnoreCase("T")) {
			System.out.println("inside Team Lead Role");
			for (int leng = 0; leng < listSize; leng++) {
				String projectName = user.getUserProjectName();
				if (filelist.get(leng).getDocProject().equalsIgnoreCase(projectName)
						&& filelist.get(leng).getFileName().contains("." + docType)) {
					filelistForUser.add(filelist.get(leng));
				}
			}
		}
		// If Role is Manager then team, project And Department document can be
		// Accessed;
		else if (role.equalsIgnoreCase("M")) {
			for (int leng = 0; leng < listSize; leng++) {
				String department = user.getUserdepartment();
				if (filelist.get(leng).getDocDepartment().equalsIgnoreCase(department)
						&& filelist.get(leng).getFileName().contains("." + docType)) {
					filelistForUser.add(filelist.get(leng));
				}
				// System.out.println("Final Check" + filelistForUser.get(0).getFileUrl());
			}
		}
		System.out.println(role);
		session.setAttribute("fileDetailsList", filelistForUser);
		return filelistForUser;
	}

	public void findUser(String emailId) {
		ArrayList<User> userDetails = (ArrayList<User>) dao.findByeMail(emailId);
		User userObject = userDetails.get(0);
		session.setAttribute("userObject", userObject);

	}
}
