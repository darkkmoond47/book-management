package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import vn.iotstar.dao.UserDAO;
import vn.iotstar.entity.User;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        request.getRequestDispatcher(
                "/WEB-INF/views/login.jsp")
                .forward(request, response);

    }
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        String email = request.getParameter("email");

        String password = request.getParameter("password");


        User user = userDAO.login(email, password);



        if(user != null){


            HttpSession session = request.getSession();


            session.setAttribute(
                    "account",
                    user
            );


            System.out.println(
                    "LOGIN SUCCESS: "
                    + user.getFullname()
            );


            response.sendRedirect("home");


        }else{


            System.out.println(
                    "LOGIN FAILED"
            );


            response.sendRedirect(
                    "login.jsp?error=true"
            );

        }

    }

}