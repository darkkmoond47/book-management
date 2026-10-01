package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import vn.iotstar.dao.UserDAO;
import vn.iotstar.entity.User;

@WebServlet("/verify-otp")
public class VerifyOTPServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/verify-otp.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String otpInput =
                request.getParameter("otp");

        HttpSession session =
                request.getSession();

        String otpSession =
                (String) session.getAttribute(
                        "register_otp"
                );

        if (otpSession != null &&
                otpSession.equals(otpInput)) {

            User user = new User();

            user.setEmail(
                    (String) session.getAttribute(
                            "register_email"
                    )
            );

            user.setFullname(
                    (String) session.getAttribute(
                            "register_fullname"
                    )
            );

            user.setPhone(
                    (String) session.getAttribute(
                            "register_phone"
                    )
            );

            user.setPasswd(
                    (String) session.getAttribute(
                            "register_password"
                    )
            );

            // Tài khoản đăng ký mới là User
            user.setAdmin(false);

            userDAO.save(user);

            // Xóa dữ liệu đăng ký tạm
            session.removeAttribute("register_email");
            session.removeAttribute("register_fullname");
            session.removeAttribute("register_phone");
            session.removeAttribute("register_password");
            session.removeAttribute("register_otp");

            response.sendRedirect(
                    request.getContextPath()
                    + "/login"
            );

        } else {

            request.setAttribute(
                    "error",
                    "OTP khong dung!"
            );

            request.getRequestDispatcher(
                    "/verify-otp.jsp"
            ).forward(request, response);
        }
    }
}