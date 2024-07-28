package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.Database;
import model.Product;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminServlet" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}                                             

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database dbcontroller = new Database();
        ArrayList<Product> products = dbcontroller.getAllProducts("default");
        request.setAttribute("productList", products);
        System.out.println("done this!");
        
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
	}

}
