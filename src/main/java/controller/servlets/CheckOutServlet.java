package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.Database;
import model.Cart;
import model.Order;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CheckOutServlet" })
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Database dbController = new Database();
        float total = Float.parseFloat(request.getParameter("total"));
        HttpSession session = request.getSession();
        ArrayList<Cart> items = (ArrayList<Cart>) session.getAttribute("cartItems");
        String userName = (String) session.getAttribute("isUser");

        if (items != null && !items.isEmpty() && userName != null) {
            ArrayList<Order> orderedItems = dbController.getOrderInfo(items, total, userName);
            System.out.println("completed");

            if (!orderedItems.isEmpty()) {
                int orderId = dbController.insertOrder(orderedItems.get(0), userName);
                System.out.println("completed 2");

                if (orderId > 0) {
                    dbController.insertOrderItems(orderId, items, total);
                    dbController.updateProductStock(items);
                    session.removeAttribute("cartItems");
                    response.sendRedirect("orderConfirmation.jsp");
                } else {
                    session.setAttribute("orderError", "Error placing order. Please try again.");
                    response.sendRedirect("cart.jsp");
                }
            }
        } else {
            session.setAttribute("orderError", "Your cart is empty or user is not logged in. Please add items to your cart before placing an order.");
            response.sendRedirect("cart.jsp");
        }
    }
}
