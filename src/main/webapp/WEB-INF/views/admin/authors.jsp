<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>

<html>


<head>

<meta charset="UTF-8">

<title>Quản lý tác giả</title>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">


</head>


<body>


	<header class="header">

		<div class="logo">✍️ Authors</div>


		<a href="${pageContext.request.contextPath}/admin/books"> Quản lý
			sách </a>


	</header>



	<div class="container">


		<h1>Danh sách tác giả</h1>



		<a class="btn"
			href="${pageContext.request.contextPath}/admin/authors?action=add">

			+ Thêm tác giả </a>



		<table>


			<tr>

				<th>ID</th>

				<th>Tên tác giả</th>

				<th>Ngày sinh</th>

				<th>Thao tác</th>


			</tr>



			<c:forEach items="${authors}" var="a">


				<tr>


					<td>${a.author_id}</td>


					<td>${a.author_name}</td>


					<td>${a.date_of_birth}</td>


					<td><a class="btn-small"
						href="${pageContext.request.contextPath}/admin/authors?action=edit&id=${a.author_id}">
							Sửa </a> <a class="btn-danger"
						href="${pageContext.request.contextPath}/admin/authors?action=delete&id=${a.author_id}">
							Xóa </a></td>


				</tr>


			</c:forEach>



		</table>



	</div>



</body>


</html>