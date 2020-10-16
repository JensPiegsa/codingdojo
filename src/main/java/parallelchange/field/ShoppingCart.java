package parallelchange.field;

public class ShoppingCart {
	private int totalPrice;
	private int productCount = 0;
	private boolean hasDiscount = false;

	public void add(int price) {
		totalPrice += price;
		productCount++;
		hasDiscount |=  price >= 100;
	}

	public int calculateTotalPrice() {
		return totalPrice;
	}

	public boolean hasDiscount() {
		return hasDiscount;
	}

	public int numberOfProducts() {
		return productCount;
	}
}
