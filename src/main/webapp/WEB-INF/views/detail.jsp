<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>



<!DOCTYPE html>

<html lang="vi">


<head>

<meta charset="UTF-8">

<title>${book.title}</title>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">


</head>




<body>



	<header class="header">


		<div class="logo">📚 Book Store</div>


		<a href="${pageContext.request.contextPath}/home"> ← Trang chủ </a>


	</header>






	<div class="container">



		<div class="detail-card">



			<div class="detail-image">


				<img src="${pageContext.request.contextPath}/${book.cover_image}">


			</div>





			<div class="detail-info">


				<h1>${book.title}</h1>



				<p>

					<strong>ISBN:</strong> ${book.isbn}

				</p>



				<p>

					<strong>Tác giả:</strong>

					<c:forEach items="${book.authors}" var="a">

${a.author_name}

</c:forEach>

				</p>



				<p>

					<strong>Nhà xuất bản:</strong> ${book.publisher}

				</p>




				<p>

					<strong>Ngày xuất bản:</strong> ${book.publish_date}

				</p>




				<p>

					<strong>Số lượng:</strong> ${book.quantity}

				</p>




				<p>

					<strong>Giá:</strong> ${book.price}

				</p>


			</div>


		</div>






		<section class="review-box">


			<h2>Đánh giá</h2>



			<c:forEach items="${book.ratings}" var="r">


				<div class="review-item">


					<h4>${r.user.fullname}</h4>


					<p>${r.review_text}</p>


				</div>



			</c:forEach>





			<h3>Viết đánh giá</h3>



			<form>


				<textarea placeholder="Nhập đánh giá..."></textarea>


				<br>


				<button>Gửi đánh giá</button>


			</form>



		</section>




	</div>



</body>


</html>