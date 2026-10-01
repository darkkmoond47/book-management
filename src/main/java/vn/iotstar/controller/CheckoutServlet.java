package vn.iotstar.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import vn.iotstar.dao.OrderDAO;
import vn.iotstar.entity.User;
import vn.iotstar.model.CartItem;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrderDAO orderDAO =
            new OrderDAO();


    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(
            HttpSession session) {

        return (List<CartItem>)
                session.getAttribute("cart");
    }


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        // Phải đăng nhập
        User user =
                (User) session.getAttribute(
                        "account"
                );

        if (user == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login"
            );

            return;
        }


        // Phải có sản phẩm trong giỏ
        List<CartItem> cart =
                getCart(session);

        if (cart == null ||
                cart.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/cart"
            );

            return;
        }


        request.setAttribute(
                "cart",
                cart
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/checkout.jsp"
        ).forward(
                request,
                response
        );
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();


        // Kiểm tra tài khoản
        User user =
                (User) session.getAttribute(
                        "account"
                );

        if (user == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login"
            );

            return;
        }


        // Lấy giỏ hàng
        List<CartItem> cart =
                getCart(session);

        if (cart == null ||
                cart.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/cart"
            );

            return;
        }


        try {

            Integer orderId =
                    orderDAO.createOrder(
                            user,
                            cart
                    );


            // Thanh toán thành công
            session.removeAttribute(
                    "cart"
            );


            session.setAttribute(
                    "checkoutMessage",
                    "Thanh toán thành công! Mã đơn hàng: "
                            + orderId
            );


            response.sendRedirect(
                    request.getContextPath()
                            + "/checkout-success"
            );

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            request.setAttribute(
                    "cart",
                    cart
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/checkout.jsp"
            ).forward(
                    request,
                    response
            );
        }
    }
}