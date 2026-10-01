package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/checkout-success")
public class CheckoutSuccessServlet
        extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String message =
                (String) request
                        .getSession()
                        .getAttribute(
                                "checkoutMessage"
                        );

        if (message == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/home"
            );

            return;
        }


        request.setAttribute(
                "message",
                message
        );


        request.getSession()
                .removeAttribute(
                        "checkoutMessage"
                );


        request.getRequestDispatcher(
                "/WEB-INF/views/checkout-success.jsp"
        ).forward(
                request,
                response
        );
    }
}