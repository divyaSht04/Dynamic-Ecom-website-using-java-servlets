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

/**
 * Servlet implementation class IncrementDecrementServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/IncrementDecrementServlet" })
public class IncrementDecrementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        ArrayList<Cart> items = (ArrayList<Cart>) session.getAttribute("cartItems");

        if (items != null) {
            for (Cart item : items) {
                if (item.getId() == id) {
                    int quantity = item.getQuantity();
                    if ("inc".equals(action)) {
                        if (quantity < item.getStock()) {
                            item.setQuantity(++quantity);
                           
                        } else {
                        	System.out.println(item.getStock());
                        	System.out.println(item.getQuantity());
                            System.out.println("Stock limit reached");
                            break;
                        }
                    } else if ("dec".equals(action)) {
                        if (quantity > 1) {
                            item.setQuantity(--quantity);
                        } else {
                            System.out.println("Quantity cannot be less than 1");
                            break;
                        }
                    }
                    item.setTotalPrice(item.getQuantity() * item.getPrice());
                    break;
                }
            }
            
        }
        

        session.setAttribute("cartItems", items);
        response.sendRedirect("cart.jsp"); // Redirect to the cart page
    }
}
