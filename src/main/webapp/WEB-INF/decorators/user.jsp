<%@ page import="vn.iotstar.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>
        <sitemesh:write property="title"/>
    </title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <sitemesh:write property="head"/>

    <style>

        .sitemesh-user-layout {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .sitemesh-user-content {
            flex: 1;
        }

        .footer {
            margin-top: 0;
        }

    </style>

</head>

<body>

<%
    User user =
            (User) session.getAttribute("account");
%>

<div class="sitemesh-user-layout">

    <!-- =========================
         HEADER
         ========================= -->

    <div class="header">

        <div class="logo">
            📚 Book Store
        </div>


        <div>

            <a href="${pageContext.request.contextPath}/home">
                Trang chủ
            </a>

            <a href="${pageContext.request.contextPath}/home">
                Sản phẩm
            </a>

            <a href="${pageContext.request.contextPath}/cart">
                🛒 Giỏ hàng
            </a>

            <%
                if (user != null && user.isAdmin()) {
            %>

                <a href="${pageContext.request.contextPath}/admin/books">
                    Quản trị sách
                </a>

                <a href="${pageContext.request.contextPath}/admin/authors">
                    Quản trị tác giả
                </a>

            <%
                }
            %>


            <!-- LỊCH SỬ ĐẶT HÀNG -->

            <%
                if (user != null) {
            %>

                <a href="${pageContext.request.contextPath}/order-history">
                    📋 Lịch sử đặt hàng
                </a>

            <%
                }
            %>


            <!-- ĐÃ ĐĂNG NHẬP -->

            <%
                if (user != null) {
            %>

                <span class="welcome">
                    Xin chào
                    <%= user.getFullname() %>
                </span>

                <a href="${pageContext.request.contextPath}/logout">
                    Đăng xuất
                </a>


            <!-- CHƯA ĐĂNG NHẬP -->

            <%
                } else {
            %>

                <a href="${pageContext.request.contextPath}/login">
                    Đăng nhập
                </a>

            <%
                }
            %>

        </div>

    </div>


    <!-- =========================
         CONTENT
         ========================= -->

    <main class="sitemesh-user-content">

        <sitemesh:write property="body"/>

    </main>


    <!-- =========================
         FOOTER
         ========================= -->

    <div class="footer">

        <p>
            Họ tên: Dương Thành Đạt
        </p>

        <p>
            MSSV: 24110191
        </p>

        <p>
            Mã đề: 01
        </p>

    </div>

</div>

</body>

</html>