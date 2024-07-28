package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.User;
import util.Stringutil;
import controller.database.Database;
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterServlet" })
@MultipartConfig(fileSizeThreshold  = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        int result = 0;
        
        Database dbcontroller = new Database();
        
        String firstName = request.getParameter(Stringutil.FIRST_NAME);
        String LastName = request.getParameter(Stringutil.LAST_NAME);
        String userName = request.getParameter(Stringutil.USER_NAME);
        String address = request.getParameter(Stringutil.ADDRESS);
        String email = request.getParameter(Stringutil.EMAIL);
        String phoneNumber = request.getParameter(Stringutil.PHONENUMBER);
        String password = request.getParameter(Stringutil.PASSWORD);
        String confirmPassword = request.getParameter(Stringutil.CONFIRM_PASSWORD);
        String gender = request.getParameter(Stringutil.GENDER);
        String dateOfBirth = request.getParameter(Stringutil.DATE);
        System.out.println(dateOfBirth);
        LocalDate dobDate = LocalDate.parse(dateOfBirth);
        System.out.println(dobDate.getYear());
        Part imagePart = request.getPart(Stringutil.IMAGE);
        
 
        User userModel = new User(firstName, LastName, userName, address, email, phoneNumber, confirmPassword, gender, dobDate, imagePart);
        
        String savePath = Stringutil.IMAGE_DIR_SAVE_PATH;
		String fileName = userModel.getImageUrlFromPart();
		if(!fileName.isEmpty() && fileName != null) {
			imagePart.write(savePath + fileName);    			
		}
		
		System.out.println(imagePart);
		System.out.println();
		System.out.println("done2");
		
		
		try {
			result = dbcontroller.addUser(userModel);
		} catch (Exception e) {
			System.out.println("The problem is: " + e.getMessage());
		}
		
		System.out.println("done2");
		if(password.equals(confirmPassword)) {
       		switch (result) {
			case 1 -> {
					request.setAttribute(Stringutil.SUCCESS_MESSAGE, Stringutil.SUCCESS_REGISTER_MESSAGE);
					response.sendRedirect(request.getContextPath() + Stringutil.LOGIN_PAGE);
					break;
			}
			case 0 ->{
				request.setAttribute(Stringutil.ERROR_MESSAGE, Stringutil.ERROR_REGISTER_MESSAGE);
				request.getRequestDispatcher(Stringutil.REGISTER_PAGE).forward(request, response);
				break;
			}
			case -1 -> {
				System.out.println("error");
				request.setAttribute(Stringutil.ERROR_MESSAGE, Stringutil.SERVER_ERROR_MESSAGE);
				request.getRequestDispatcher(Stringutil.REGISTER_PAGE).forward(request, response);
				break;
			}
			case  -2 -> {
				request.setAttribute(Stringutil.ERROR_MESSAGE, Stringutil.USERNAME_ERROR_MESSAGE);
				request.getRequestDispatcher(Stringutil.REGISTER_PAGE).forward(request, 		response);
				break;
			}
			case -3 -> {
				request.setAttribute(Stringutil.ERROR_MESSAGE, Stringutil.EMAIL_ERROR_MESSAGE);
				request.getRequestDispatcher(Stringutil.REGISTER_PAGE).forward(request, response);
				break;
			}
			case -4 ->{
				request.setAttribute(Stringutil.ERROR_MESSAGE, Stringutil.PHONE_NUMBER_ERROR_MESSAGE);
				request.getRequestDispatcher(Stringutil.REGISTER_PAGE).forward(request, response);
				break;
			}
			default ->{
				request.setAttribute(Stringutil.ERROR_MESSAGE, Stringutil.SERVER_ERROR_MESSAGE);
				request.getRequestDispatcher(Stringutil.REGISTER_PAGE).forward(request, response);
				break;
			}
       	}
	}else {
		request.setAttribute(Stringutil.ERROR_MESSAGE, Stringutil.PASSWORD_UNMATCHED_ERROR_MESSAGE);
		request.getRequestDispatcher(Stringutil.REGISTER_PAGE).forward(request, response);
	}		
	}

}
