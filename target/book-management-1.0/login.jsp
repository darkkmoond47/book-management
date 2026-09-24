<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Login</title>

</head>


<body>


<h2>Đăng nhập</h2>



<form action="login" method="post">


Email:

<input type="text"
       name="email">


<br>


Password:

<input type="password"
       name="password">


<br>


<button type="submit">
Đăng nhập
</button>



</form>



<%
if(request.getParameter("error") != null){
%>

<p style="color:red">
Sai tài khoản hoặc mật khẩu
</p>

<%
}
%>



</body>

</html>