<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

<head>

<meta charset="UTF-8">

<title>Xác nhận OTP - Book Store</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">

</head>

<body>

	<div class="login-page">

		<div class="login-box">

			<div class="login-logo">📚 Book Store</div>

			<h2>Xác nhận OTP</h2>

			<p>Mã OTP đã được gửi đến email của bạn.</p>

			<%
			if (request.getAttribute("error") != null) {
			%>

			<div class="error">
				<%=request.getAttribute("error")%>
			</div>

			<%
			}
			%>

			<form action="${pageContext.request.contextPath}/verify-otp"
				method="post">

				<label>Mã OTP</label> <input type="text" name="otp"
					placeholder="Nhập mã OTP 6 số" maxlength="6" required>

				<button type="submit">Xác nhận</button>

			</form>

		</div>

	</div>

</body>

</html>