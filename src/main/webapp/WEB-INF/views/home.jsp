<%@ page import="vn.iotstar.entity.User"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html lang="vi">


<head>

<meta charset="UTF-8">

<title>Book Store</title>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">


</head>


<body>


	<header class="header">


		<div class="logo">📚 Book Store</div>



		<nav>


			<a href="${pageContext.request.contextPath}/home"> Trang chủ </a> <a
				href="#"> Sản phẩm </a>


			<%
			User user = (User) session.getAttribute("account");

			if (user != null) {
			%>


			<span class="welcome"> Xin chào <%=user.getFullname()%>

			</span> <a href="${pageContext.request.contextPath}/logout"> Đăng xuất </a>


			<%
			} else {
			%>


			<a href="${pageContext.request.contextPath}/login.jsp"> Đăng nhập

			</a>


			<%
			}
			%>



		</nav>


	</header>





	<main class="container">



		<h1 class="page-title">Danh sách sách</h1>





		<div class="book-grid">



			<c:forEach items="${books}" var="b">


				<div class="book-card">



					<img src="${pageContext.request.contextPath}/${b.cover_image}"
						class="book-img">





					<h2>

						<a href="${pageContext.request.contextPath}/detail?id=${b.bookid}">

							${b.title} </a>

					</h2>





					<div class="info">


						<p>
							<strong>ISBN:</strong> ${b.isbn}
						</p>



						<p>

							<strong>Tác giả:</strong>

							<c:forEach items="${b.authors}" var="a">

${a.author_name}

</c:forEach>

						</p>



						<p>

							<strong>NXB:</strong> ${b.publisher}

						</p>




						<p>

							<strong>Ngày xuất bản:</strong> ${b.publish_date}

						</p>



						<p>

							<strong>Số lượng:</strong> ${b.quantity}

						</p>



						<p class="review">⭐ ${b.ratings.size()} đánh giá</p>



					</div>



					<a class="btn"
						href="${pageContext.request.contextPath}/detail?id=${b.bookid}">

						Xem chi tiết </a>



				</div>


			</c:forEach>



		</div>





		<div class="pagination">


			<c:forEach begin="1" end="${totalPage}" var="i">


				<a href="${pageContext.request.contextPath}/home?page=${i}">

					${i} </a>


			</c:forEach>


		</div>



	</main>




	<footer class="footer">

		<p>Họ tên: Dương Thành Đạt</p>

		<p>MSSV: 24110191</p>

		<p>Mã đề: 01</p>


	</footer>



</body>


</html>