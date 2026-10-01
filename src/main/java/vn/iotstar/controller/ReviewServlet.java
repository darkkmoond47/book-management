package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import vn.iotstar.dao.RatingDAO;
import vn.iotstar.entity.Book;
import vn.iotstar.entity.Rating;
import vn.iotstar.entity.User;
import vn.iotstar.service.BookService;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

    private RatingDAO ratingDAO = new RatingDAO();

    private BookService bookService =
            new BookService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession();

        // Kiểm tra đăng nhập
        User user =
                (User) session.getAttribute("account");

        if (user == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        String bookIdParam =
                request.getParameter("bookid");

        String reviewText =
                request.getParameter("review_text");

        if (bookIdParam == null ||
                reviewText == null ||
                reviewText.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/home"
            );

            return;
        }

        try {

            int bookId =
                    Integer.parseInt(bookIdParam);

            Book book =
                    bookService.findById(bookId);

            if (book == null) {

                response.sendRedirect(
                        request.getContextPath() + "/home"
                );

                return;
            }

            Rating rating = new Rating();

            rating.setUser(user);

            rating.setBook(book);

            rating.setReview_text(
                    reviewText.trim()
            );

            ratingDAO.save(rating);

            response.sendRedirect(
                    request.getContextPath()
                    + "/detail?id=" + bookId
            );

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/home"
            );
        }
    }
}