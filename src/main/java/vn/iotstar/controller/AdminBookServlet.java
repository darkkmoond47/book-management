package vn.iotstar.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

import vn.iotstar.dao.BookDAO;
import vn.iotstar.entity.Book;



@WebServlet("/admin/books")
public class AdminBookServlet extends HttpServlet {


    private BookDAO bookDAO = new BookDAO();



    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {



        String action = request.getParameter("action");



        if("add".equals(action)){


            request.getRequestDispatcher(
                    "/WEB-INF/views/admin/book-form.jsp"
            ).forward(request,response);


            return;
        }



        if("edit".equals(action)){


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
                    "/WEB-INF/views/admin/book-form.jsp"
            ).forward(request,response);


            return;

        }




        if("delete".equals(action)){


            int id = Integer.parseInt(
                    request.getParameter("id")
            );


            bookDAO.delete(id);


            response.sendRedirect(
                    request.getContextPath()
                    +"/admin/books"
            );


            return;

        }





        request.setAttribute(
                "books",
                bookDAO.findAllAdmin()
        );


        request.getRequestDispatcher(
                "/WEB-INF/views/admin/books.jsp"
        ).forward(request,response);


    }





    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {



        Book book = new Book();


        String id =
                request.getParameter("bookid");



        if(id != null && !id.isEmpty()){

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


        bookDAO.save(book);



        response.sendRedirect(
                request.getContextPath()
                +"/admin/books"
        );

    }


}