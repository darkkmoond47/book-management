<%@ page contentType="text/html;charset=UTF-8"%>


<!DOCTYPE html>

<html>


<head>

<meta charset="UTF-8">

<title>Tác giả</title>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">


</head>


<body>


	<div class="container">


		<div class="form-card">


			<h1>Thông tin tác giả</h1>



			<form method="post"
				action="${pageContext.request.contextPath}/admin/authors">



				<input type="hidden" name="author_id" value="${author.author_id}">



				<label> Tên tác giả </label> <input type="text" name="author_name"
					value="${author.author_name}"> <label> Ngày sinh </label> <input
					type="date" name="date_of_birth" value="${author.date_of_birth}">



				<button>Lưu</button>



			</form>


		</div>


	</div>


</body>


</html>