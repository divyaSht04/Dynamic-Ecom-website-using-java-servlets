package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.Database;
import model.Product;

@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateItemServlet" })
public class UpdateItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productID");
        System.out.println(productId);

        Database dbController = new Database();
        Product product = dbController.getProductById(Integer.parseInt(productId));

        if (product != null) {
            request.setAttribute("product", product);
            request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Product not found.");
            response.sendRedirect("adminProducts.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("i am here too");
        int productId = Integer.parseInt(request.getParameter("productId"));
        System.out.println("i am here");
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int ratings = Integer.parseInt(request.getParameter("ratings"));



        Database dbController = new Database();
        boolean success = dbController.updateProduct(productId, name, price, stock, ratings);

        if (success) {
            request.setAttribute("success", "Product updated successfully.");
        } else {
            request.setAttribute("error", "Failed to update product. Please try again.");
        }

        response.sendRedirect("AdminServlet");
    }

}
