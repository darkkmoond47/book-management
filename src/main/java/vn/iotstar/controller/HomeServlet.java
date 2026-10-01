package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import vn.iotstar.entity.User;
import vn.iotstar.service.BookService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int page = 1;

        String pageParam = request.getParameter("page");

        if (pageParam != null && !pageParam.isEmpty()) {

            try {
                page = Integer.parseInt(pageParam);

                if (page < 1) {
                    page = 1;
                }

            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // Lấy sách theo trang, 6 sách/trang
        request.setAttribute(
                "books",
                bookService.findAll(page)
        );

        // Tổng số sách
        long total = bookService.count();

        // Tổng số trang
        int totalPage = (int) Math.ceil(total / 6.0);

        request.setAttribute(
                "totalPage",
                totalPage
        );

        request.setAttribute(
                "currentPage",
                page
        );

        // Lấy tài khoản từ Session
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("account");

        request.setAttribute(
                "account",
                user
        );

        // Chuyển sang trang Home
        request.getRequestDispatcher(
                "/WEB-INF/views/home.jsp"
        ).forward(request, response);
    }
}