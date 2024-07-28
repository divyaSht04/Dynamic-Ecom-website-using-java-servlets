package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.Database;
import model.Product;
import util.Stringutil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("We are in login servlet!");

        int result = 0;
        int role = 0;

        String userName = request.getParameter(Stringutil.USER_NAME);
        String password = request.getParameter(Stringutil.PASSWORD);


        Database dbcontroller = new Database();
        ArrayList<Product> products = dbcontroller.getAllProducts("id");
        System.out.println(products);

        try {
            result = dbcontroller.getUser(userName, password);
            role = dbcontroller.getRole(userName, password);
        } catch (Exception e) {
            System.out.println("The error is: " + e.getMessage());
            return;
        }

        if (result == 1) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30 * 60); // 30 minutes
            
            Cookie userCookie = new Cookie("userName", userName);
            userCookie.setMaxAge(60 * 60); // 1 hour
            response.addCookie(userCookie);

            if (role == 2) {
                session.setAttribute("isAdmin", userName); // Set admin attribute
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            } else if (role == 1) {
                System.out.println("here!");
                request.setAttribute("productList", products);
                session.setAttribute("isUser", userName); // Set user attribute
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                request.setAttribute(Stringutil.ERROR_MESSAGE, Stringutil.ERROR_ROLE_MESSAGE);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute(Stringutil.ERROR_MESSAGE, Stringutil.ERROR_LOGIN_MESSAGE);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
