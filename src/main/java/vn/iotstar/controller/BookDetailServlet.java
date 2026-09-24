package vn.iotstar.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


import vn.iotstar.dao.BookDAO;
import vn.iotstar.entity.Book;



@WebServlet("/detail")
public class BookDetailServlet extends HttpServlet {


    private BookDAO bookDAO = new BookDAO();



    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        int id = Integer.parseInt(
                request.getParameter("id")
        );


        Book book =
                bookDAO.findById(id);



        request.setAttribute(
                "book",
                book
        );


        request.getRequestDispatcher(
                "/WEB-INF/views/detail.jsp"
        ).forward(request, response);


    }

}