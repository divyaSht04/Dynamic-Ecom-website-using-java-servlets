package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.database.Database;
import model.User;
import util.Stringutil;

/**
 * Servlet implementation class UserProfileUpdateServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserProfileUpdateServlet" })
@MultipartConfig(fileSizeThreshold  = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UserProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dateOfBirth = request.getParameter("dob");
		LocalDate dobDate = LocalDate.parse(dateOfBirth);
		String address = request.getParameter("address");
		Part image = request.getPart("image");
		
		User userModel = new User(firstName, lastName, dobDate, address , image);
		String savePath = Stringutil.IMAGE_DIR_SAVE_PATH;
		System.out.println(firstName);
		System.out.println(dobDate);
		String fileName = userModel.getImageUrlFromPart();
		
		if(!fileName.isEmpty() && fileName != null) {
			image.write(savePath + fileName);    			
		}
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("isUser");
		System.out.println(userName);
		
		Database dbController = new Database();
		int result = dbController.UpdateUSer(userName,userModel);
		System.out.println(result);
		if(result > 0) {
			response.sendRedirect("UserProfileServlet");
		}
	}



}
