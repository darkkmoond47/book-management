<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Đăng ký - Book Store</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

</head>

<body>

<div class="login-page">

    <div class="login-box">

        <div class="login-logo">
            📚 Book Store
        </div>

        <h2>Đăng ký tài khoản</h2>

        <% if (request.getAttribute("error") != null) { %>

            <div class="error">
                <%= request.getAttribute("error") %>
            </div>

        <% } %>

        <form action="${pageContext.request.contextPath}/register"
              method="post">

            <label>Họ tên</label>

            <input
                type="text"
                name="fullname"
                placeholder="Nhập họ tên"
                required
            >

            <label>Email</label>

            <input
                type="email"
                name="email"
                placeholder="Nhập email"
                required
            >

            <label>Số điện thoại</label>

            <input
                type="text"
                name="phone"
                placeholder="Nhập số điện thoại"
                required
            >

            <label>Mật khẩu</label>

            <input
                type="password"
                name="password"
                placeholder="Nhập mật khẩu"
                required
            >

            <button type="submit">
                Đăng ký
            </button>

        </form>

        <p class="register-text">

            Đã có tài khoản?

            <a href="${pageContext.request.contextPath}/login">
                Đăng nhập
            </a>

        </p>

    </div>

</div>

</body>

</html>