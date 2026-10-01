package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

import vn.iotstar.entity.Author;
import vn.iotstar.service.AuthorService;

@WebServlet("/admin/authors")
public class AdminAuthorServlet extends HttpServlet {

    private AuthorService authorService =
            new AuthorService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");

        // THÊM
        if ("add".equals(action)) {

            request.getRequestDispatcher(
                    "/WEB-INF/views/admin/author-form.jsp"
            ).forward(request, response);

            return;
        }

        // SỬA
        if ("edit".equals(action)) {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            Author author =
                    authorService.findById(id);

            request.setAttribute(
                    "author",
                    author
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/admin/author-form.jsp"
            ).forward(request, response);

            return;
        }

        // XÓA
        if ("delete".equals(action)) {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            authorService.delete(id);

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/authors"
            );

            return;
        }

        // PHÂN TRANG
        int page = 1;

        String pageParam =
                request.getParameter("page");

        if (pageParam != null &&
                !pageParam.isEmpty()) {

            try {

                page = Integer.parseInt(pageParam);

                if (page < 1) {
                    page = 1;
                }

            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        request.setAttribute(
                "authors",
                authorService.findAll(page)
        );

        long total =
                authorService.count();

        int totalPage =
                (int) Math.ceil(total / 6.0);

        request.setAttribute(
                "totalPage",
                totalPage
        );

        request.setAttribute(
                "currentPage",
                page
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/authors.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            Author author = new Author();

            String id =
                    request.getParameter("author_id");

            if (id != null && !id.isEmpty()) {

                author.setAuthor_id(
                        Integer.parseInt(id)
                );
            }

            author.setAuthor_name(
                    request.getParameter("author_name")
            );

            String date =
                    request.getParameter("date_of_birth");

            if (date != null && !date.isEmpty()) {

                author.setDate_of_birth(
                        new SimpleDateFormat(
                                "yyyy-MM-dd"
                        ).parse(date)
                );
            }

            authorService.save(author);

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/authors"
            );

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                    + "/admin/authors"
            );
        }
    }
}