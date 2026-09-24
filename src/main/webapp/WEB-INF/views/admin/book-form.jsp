<%@ page contentType="text/html;charset=UTF-8"%>


<!DOCTYPE html>

<html>


<head>

<meta charset="UTF-8">

<title>Thông tin sách</title>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">


</head>


<body>


	<header class="header">

		<div class="logo">📚 Book Form</div>

	</header>




	<div class="container">


		<div class="form-card">


			<h1>Thông tin sách</h1>



			<form method="post"
				action="${pageContext.request.contextPath}/admin/books">



				<input type="hidden" name="bookid" value="${book.bookid}"> <label>
					ISBN </label> <input type="text" name="isbn" value="${book.isbn}">



				<label> Tên sách </label> <input type="text" name="title"
					value="${book.title}"> <label> Nhà xuất bản </label> <input
					type="text" name="publisher" value="${book.publisher}"> <label>
					Giá </label> <input type="number" name="price" value="${book.price}">



				<label> Số lượng </label> <input type="number" name="quantity"
					value="${book.quantity}"> <label> Mô tả </label>


				<textarea name="description">

${book.description}

</textarea>



				<button>Lưu</button>


			</form>



		</div>



	</div>


</body>

</html>