<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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

    <div class="logo">
        📚 Book Store
    </div>

    <a href="${pageContext.request.contextPath}/home">
        ← Trang chủ
    </a>

</header>


<div class="container">

    <!-- THÔNG TIN SÁCH -->

    <div class="detail-card">

        <div class="detail-image">

            <img
                src="${pageContext.request.contextPath}/${book.cover_image}"
                alt="${book.title}"
            >

        </div>


        <div class="detail-info">

            <p>
                <strong>Tiêu đề:</strong>
                ${book.title}
            </p>

            <p>
                <strong>Mã ISBN:</strong>
                ${book.isbn}
            </p>

            <p>

                <strong>Tác giả:</strong>

                <c:forEach
                    items="${book.authors}"
                    var="a">

                    ${a.author_name}

                </c:forEach>

            </p>

            <p>
                <strong>Publisher:</strong>
                ${book.publisher}
            </p>

            <p>
                <strong>Publisher_date:</strong>
                ${book.publish_date}
            </p>

            <p>
                <strong>Quantity:</strong>
                ${book.quantity}
            </p>
            <form
    action="${pageContext.request.contextPath}/cart"
    method="post">

    <input
        type="hidden"
        name="action"
        value="add">

    <input
        type="hidden"
        name="bookid"
        value="${book.bookid}">

    <label>
        Số lượng:
    </label>

    <input
        type="number"
        name="quantity"
        value="1"
        min="1"
        max="${book.quantity}"
        style="width: 100px;">

    <button type="submit">
        🛒 Thêm vào giỏ
    </button>

</form>

        </div>

    </div>


    <!-- REVIEWS -->

    <section class="review-box">

        <h2>
            Reviews (${book.ratings.size()})
        </h2>


        <c:choose>

            <c:when test="${empty book.ratings}">

                <p>
                    Chưa có đánh giá nào.
                </p>

            </c:when>


            <c:otherwise>

                <c:forEach
                    items="${book.ratings}"
                    var="r">

                    <div class="review-item">

                        <strong>
                            ${r.user.fullname}
                        </strong>

                        <p>
                            ${r.review_text}
                        </p>

                    </div>

                </c:forEach>

            </c:otherwise>

        </c:choose>


        <!-- FORM THÊM REVIEW -->

        <h3>
            Thêm review
        </h3>


        <c:choose>

            <c:when test="${not empty sessionScope.account}">

                <form
                    action="${pageContext.request.contextPath}/review"
                    method="post">

                    <input
                        type="hidden"
                        name="bookid"
                        value="${book.bookid}"
                    >

                    <textarea
                        name="review_text"
                        placeholder="Nhập đánh giá..."
                        required
                    ></textarea>

                    <br>

                    <button type="submit">
                        Submit
                    </button>

                </form>

            </c:when>


            <c:otherwise>

                <p>
                    Vui lòng
                    <a href="${pageContext.request.contextPath}/login">
                        đăng nhập
                    </a>
                    để viết review.
                </p>

            </c:otherwise>

        </c:choose>

    </section>

</div>

</body>

</html>