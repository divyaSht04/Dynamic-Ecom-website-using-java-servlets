package model;

import java.io.File;
import java.time.LocalDate;

import javax.servlet.http.Part;
import util.Stringutil;



public class User {
	private String firstName;
    private String lastName;
	private String userName;
	private String address;	
    private String email;
    private String phoneNumber;
    private String password;
    private String gender;
    private LocalDate dateOfBirth;
    private String imageUrlFromPart;
    
    public User() {
    	
    }
   
    
    public User(String firstName, String lastName, String userName, String address, String email,
    		String phoneNumber, String password, String gender, LocalDate dateOfBirth, Part imagePart) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.userName = userName;
    	this.address = address;
    	this.email = email;
    	this.phoneNumber = phoneNumber;
    	this.password = password;
    	this.gender = gender;
    	this.dateOfBirth = dateOfBirth;
    	this.imageUrlFromPart = (imagePart != null) ? getImageUrl(imagePart) : null;
    }
    
    public User(String firstName, String lastName, LocalDate dateOfBirth, String address, Part imagePart) {
    	this.firstName = firstName;
    	this.dateOfBirth = dateOfBirth;
    	this.lastName = lastName;
    	this.address = address;
    	this.imageUrlFromPart = (imagePart != null) ? getImageUrl(imagePart) : null;
    }
  

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	    if (part == null) {
	        return "default.jpg"; // default image or logic to handle null
	    }
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
	        imageUrlFromPart = "download.jpg"; // default or placeholder image
	    }
	    return imageUrlFromPart;
	}
    

	
}
