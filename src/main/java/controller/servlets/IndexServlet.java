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


@WebServlet(asyncSupported = true, urlPatterns = { "/IndexServlet" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String sortOption = request.getParameter("sortOption");
    
        if (sortOption == null || sortOption.isEmpty()) {
            sortOption = "default"; // Set your default sorting option here
        }

        System.out.println(sortOption);

        Database dbcontroller = new Database();
        ArrayList<Product> products = dbcontroller.getAllProducts(sortOption);

        request.setAttribute("productList", products);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
