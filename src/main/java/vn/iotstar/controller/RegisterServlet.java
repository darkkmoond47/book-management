package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Random;

import vn.iotstar.dao.UserDAO;
import vn.iotstar.entity.User;
import vn.iotstar.util.MailUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/register.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Tạo OTP 6 số
        String otp = String.valueOf(
                100000 + new Random().nextInt(900000)
        );

        HttpSession session = request.getSession();

        // Lưu thông tin đăng ký tạm thời vào Session
        session.setAttribute("register_email", email);
        session.setAttribute("register_fullname", fullname);
        session.setAttribute("register_phone", phone);
        session.setAttribute("register_password", password);
        session.setAttribute("register_otp", otp);

        try {

            MailUtil.sendOTP(email, otp);

            response.sendRedirect(
                    request.getContextPath()
                    + "/verify-otp"
            );

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    "Khong the gui OTP. Vui long kiem tra email."
            );

            request.getRequestDispatcher(
                    "/register.jsp"
            ).forward(request, response);
        }
    }
}