package vn.iotstar.controller;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {


    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {


        HttpSession session =
                request.getSession();


        session.invalidate();


        response.sendRedirect(
                "login.jsp");

    }

}