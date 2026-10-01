package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

import vn.iotstar.entity.Book;
import vn.iotstar.service.BookService;

@WebServlet("/admin/books")
public class AdminBookServlet extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // THÊM
        if ("add".equals(action)) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/admin/book-form.jsp"
            ).forward(request, response);

            return;
        }

        // SỬA
        if ("edit".equals(action)) {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            Book book = bookService.findById(id);

            request.setAttribute("book", book);

            request.getRequestDispatcher(
                    "/WEB-INF/views/admin/book-form.jsp"
            ).forward(request, response);

            return;
        }

        // XÓA
        if ("delete".equals(action)) {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            bookService.delete(id);

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/books"
            );

            return;
        }

        // PHÂN TRANG
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

        request.setAttribute(
                "books",
                bookService.findAllAdmin(page)
        );

        long total = bookService.count();

        int totalPage =
                (int) Math.ceil(total / 6.0);

        request.setAttribute(
                "totalPage",
                totalPage
        );

        request.setAttribute(
                "currentPage",
                page
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/books.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");

        Book book = new Book();

        String id =
                request.getParameter("bookid");

        if (id != null && !id.isEmpty()) {

            book.setBookid(
                    Integer.parseInt(id)
            );
        }

        book.setIsbn(
                request.getParameter("isbn")
        );

        book.setTitle(
                request.getParameter("title")
        );

        book.setPublisher(
                request.getParameter("publisher")
        );

        book.setDescription(
                request.getParameter("description")
        );

        book.setQuantity(
                Integer.parseInt(
                        request.getParameter("quantity")
                )
        );

        book.setPrice(
                new BigDecimal(
                        request.getParameter("price")
                )
        );

        bookService.save(book);

        response.sendRedirect(
                request.getContextPath()
                + "/admin/books"
        );
    }
}