package model;

import java.time.LocalDate;

public class Order extends Cart{
	
	private int id;
	private LocalDate date;
	private float totalAmount;
	private User user;
	
	public Order () {
		
	}
	
	public Order(int id, LocalDate date, float totalAmount, User user) {
		this.id = id;
		this.date = date;
		this.user = user;
		this.totalAmount = totalAmount;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
