package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.listeners.LogAttributeChanges;
import org.example.model.Transaction;
import org.example.model.Id;
import org.example.model.Type;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var session = req.getSession(false);
        if (session == null) {
            resp.getWriter().println("Not authorized");
            return;
        }

        var context = req.getServletContext();
        var transactions = (LinkedHashMap<Id, Transaction>) context.getAttribute("transactions");

        for (Map.Entry<Id, Transaction> entry : transactions.entrySet()) {
            Transaction transaction = entry.getValue();
            Type transactionType = transaction.getType();
            switch (transactionType) {
                case EXPENSE:
                    resp.getWriter().println(String.format("- %s(%d)", transaction.getName(), transaction.getSum()));
                    break;
                case INCOME:
                    resp.getWriter().println(String.format("+ %s(%d)", transaction.getName(), transaction.getSum()));
                    break;
            }
        }
    }
}
