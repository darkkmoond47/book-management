<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>

<html lang="vi">


<head>


<meta charset="UTF-8">


<title>Đăng nhập - Book Store</title>



<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">



</head>




<body>



	<div class="login-page">



		<div class="login-box">



			<div class="login-logo">📚 Book Store</div>



			<h2>Đăng nhập tài khoản</h2>




			<%
			if (request.getParameter("error") != null) {
			%>



			<div class="error">Sai email hoặc mật khẩu!</div>



			<%
			}
			%>





			<form action="${pageContext.request.contextPath}/login" method="post">





				<label> Email </label> <input type="text" name="email"
					placeholder="Nhập email"> <label> Mật khẩu </label> <input
					type="password" name="password" placeholder="Nhập mật khẩu">





				<button type="submit">Đăng nhập</button>




			</form>




			<p class="register-text">


				Chưa có tài khoản? <a href="#"> Đăng ký </a>


			</p>




		</div>



	</div>



</body>


</html>