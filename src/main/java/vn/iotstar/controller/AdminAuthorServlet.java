package vn.iotstar.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

import vn.iotstar.dao.AuthorDAO;
import vn.iotstar.entity.Author;



@WebServlet("/admin/authors")
public class AdminAuthorServlet extends HttpServlet {


    private AuthorDAO authorDAO = new AuthorDAO();



    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {



        String action =
                request.getParameter("action");



        if("add".equals(action)){


            request.getRequestDispatcher(
                    "/WEB-INF/views/admin/author-form.jsp"
            ).forward(request,response);


            return;

        }



        if("edit".equals(action)){


            int id = Integer.parseInt(
                    request.getParameter("id")
            );


            Author author =
                    authorDAO.findById(id);



            request.setAttribute(
                    "author",
                    author
            );



            request.getRequestDispatcher(
                    "/WEB-INF/views/admin/author-form.jsp"
            ).forward(request,response);


            return;

        }




        if("delete".equals(action)){


            int id = Integer.parseInt(
                    request.getParameter("id")
            );


            authorDAO.delete(id);



            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/authors"
            );


            return;

        }




        request.setAttribute(
                "authors",
                authorDAO.findAll()
        );



        request.getRequestDispatcher(
                "/WEB-INF/views/admin/authors.jsp"
        )
        .forward(request,response);



    }





    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {



        try{


            Author author = new Author();



            String id =
                    request.getParameter("author_id");



            if(id != null && !id.isEmpty()){

                author.setAuthor_id(
                        Integer.parseInt(id)
                );

            }



            author.setAuthor_name(
                    request.getParameter("author_name")
            );



            String date =
                    request.getParameter("date_of_birth");



            if(date != null && !date.isEmpty()){

                author.setDate_of_birth(
                        new SimpleDateFormat("yyyy-MM-dd")
                        .parse(date)
                );

            }



            authorDAO.save(author);



            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/authors"
            );



        }catch(Exception e){

            e.printStackTrace();

        }


    }


}