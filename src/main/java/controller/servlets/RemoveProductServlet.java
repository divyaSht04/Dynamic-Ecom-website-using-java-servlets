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
import model.Product;

@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveProductServlet" })
public class RemoveProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Database dbController = new Database();
        
        HttpSession session = request.getSession();
        ArrayList<Product> products = dbController.getAllProducts("default");
        
        dbController.updateProducts(products, id);
        response.sendRedirect("AdminServlet");
    }
}
