package vn.iotstar.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import vn.iotstar.dao.OrderDAO;
import vn.iotstar.entity.Order;
import vn.iotstar.entity.User;

@WebServlet("/order-history")
public class OrderHistoryServlet extends HttpServlet {

    private final OrderDAO orderDAO =
            new OrderDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        // Lấy tài khoản đang đăng nhập
        User user =
                (User) session.getAttribute("account");

        // Chưa đăng nhập
        if (user == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login"
            );

            return;
        }

        // Lấy trạng thái từ URL
        String status =
                request.getParameter("status");

        if (status == null ||
                status.trim().isEmpty()) {

            status = "ALL";
        }

        // 8 trạng thái theo yêu cầu
        List<String> statuses =
                Arrays.asList(
                        "Đơn hàng mới",
                        "Đã xác nhận",
                        "Chuẩn bị hàng",
                        "Vận chuyển",
                        "Giao hàng",
                        "Đã giao",
                        "Đơn hàng hủy",
                        "Đơn hàng hoàn"
                );

        // Lấy đơn hàng của user hiện tại
        List<Order> orders =
                orderDAO.findByUserAndStatus(
                        user.getId(),
                        status
                );

        request.setAttribute(
                "orders",
                orders
        );

        request.setAttribute(
                "statuses",
                statuses
        );

        request.setAttribute(
                "selectedStatus",
                status
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/order-history.jsp"
        ).forward(
                request,
                response
        );
    }
}