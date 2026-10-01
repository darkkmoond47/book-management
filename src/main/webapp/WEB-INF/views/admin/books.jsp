<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html lang="vi">

<head>

<meta charset="UTF-8">

<title>Quản lý sách</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">

</head>


<body>


	<header class="header">

		<div class="logo">📚 Admin Book</div>


		<a href="${pageContext.request.contextPath}/home"> Trang chủ </a>


	</header>




	<div class="container">


		<h1 class="page-title">Quản lý sách</h1>



		<a class="btn"
			href="${pageContext.request.contextPath}/admin/books?action=add">

			+ Thêm sách </a>




		<table>


			<tr>

				<th>ID</th>

				<th>Tên sách</th>

				<th>ISBN</th>

				<th>NXB</th>

				<th>Số lượng</th>

				<th>Thao tác</th>


			</tr>




			<c:forEach items="${books}" var="b">


				<tr>


					<td>${b.bookid}</td>


					<td>${b.title}</td>


					<td>${b.isbn}</td>


					<td>${b.publisher}</td>


					<td>${b.quantity}</td>



					<td><a class="btn-small"
						href="${pageContext.request.contextPath}/admin/books?action=edit&id=${b.bookid}">
							Sửa </a> <a class="btn-danger"
						href="${pageContext.request.contextPath}/admin/books?action=delete&id=${b.bookid}"
						onclick="return confirm('Bạn có chắc muốn xóa?')"> Xóa </a></td>


				</tr>


			</c:forEach>



		</table>
		<div class="pagination">

			<c:forEach begin="1" end="${totalPage}" var="i">

				<a href="${pageContext.request.contextPath}/admin/books?page=${i}"
					class="${i == currentPage ? 'active' : ''}"> ${i} </a>

			</c:forEach>

		</div>



	</div>


</body>

</html>