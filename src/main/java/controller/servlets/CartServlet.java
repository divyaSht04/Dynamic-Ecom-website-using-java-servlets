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
 * Servlet implementation class CartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CartServlet" })
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Cart cart = new Cart();
        cart.setId(id);
        cart.setQuantity(1);

        HttpSession session = request.getSession();
        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

        if (cartList == null) {
            cartList = new ArrayList<>();
            cartList.add(cart);
            session.setAttribute("cart-list", cartList);
        } else {
            boolean exists = false;
            for (Cart c : cartList) {
                if (c.getId() == id) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                cartList.add(cart);
            }
        }
        response.sendRedirect("IndexServlet");
    }
}
