package util;

import java.io.File;

public class Stringutil {

	public static final String CONNECTION = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/laptopgalaxy";
	public static final String USER = "root";
	public static final String PASS = "";
	
	// database queries
	public static final String INSERT_USER = "Insert into user_info (firstName, lastName, userName, address, email, phoneNumber, password, gender, dob, role, image) values (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String GET_USERNAME = "SELECT COUNT(*) FROM user_info WHERE userName = ?";
	public static final String GET_PHONE = "SELECT COUNT(*) FROM user_info WHERE phoneNumber = ?";
	public static final String GET_EMAIL = "SELECT COUNT(*) FROM user_info WHERE email = ?";
	public static final String GET_LOGIN_USER_INFO = "SELECT * FROM user_info WHERE userName = ? and password = ?";
	public static final String GET_LOGIN_USER_ROLE = "SELECT role FROM user_info WHERE userName = ? and password = ?";
	public static final String GET_USERS_QUERY = "SELECT * from user_info where role = 'user' ";
	public static final String GET_PASSWORD = "SELECT password FROM user_info WHERE userName = ?";
	public static final String GET_ROLE_PASSWORD = "SELECT password, role FROM user_info WHERE userName = ?";
	public static final String GET_USER_FROM_USERNAME = "Select * from user_info where username = ?";
	public static final String GET_PRODUCT_FROM_PRODUCT_ID = "Select * from product where product_id = ?";
	public static final String GET_USER_ID_FROM_USER = "Select id from user_info where username = ?";
	public static final String INSERT_ORDER = "INSERT INTO Orders (id,order_date) VALUES (?,?)";
	public static final String ORDER_PRODUCT = "INSERT INTO order_product (order_id, product_id, total) VALUES (?, ?, ?)";
	public static final String UPDATE_STOCK = "UPDATE product SET stock = stock - ? WHERE product_id = ?";

	// register page label name
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String USER_NAME = "userName";
	public static final String ADDRESS = "address";
	public static final String EMAIL = "email";
	public static final String PHONENUMBER = "phoneNumber";
	public static final String PASSWORD = "password";
	public static final String CONFIRM_PASSWORD = "confirmPassword";
	public static final String GENDER = "gender";
	public static final String DATE = "dob";
	public static final String IMAGE = "image";
	
	public static final String PRODUCT_ID = "product_id";
	public static final String PRODUCT_NAME = "name";
	public static final String PRODUCT_PRICE = "price";
	public static final String PRODUCT_IMAGE = "image";
	public static final String PRODUCT_STOCK = "stock";
	public static final String PRODUCT_DESCRIPTION = "description";
	public static final String PRODUCT_RATINGS = "rating";
	
	
	// pages
	public static final String REGISTER_PAGE = "/register.jsp";
	public static final String LOGIN_PAGE = "/login.jsp";
	public static final String HOME_PAGE = "/index.jsp";
	
	
	
	// file paths
	public static final String IMAGE_DIR_USER = "Users\\DELL\\eclipse-workspace\\LaptopGalaxy\\src\\main\\webapp\\resources\\images\\user\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR_USER;
	
	public static final String IMAGE_DIR_PRODUCT = "Users\\DELL\\eclipse-workspace\\LaptopGalaxy\\src\\main\\webapp\\resources\\images\\products\\";
	public static final String IMAGE_DIR_SAVE_PATH_PRODUCT = "C:" + File.separator + IMAGE_DIR_PRODUCT;
	
	// Error messages
	public static final String SUCCESS_REGISTER_MESSAGE = "Successfully Registered!";
	public static final String SUCCESS_LOGIN_MESSAGE = "Successfully LOGED IN!!";
	public static final String REGISTER_ERROR_MESSAGE = "Please correct the form data.";
	public static final String LOGIN_ERROR_MESSAGE = "Please correct the form data.";
	public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occurred.";
	public static final String SUCCESS_MESSAGE = "successMessage";
	public static final String ERROR_MESSAGE = "errorMessage";
		// page error messages
	public static final String ERROR_REGISTER_MESSAGE = "Please correct the form data.";
	public static final String SERVER_ERROR_MESSAGE1 = "An unexpected server error occurred."; 
	public static final String USERNAME_ERROR_MESSAGE = "Username is already registered."; 
	public static final String EMAIL_ERROR_MESSAGE = "Email is already registered.";
	public static final String PHONE_NUMBER_ERROR_MESSAGE = "Phone number is already registered."; 
	public static final String PASSWORD_UNMATCHED_ERROR_MESSAGE = "Password is not matched.";
	public static final String ERROR_ROLE_MESSAGE = "Invalid role!,";
	public static final String ERROR_LOGIN_MESSAGE = "Please correct the form data";
}
