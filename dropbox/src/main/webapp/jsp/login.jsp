<%@page import="java.util.HashMap"%>

<%
HashMap<String,String> hm=new HashMap<String,String>();
hm.put("msg1","INVALID PASSWORD OR UID");
%>
<!DOCTYPE html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/file2.css">
<!------ Include the above in your HEAD tag ---------->
<script> 
  $(function(){
    $("#header").load("jsp/headerindex.jsp"); 
    $("#footer").load("jsp/footer.html"); 
  });

  </script>
<style type="text/css">
#hideMe {
	-webkit-animation: cssAnimation 5s forwards;
	animation: cssAnimation 5s forwards;
}
</style>
</head>
<body>

<div id="header" class="position-sticky"></div>
<div class="wrapper fadeInDown">
		<div id="formContent">
    <!-- Icon -->
    <div class="fadeIn first">
      <img src="images/0.png" id="icon" alt="User Icon" height="100" width="180"/>
    <h2 style="color:#969799;">Aroha File Box</h2>
   
    </div>
 <br>
    <!-- Login Form -->
    <form action="myData" method="post">
      
      <input type="text" name="eMail" id="login" class="fadeIn second" placeholder="User Email" required style="font-family: 'Courier New', Courier, monospace;">
      <input type="password" name="userPwd"  id="password" class="fadeIn third" placeholder="password"  required style="font-family: 'Courier New', Courier, monospace;">
      <input type="submit" class="fadeIn fourth" value="Log In" style="font-family: 'Courier New', Courier, monospace;">
    </form>
    
    
    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="jsp/otpGenerator.html" style="font-family: 'Courier New', Courier, monospace;">Forgot Password?</a>
    </div>
  </div>

</div>
<div id="footer"></div>
</body>
</html>