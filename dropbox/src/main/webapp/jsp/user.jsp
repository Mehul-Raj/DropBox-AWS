<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="com.example.demo.model.FileDetails"%>
<%@page import="com.example.demo.model.User"%>
<!DOCTYPE html>

<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/file.css">
<!------ Include the above in your HEAD tag ---------->
<script>
	$(function() {
		$("#header").load("jsp/header.html");
		$("#footer").load("jsp/footer.html");
	});

	function toggle(div) {
		var items = [ 'search', 'Upload', 'c'];
		for (var i = 0; i < items.length; i++) {
			if (div === items[i]) {
				$('#' + items[i]).show();
			} else {
				$('#' + items[i]).hide();
			}
		}
	}

	$(document).ready(function() {
		$("#SearchDiv").click(function() {
			 $("#search").show();
			$("#Upload").hide();
			$("#c").hide();
			
		});

		$("#uploadDocument").click(function() {
			 $("#search").hide();
			$("#Upload").show();
			$("#c").hide();
			
		});
	});
	function viewLink(url){
		console.log("url",url);
		window.sessionStorage.setItem("url",url);
		window.open("/static/filereader.html","_self");
		}
</script>
<style>
.vertical-menu {
	width: 30%;
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

#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 80%;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

#customers tr:hover {
	background-color: #ddd;
}

#customers th {
	padding-top: 6px;
	padding-bottom: 6px;
	text-align: left;
	background-color: #56baed;;
	color: white;
}

#Upload, #c {
	border-style: double;
	border-width: thick;
}
#details{
color: #56baed;
font-family: cursive;
font-size: large;
text-align: right;

}
#viewurl{
background-color: #56baed;
	border: none;
	color: white;
	padding: 11px 30px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	text-transform: uppercase;
	font-size: 13px;
	-webkit-box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
	box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
	-webkit-border-radius: 5px 5px 5px 5px;
	border-radius: 5px 5px 5px 5px;
	margin: 5px 20px 40px 20px;
	-webkit-transition: all 0.3s ease-in-out;
	-moz-transition: all 0.3s ease-in-out;
	-ms-transition: all 0.3s ease-in-out;
	-o-transition: all 0.3s ease-in-out;
	transition: all 0.3s ease-in-out;

}
</style>
</head>

<body>
	                <%
					User userObj = (User) session.getAttribute("userObject");
	                String level=null;
	                if(userObj.getUserRole().equalsIgnoreCase("U")){
	                	level="Team Level";
	                }else if(userObj.getUserRole().equalsIgnoreCase("T")){
	                	level="Project Level";
	                }else if(userObj.getUserRole().equalsIgnoreCase("M")){
	                	level="Department Level";
	                }
					%>
	<div id="header"></div>
	<div id="details">
		Name: <%=userObj.getUserName()%>&nbsp;&nbsp;&#8741;&nbsp;&nbsp;
			Project Name: <%=userObj.getUserProjectName() %>&nbsp;&nbsp;&#8741;&nbsp;&nbsp;
			Team Name:<%=userObj.getUserTeamName() %>&nbsp;&nbsp;&#8741;&nbsp;&nbsp;
			Access Level:<%=level%>
			</div>
	<!-- Store Details -->
	<div >
		<div class="row">
			<div class="col-sm-3 col-xs-5 col-md-3">
			
				<div class="vertical-menu">
				<input type="button" id="uploadDocument" value="Upload  Document">
				<!-- 	<a id="uploadDocument" class="active">Upload Document</a>  -->
					<form action="getFileList">
					<input type="submit" value="Show  Documents">
					</form>
					<!-- <a href="/getFileList">Show Documents</a> --> 
					<input type="button" id="SearchDiv" value="Search By Tag/Type">
					<!-- <a id="SearchDiv">Search By Tag/Type</a> -->
				</div>
			</div>
			
			<div class="col-sm-9 col-xs-7 col-md-9">
				<div class="row" id="search" style="display : none">
					<div class="col-sm-4 col-xs-6 col-md-5" >
						<div >
							<h3>Search By Tag</h3>
							<form action="searchByTag" method="post">
								Select Tag:<select id="searcSelect"  name="selectedTag">
									<option value="java">java</option>
									<option value="Sql">Sql</option>
									<option value="Angular">Angular</option>
									<option value="React">React</option>
									<option value="Php">Php</option>
								</select> <input type="submit" value="GO" class="inputtypeSubmit">
							</form>
						</div>
					</div>
					<div class="col-sm-6">
						<div  class="col-sm-8 col-xs-6 col-md-7">
							<h3>Search By Doc</h3>
							<form action="searchByType" method="post">
								Select Doc Type:<select id="searcSelect"  name="tagDocType">
									<option value="pdf">pdf</option>
									<option value="doc">doc</option>
									<option value="png">Image</option>
									<option value="pptx">ppt</option>
									<option value="mp4">video</option>
								</select> <input type="submit" value="GO" class="inputtypeSubmit">
							</form>
						</div>
					</div>
				</div>
				<div class="container" id="Upload" style="display : none">
					<!-- Upload file  -->
					<form action="uploadFile" method="post"
						enctype="multipart/form-data">
						Select File:<input type="file" name="file"> Upload File To
						:<select name="uploadTo" id="companyList">
							<option value="Company">Company</option>
							<option value="Department">Department</option>
							<option value="Project">Project</option>
							<option value="Team">Team</option>
						</select><br> Select Tag:<select id="multipleSelect"
							multiple="multiple" name="multiTag">
							<option value="java">java</option>
							<option value="Sql">Sql</option>
							<option value="Angular">Angular</option>
							<option value="React">React</option>
							<option value="Php">Php</option>
							<br>
						</select> <input type="submit" value="Process" name="upload">
					</form>
				</div>
				<div id="c" style="border: none;">

					<%
						ArrayList<FileDetails> al = (ArrayList<FileDetails>) session.getAttribute("fileDetailsList");
						if (al != null) {
					%>
					<table id="customers">
						<tr style="background-color: #56baed;">
							<th colspan="8">Documents</th>
						</tr>
						<tr>
							<td>File Name</td>
							<td>Uploaded By</td>
							<td>Team Name</td>
							<td>Project Name</td>
							<td>File Size</td>
							<td>File Tag</td>
							<td>Uploaded To</td>
							<td>File Url</td>
						</tr>
						<%
							Iterator<FileDetails> itr = al.iterator();
								while (itr.hasNext()) {
									FileDetails temp = itr.next();
						%>
						<tr>
							<td><%=temp.getFileName()%></td>
							<td><%=temp.getUploadBy()%></td>
							<td><%=temp.getDocTeam()%></td>
							<td><%=temp.getDocProject()%></td>
							<td><%=temp.getFileSize()%></td>
							<td><%=temp.getTag()%></td>
							<td><%=temp.getUploadFileTo()%></td>
							<td><a href="<%=temp.getFileUrl()%>">View</a></td>
						<%-- 	<td><input type="button" id="viewurl" onclick="viewLink('<%=temp.getFileUrl()%>');" value="View"></td>
						 --%>	<%-- <td><a onclick="viewLink('<%=temp.getFileUrl()%>');">View</a></td> --%>
							
						</tr>
						<%
							}
							} else {
								System.out.println("AL is Null");
							}
						%>

					</table>

				</div>


			</div>
		</div>
	</div>
	<div><br></div>
	<div id="footer"></div>
</body>

</html>