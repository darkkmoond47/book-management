<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container">

    <h1 class="page-title">
        🛒 Giỏ hàng
    </h1>


    <c:if test="${not empty sessionScope.cartMessage}">

        <div class="cart-message">
            ${sessionScope.cartMessage}
        </div>

        <%
            session.removeAttribute("cartMessage");
        %>

    </c:if>


    <c:choose>

        <c:when test="${empty cart}">

            <div class="empty-cart">

                <h2>
                    Giỏ hàng đang trống
                </h2>

                <p>
                    Bạn chưa có sản phẩm nào trong giỏ hàng.
                </p>

                <a
                    class="btn"
                    href="${pageContext.request.contextPath}/home">

                    Tiếp tục mua sách

                </a>

            </div>

        </c:when>


        <c:otherwise>

            <form
                action="${pageContext.request.contextPath}/cart"
                method="post">

                <input
                    type="hidden"
                    name="action"
                    value="update">


                <table class="cart-table">

                    <thead>

                        <tr>

                            <th>Sách</th>

                            <th>Đơn giá</th>

                            <th>Số lượng</th>

                            <th>Thành tiền</th>

                            <th>Thao tác</th>

                        </tr>

                    </thead>


                    <tbody>

                        <c:forEach
                                items="${cart}"
                                var="item">

                            <tr>

                                <td>

                                    <div class="cart-book">

                                        <img
                                            src="${pageContext.request.contextPath}/${item.book.cover_image}"
                                            class="cart-img"
                                            alt="${item.book.title}">

                                        <span>
                                            ${item.book.title}
                                        </span>

                                    </div>

                                </td>


                                <td>
                                    ${item.book.price}
                                </td>


                                <td>

                                    <input
                                        type="number"
                                        name="quantity_${item.book.bookid}"
                                        value="${item.quantity}"
                                        min="1"
                                        max="${item.book.quantity}"
                                        class="quantity-input">

                                </td>


                                <td>
                                    ${item.subtotal}
                                </td>


                                <td>

                                    <button
                                        type="button"
                                        class="btn-danger"
                                        onclick="deleteItem(${item.book.bookid})">

                                        Xóa

                                    </button>

                                </td>

                            </tr>

                        </c:forEach>

                    </tbody>

                </table>


                <div class="cart-actions">

                    <button type="submit" class="btn">
                        Cập nhật giỏ hàng
                    </button>

                    <button
                        type="button"
                        class="btn-danger"
                        onclick="clearCart()">

                        Xóa toàn bộ

                    </button>

                </div>

            </form>


            <div class="cart-total">

                <h2>
                    Tổng tiền:
                    ${total}
                </h2>

                <a
                    class="btn"
                    href="${pageContext.request.contextPath}/checkout">

                    Thanh toán

                </a>

            </div>

        </c:otherwise>

    </c:choose>

</div>


<form
    id="deleteForm"
    action="${pageContext.request.contextPath}/cart"
    method="post">

    <input
        type="hidden"
        name="action"
        value="delete">

    <input
        type="hidden"
        name="bookid"
        id="deleteBookId">

</form>


<form
    id="clearForm"
    action="${pageContext.request.contextPath}/cart"
    method="post">

    <input
        type="hidden"
        name="action"
        value="clear">

</form>


<script>

function deleteItem(bookid) {

    if (confirm("Bạn có chắc muốn xóa sách này khỏi giỏ hàng?")) {

        document.getElementById("deleteBookId").value = bookid;

        document.getElementById("deleteForm").submit();
    }
}


function clearCart() {

    if (confirm("Bạn có chắc muốn xóa toàn bộ giỏ hàng?")) {

        document.getElementById("clearForm").submit();
    }
}

</script>