<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Book Home</title>

</head>


<body>


<h1>Danh sách sách</h1>


<c:forEach items="${books}" var="b">


    <div style="border:1px solid black; margin:10px; padding:10px">


        <img src="${b.cover_image}"
             width="100"
             height="120">


        <h3>
            ${b.title}
        </h3>


        <p>
            ISBN:
            ${b.isbn}
        </p>


        <p>
            Nhà xuất bản:
            ${b.publisher}
        </p>


        <p>
            Số lượng:
            ${b.quantity}
        </p>


    </div>


</c:forEach>



<hr>


<c:forEach begin="1"
           end="${totalPage}"
           var="i">


    <a href="home?page=${i}">
        ${i}
    </a>


</c:forEach>



</body>

</html>