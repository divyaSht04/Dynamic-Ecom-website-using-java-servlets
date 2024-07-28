package model;

public class Cart extends Product{

	private int quantity;
	private float totalPrice;
	
	public Cart (int quantity, float totalPrice) {
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public Cart() {
		
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
