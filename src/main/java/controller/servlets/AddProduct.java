package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.Database;
import model.Product;
import util.Stringutil;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProduct" })
@MultipartConfig(fileSizeThreshold  = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("i am here ");
		String productName = request.getParameter("productName");
		String productStockStr = request.getParameter("productStock");
		String productRatingsStr = request.getParameter("productRatings");
		String productPriceStr = request.getParameter("productPrice");
		String productDescription = request.getParameter("productDescription");
		Part imagePart = request.getPart("productImage");

		if (productName == null || productStockStr == null || productRatingsStr == null || productPriceStr == null || productDescription == null || imagePart == null) {
			System.out.println("values are null");
			return;
		}

		int productStock = Integer.parseInt(productStockStr);
		int productRatings = Integer.parseInt(productRatingsStr);
		float productPrice = Float.parseFloat(productPriceStr);

		Product productModel = new Product(productName, productDescription, productStock, productPrice, imagePart, productRatings);

		String savePath = Stringutil.IMAGE_DIR_SAVE_PATH_PRODUCT;
		String fileName = productModel.getImageUrlFromPart();
		if (!fileName.isEmpty() && fileName != null) {
			imagePart.write(savePath + fileName);
		}

		Database dbController = new Database();

		try {
			int result = dbController.addProduct(productModel);

			if (result > 0) {
				response.sendRedirect("AdminServlet");
			} else {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the product.");
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
		}
	}
}
