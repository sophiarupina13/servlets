package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/errorHandler")
public class ErrorHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleError(req, resp);
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");

        if (statusCode != null) {
            if (statusCode == 404) {
                resp.setContentType("text/html");
                resp.getWriter().println(("<h1>Error 404 – Page Not Found</h1>"));
                resp.getWriter().println("<p>We're so sorry...</p>");
            } else if (statusCode == 500 ){
                resp.setContentType("text/html");
                resp.getWriter().println("Error 500 - Internal Server Error");
                resp.getWriter().println("<p>We're so sorry...</p>");
            }
        }
    }

}
