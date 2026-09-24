package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import vn.iotstar.dao.BookDAO;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	private BookDAO bookDAO = new BookDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 1;

		if (request.getParameter("page") != null) {

			page = Integer.parseInt(request.getParameter("page"));

		}

		request.setAttribute("books", bookDAO.findAll(page));

		long total = bookDAO.count();

		request.setAttribute("totalPage", (int) Math.ceil(total / 6.0));

		request.setAttribute("currentPage", page);

		HttpSession session = request.getSession();

		request.setAttribute("account", session.getAttribute("account"));
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);

	}

}