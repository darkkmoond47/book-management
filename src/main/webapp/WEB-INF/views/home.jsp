<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Book Store</title>
</head>

<body>

<div class="container">

    <h1 class="page-title">
        Danh sách sách
    </h1>

    <div class="book-grid">

        <c:forEach items="${books}" var="b">

            <div class="book-card">

                <img
                    src="${pageContext.request.contextPath}/${b.cover_image}"
                    class="book-img"
                    alt="${b.title}"
                >

                <h2>
                    <a href="${pageContext.request.contextPath}/detail?id=${b.bookid}">
                        ${b.title}
                    </a>
                </h2>

                <div class="info">

                    <p>
                        <strong>ISBN:</strong>
                        ${b.isbn}
                    </p>

                    <p>
                        <strong>Tác giả:</strong>

                        <c:forEach items="${b.authors}" var="a">
                            ${a.author_name}
                        </c:forEach>

                    </p>

                    <p>
                        <strong>NXB:</strong>
                        ${b.publisher}
                    </p>

                    <p>
                        <strong>Ngày xuất bản:</strong>
                        ${b.publish_date}
                    </p>

                    <p>
                        <strong>Số lượng:</strong>
                        ${b.quantity}
                    </p>

                    <p class="review">
                        ⭐ ${b.ratings.size()} đánh giá
                    </p>

                </div>

                <a
                    class="btn"
                    href="${pageContext.request.contextPath}/detail?id=${b.bookid}">
                    Xem chi tiết
                </a>

            </div>

        </c:forEach>

    </div>


    <!-- PHÂN TRANG -->
    <div class="pagination">

        <c:forEach begin="1" end="${totalPage}" var="i">

            <a href="${pageContext.request.contextPath}/home?page=${i}">
                ${i}
            </a>

        </c:forEach>

    </div>

</div>

</body>

</html>