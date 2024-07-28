package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;

@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveItemServlet" })
public class RemoveItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        ArrayList<Cart> items = (ArrayList<Cart>) session.getAttribute("cartItems");

        if (items != null) {
            items.removeIf(item -> item.getId() == id);
        }

        session.setAttribute("cartItems", items);
        response.sendRedirect("cart.jsp");
    }
}
