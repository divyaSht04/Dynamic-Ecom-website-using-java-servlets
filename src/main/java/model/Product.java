package model;

import java.io.File;

import javax.servlet.http.Part;

import util.Stringutil;

public class Product {
	private int id;
	private String name;
	private float price;
	private int stock;
	private String description;
	private String imageUrlFromPart;
	private int rating;
	
	public Product() {
		
	}

	public Product(int id, String name, String description, int stock, float price, Part imagePart ,int rating) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.price = price;
		this.imageUrlFromPart = getImageUrl(imagePart);
		this.rating = rating;
	}
	
	public Product(String name, String description, int stock, float price, Part imagePart ,int rating) {
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.price = price;
		this.imageUrlFromPart = getImageUrl(imagePart);
		this.rating = rating;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
 
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}

	public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}

	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
	}

	private String getImageUrl(Part part) {
		String savePath = Stringutil.IMAGE_DIR_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "download.jpg";
		}
		return imageUrlFromPart;
	}

}
