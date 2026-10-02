<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container">

    <h1 class="page-title">
        Lịch sử đặt hàng
    </h1>

    <div class="form-card">

        <form
                method="get"
                action="${pageContext.request.contextPath}/order-history">

            <label for="status">
                Lọc theo trạng thái:
            </label>

            <select
                    id="status"
                    name="status"
                    style="
                        width:100%;
                        padding:12px;
                        margin-top:8px;
                        border-radius:10px;
                        border:1px solid #ccc;
                    ">

                <option
                        value="ALL"
                        ${selectedStatus == 'ALL' ? 'selected' : ''}>
                    Tất cả đơn hàng
                </option>

                <c:forEach
                        items="${statuses}"
                        var="s">

                    <option
                            value="${s}"
                            ${selectedStatus == s ? 'selected' : ''}>

                        ${s}

                    </option>

                </c:forEach>

            </select>

            <button type="submit">
                Lọc đơn hàng
            </button>

        </form>

    </div>

    <br>

    <c:choose>

        <c:when test="${empty orders}">

            <div class="form-card">

                <h2>
                    Không có đơn hàng
                </h2>

                <p>
                    Không tìm thấy đơn hàng
                    phù hợp với trạng thái đã chọn.
                </p>

            </div>

        </c:when>

        <c:otherwise>

            <c:forEach
                    items="${orders}"
                    var="order">

                <div
                        class="form-card"
                        style="margin-bottom:25px;">

                    <h2>
                        Đơn hàng #${order.id}
                    </h2>

                    <p>
                        <strong>Ngày đặt:</strong>
                        ${order.orderDate}
                    </p>

                    <p>
                        <strong>Phương thức thanh toán:</strong>
                        ${order.paymentMethod}
                    </p>

                    <p>
                        <strong>Trạng thái:</strong>

                        <span
                                style="
                                    display:inline-block;
                                    background:#2563eb;
                                    color:white;
                                    padding:6px 12px;
                                    border-radius:8px;
                                    margin-left:5px;
                                ">

                            ${order.status}

                        </span>

                    </p>

                    <hr>

                    <h3>
                        Sản phẩm
                    </h3>

                    <table>

                        <thead>

                            <tr>

                                <th>
                                    Sách
                                </th>

                                <th>
                                    Đơn giá
                                </th>

                                <th>
                                    Số lượng
                                </th>

                                <th>
                                    Thành tiền
                                </th>

                            </tr>

                        </thead>

                        <tbody>

                            <c:forEach
                                    items="${order.details}"
                                    var="detail">

                                <tr>

                                    <td>
                                        ${detail.book.title}
                                    </td>

                                    <td>
                                        ${detail.price}
                                    </td>

                                    <td>
                                        ${detail.quantity}
                                    </td>

                                    <td>
                                        ${detail.subtotal}
                                    </td>

                                </tr>

                            </c:forEach>

                        </tbody>

                    </table>

                    <div
                            style="
                                text-align:right;
                                margin-top:20px;
                            ">

                        <h3>
                            Tổng tiền:
                            ${order.totalAmount}
                        </h3>

                    </div>

                </div>

            </c:forEach>

        </c:otherwise>

    </c:choose>

</div>