
<%@page import="java.util.HashMap"%>
<!DOCTYPE html>

<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/file.css">
<!------ Include the above in your HEAD tag ---------->
<script>
	$(function() {
		$("#header").load("jsp/header.html");
		$("#footer").load("jsp/footer.html");
	});

     function toggle(div){
             var items=['a','b','c','c','d','e'];
             for(var i=0;i<items.length;i++){
                   if(div===items[i]){
                         $('#'+items[i]).show();
                       }
                   else{
                	   $('#'+items[i]).hide();
                       }
                 }
         }
	
	$(document).ready(function() {
		$("#createCompany").click(function() {
			toggle('a');
			/* $("#a").show();
			$("#b").hide();
			$("#c").hide();
			$("#d").hide();
			$("#e").hide(); */
		});
		$("#createDepartment").click(function() {
			toggle('b');
			/* $("#a").hide();
			$("#b").show();
			$("#c").hide();
			$("#d").hide();
			$("#e").hide(); */
		});

		$("#createProject").click(function() {
			toggle('c');
			/* $("#a").hide();
			$("#b").hide();
			$("#c").show();
			$("#d").hide();
			$("#e").hide(); */
		});

		$("#createTeam").click(function() {
			toggle('d');
			/* $("#a").hide();
			$("#b").hide();
			$("#c").hide();
			$("#d").show();
			$("#e").hide(); */
		});

		$("#createUser").click(function() {
			toggle('e');
			/* $("#a").hide();
			$("#b").hide();
			$("#c").hide();
			$("#d").hide();
			$("#e").show(); */
		});
	});
</script>
<style>

#box{
position: relative;

}

.vertical-menu {
	width: 230px;
}

.vertical-menu a {
	background-color: #eee;
	color: black;
	display: block;
	padding: 12px;
	text-decoration: none;
}

.vertical-menu a:hover {
	background-color: #ccc;
}

.vertical-menu a.active {
	background-color: #56baed;
	color: white;
}
#a,#b,#c,#d,#e{
  border-style: double;
  border-width: thick;
}
##hh{
width: 100%;
}
</style>


</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@page import="java.util.*"%>
<body>
	<div id="header"></div>
	<!-- Store Details -->
	<div id="hh">
		<div class="row">
			<div class="col-sm-3">
				<div class="vertical-menu">
					<a class="active" id="createCompany" >Create Company</a>
				    <a id="createDepartment">Create Department</a>
				    <a id="createProject">Create project</a> 
				    <a id="createTeam">Create Team</a> 
				    <a class="showSingle" class="active" id="createUser">Create User</a>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="container" id="a" >
						<!-- Create Company  -->
					<form action="createCompany" method="post">
						<fieldset>
						<legend>Create Company</legend>
						
						Company Name:<input type="text" name="companyName" size=400px> 
						<input type="submit"	value="Create">
						</fieldset>
					</form>
				</div>
				<div class="container" id="b" style="display : none">
					<!-- Create Department  -->
					<form action="createDept" method="post">
					<fieldset>
						<legend>Create Department</legend>
					Select Company <select name="companyName" class="selectOption">
								<c:forEach items="${companyList}" var="category">
									<option value="${category.companyName}">${category.companyName}</option>
								</c:forEach>
							</select> <br>
						Department Name<input type="text" name="departmentName" size=400px> 
						<input type="submit" value="Create" name="upload">
						</fieldset>
					</form>
				</div>
				<div class="container"  id="c" style="display : none">
					<!-- Create Project -->
					<form action="createProject" method="post">
					<fieldset>
						<legend>Create Project</legend>
					Select Company <select name="companyName" class="selectOption">
								<c:forEach items="${companyList}" var="category">
									<option value="${category.companyName}">${category.companyName}</option>
								</c:forEach>
							</select> <br>
						Select Department :<select name="departmentName" class="selectOption">
							<c:forEach items="${departmentList}" var="dept">
									<option value="${dept.departmentName}">${dept.departmentName}</option>
								</c:forEach>
						</select> <br>
						Project Name<input type="text" name="projectName" size=400px> 
						<input type="submit" value="Create" name="upload">
						</fieldset>
					</form>
				</div>
				<div class="container"  id="d" style="display : none">
					<!-- Create Team -->
					<form action="createTeam" method="post">
					<fieldset>
						<legend>Create Team</legend>
						Select Company <select name="companyName" required class="selectOption">
								<c:forEach items="${companyList}" var="category">
									<option value="${category.companyName}">${category.companyName}</option>
								</c:forEach>
							</select> <br>
						Select Department :<select name="deptartmentName"required class="selectOption">
							<c:forEach items="${departmentList}" var="dept">
									<option value="${dept.departmentName}">${dept.departmentName}</option>
								</c:forEach>
						</select> <br>
						Select Project<select name="projectName"required class="selectOption">
							<c:forEach items="${projectList}" var="project">
									<option value="${project.projectName}">${project.projectName}</option>
								</c:forEach>
						</select> <br>
						Team Name<input type="text" name="teamName" size=400px> 
						<input type="submit" value="Create" name="upload">
						</fieldset>
					</form>
				</div>			
				
				<div class="container"  id="e" style="display : none">
					<!-- Create User -->
					<form action="createUser" method="post">
					<fieldset>
						<legend>Create User</legend>
						Select Company <select name="userCompany" class="selectOption">
								<c:forEach items="${companyList}" var="category">
									<option value="${category.companyName}">${category.companyName}</option>
								</c:forEach>
							</select> <br>
						Select Department :<select name="userdepartment" class="selectOption">
							<c:forEach items="${departmentList}" var="dept">
									<option value="${dept.departmentName}">${dept.departmentName}</option>
								</c:forEach>
						</select> <br>
						Select Project<select name="userProjectName" class="selectOption">
							<c:forEach items="${projectList}" var="project">
									<option value="${project.projectName}">${project.projectName}</option>
								</c:forEach>
						</select> <br>
						Select Team:<select name="userTeamName" class="selectOption">
							<c:forEach items="${teamList}" var="team">
									<option value="${team.teamName}">${team.teamName}</option>
								</c:forEach>
						</select><br>
						Select Role:<select name="userRole"  class="selectOption">
							<option value="Admin">Admin</option>
							<option value="M">Manager </option>
							<option value="T">Team Lead</option>
							<option value="U">User</option>
						</select><br>				
						Name<input type="text" name="userName" size=400px> <br>
						Email Id:<input type ="text" name="eMail" size="400px">
						
						<input type="submit" value="Create" name="upload">
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br><br>
	<div id="footer"></div>
</body>

</html>