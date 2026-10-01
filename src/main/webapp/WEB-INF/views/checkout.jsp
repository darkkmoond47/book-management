<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container">

    <h1 class="page-title">
        Thanh toán đơn hàng
    </h1>


    <c:if test="${not empty error}">

        <div class="error">
            ${error}
        </div>

    </c:if>


    <div class="form-card">

        <h2>
            Thông tin người mua
        </h2>

        <p>
            <strong>Họ tên:</strong>
            ${sessionScope.account.fullname}
        </p>

        <p>
            <strong>Email:</strong>
            ${sessionScope.account.email}
        </p>

        <p>
            <strong>Số điện thoại:</strong>
            ${sessionScope.account.phone}
        </p>

    </div>


    <br>


    <div class="form-card">

        <h2>
            Sản phẩm trong đơn hàng
        </h2>


        <table>

            <thead>

                <tr>

                    <th>Sách</th>

                    <th>Đơn giá</th>

                    <th>Số lượng</th>

                    <th>Thành tiền</th>

                </tr>

            </thead>


            <tbody>

                <c:set
                    var="total"
                    value="0"
                />


                <c:forEach
                        items="${cart}"
                        var="item">

                    <tr>

                        <td>
                            ${item.book.title}
                        </td>

                        <td>
                            ${item.book.price}
                        </td>

                        <td>
                            ${item.quantity}
                        </td>

                        <td>
                            ${item.subtotal}
                        </td>

                    </tr>

                </c:forEach>

            </tbody>

        </table>

    </div>


    <div class="cart-total">

        <h2>
            Xác nhận thanh toán
        </h2>

        <p>
            Sau khi xác nhận, hệ thống sẽ tạo đơn hàng
            và cập nhật số lượng sách trong kho.
        </p>


        <form
            action="${pageContext.request.contextPath}/checkout"
            method="post">

            <button
                type="submit"
                class="btn">

                Xác nhận thanh toán

            </button>


            <a
                href="${pageContext.request.contextPath}/cart"
                class="btn">

                Quay lại giỏ hàng

            </a>

        </form>

    </div>

</div>