package controller.servlets;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.Database;
import model.User;

 
@WebServlet(asyncSupported = true, urlPatterns = { "/UserProfileServlet" })
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Database dbController = new Database();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("isUser");
		
		System.out.println(username);
		ArrayList<User> user = dbController.getUser(username);
		System.out.println(user);
		
		for(User info:user) {
			System.out.println(info.getFirstName());
			System.out.println(info.getImageUrlFromPart());
		}
		
		request.setAttribute("userInfo", user);
		request.getRequestDispatcher("/UserProfile.jsp").forward(request, response);
	}

}
