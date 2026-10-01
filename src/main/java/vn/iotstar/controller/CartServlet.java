package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vn.iotstar.entity.Book;
import vn.iotstar.model.CartItem;
import vn.iotstar.service.BookService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private BookService bookService = new BookService();

    private static final String CART = "cart";

    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute(CART);

        if (cart == null) {

            cart = new ArrayList<>();

            session.setAttribute(CART, cart);
        }

        return cart;
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<CartItem> cart = getCart(session);

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cart) {
            total = total.add(item.getSubtotal());
        }

        request.setAttribute("cart", cart);
        request.setAttribute("total", total);

        request.getRequestDispatcher(
                "/WEB-INF/views/cart.jsp"
        ).forward(request, response);
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        HttpSession session = request.getSession();

        List<CartItem> cart = getCart(session);


        // =========================
        // THÊM VÀO GIỎ
        // =========================
        if ("add".equals(action)) {

            try {

                int bookId =
                        Integer.parseInt(
                                request.getParameter("bookid")
                        );

                int quantity = 1;

                String quantityParam =
                        request.getParameter("quantity");

                if (quantityParam != null &&
                        !quantityParam.isEmpty()) {

                    quantity =
                            Integer.parseInt(quantityParam);
                }

                if (quantity < 1) {
                    quantity = 1;
                }

                Book book =
                        bookService.findById(bookId);

                if (book == null) {

                    response.sendRedirect(
                            request.getContextPath() + "/home"
                    );

                    return;
                }

                if (book.getQuantity() == null ||
                        book.getQuantity() <= 0) {

                    session.setAttribute(
                            "cartMessage",
                            "Sách đã hết hàng."
                    );

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/detail?id=" + bookId
                    );

                    return;
                }


                boolean found = false;

                for (CartItem item : cart) {

                    if (item.getBook()
                            .getBookid()
                            .equals(bookId)) {

                        int newQuantity =
                                item.getQuantity()
                                + quantity;

                        if (newQuantity >
                                book.getQuantity()) {

                            newQuantity =
                                    book.getQuantity();
                        }

                        item.setQuantity(newQuantity);

                        found = true;

                        break;
                    }
                }


                if (!found) {

                    if (quantity > book.getQuantity()) {
                        quantity = book.getQuantity();
                    }

                    cart.add(
                            new CartItem(
                                    book,
                                    quantity
                            )
                    );
                }

                session.setAttribute(
                        "cartMessage",
                        "Đã thêm sách vào giỏ hàng."
                );

                response.sendRedirect(
                        request.getContextPath() + "/cart"
                );

            } catch (NumberFormatException e) {

                response.sendRedirect(
                        request.getContextPath() + "/home"
                );
            }

            return;
        }


        // =========================
        // CẬP NHẬT SỐ LƯỢNG
        // =========================
        if ("update".equals(action)) {

            for (CartItem item : cart) {

                String paramName =
                        "quantity_"
                        + item.getBook().getBookid();

                String value =
                        request.getParameter(paramName);

                if (value == null ||
                        value.isEmpty()) {

                    continue;
                }

                try {

                    int quantity =
                            Integer.parseInt(value);

                    if (quantity <= 0) {

                        item.setQuantity(0);

                    } else {

                        int max =
                                item.getBook().getQuantity();

                        if (quantity > max) {
                            quantity = max;
                        }

                        item.setQuantity(quantity);
                    }

                } catch (NumberFormatException e) {
                    // Bỏ qua giá trị không hợp lệ
                }
            }


            Iterator<CartItem> iterator =
                    cart.iterator();

            while (iterator.hasNext()) {

                CartItem item = iterator.next();

                if (item.getQuantity() <= 0) {
                    iterator.remove();
                }
            }


            session.setAttribute(
                    "cartMessage",
                    "Đã cập nhật giỏ hàng."
            );

            response.sendRedirect(
                    request.getContextPath() + "/cart"
            );

            return;
        }


        // =========================
        // XÓA 1 SẢN PHẨM
        // =========================
        if ("delete".equals(action)) {

            try {

                int bookId =
                        Integer.parseInt(
                                request.getParameter("bookid")
                        );

                cart.removeIf(
                        item ->
                                item.getBook()
                                        .getBookid()
                                        .equals(bookId)
                );

            } catch (NumberFormatException e) {
                // Không làm gì
            }


            response.sendRedirect(
                    request.getContextPath() + "/cart"
            );

            return;
        }


        // =========================
        // XÓA TOÀN BỘ GIỎ
        // =========================
        if ("clear".equals(action)) {

            session.removeAttribute(CART);

            response.sendRedirect(
                    request.getContextPath() + "/cart"
            );

            return;
        }


        response.sendRedirect(
                request.getContextPath() + "/cart"
        );
    }
}