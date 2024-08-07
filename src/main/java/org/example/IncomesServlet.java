package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Transaction;
import org.example.model.Id;
import org.example.model.Type;

import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet("/incomes/add")
public class IncomesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var session = req.getSession(false);
        if (session == null) {
            resp.getWriter().println("Not authorized");
            return;
        }

        var context = req.getServletContext();
        context.log("[IncomesServlet] doGet");

        var transactions = (LinkedHashMap<Id, Transaction>) context.getAttribute("transactions");
        int freeMoney = (int) context.getAttribute("freeMoney");

        for (var k : req.getParameterMap().keySet()) {
            int value = Integer.parseInt(req.getParameter(k));
            freeMoney += value;
            transactions.put(new Id(), (new Transaction(Type.INCOME, k, value)));
        }

        context.setAttribute("freeMoney", freeMoney);
        context.setAttribute("transactions", transactions);
        resp.getWriter().println("Incomes were added");

        try {
            Thread.sleep(3000);
            resp.sendRedirect("/summary");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
