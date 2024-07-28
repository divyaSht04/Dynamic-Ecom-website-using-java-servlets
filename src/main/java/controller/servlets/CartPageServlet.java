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


@WebServlet(asyncSupported = true, urlPatterns = { "/CartPageServlet" })
public class CartPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Database dbController = new Database();
		
		System.out.println("Here in cartPage Servlet");
		
		HttpSession session = request.getSession();
		ArrayList<Cart> itemsID = (ArrayList<Cart>) session.getAttribute("cart-list");
		System.out.println(itemsID);
	
		ArrayList<Cart> items = dbController.getCartItems(itemsID);	
		if(items == null || items.isEmpty()) {
			System.out.println("add some  items first");
			response.sendRedirect("cart.jsp");
		}else {
			for(Cart item:items) {
				System.out.println(item.getName());
				System.out.println(item.getPrice());
				System.out.println(item.getId());
				System.out.println(item.getImageUrlFromPart());
			}	
			
			session.setAttribute("cart-list", items);
			session.setAttribute("cartItems", items);
			response.sendRedirect("cart.jsp");
		}
		
	}

}
