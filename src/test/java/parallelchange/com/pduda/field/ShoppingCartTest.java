package parallelchange.com.pduda.field;

import org.junit.Assert;
import org.junit.Test;
import parallelchange.field.ShoppingCart;

public class ShoppingCartTest {


	@Test
	public void singleItem_numberOfProductsInTheCart() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(10);

		Assert.assertEquals(1, shoppingCart.numberOfProducts());
	}

	@Test
	public void singleItem_totalPrice() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(10);

		Assert.assertEquals(10, shoppingCart.calculateTotalPrice());
	}

	@Test
	public void singleItem_hasDiscountIfContainsAtLeastOneProductWorthAtLeast100() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(100);

		Assert.assertTrue(shoppingCart.hasDiscount());
	}

	@Test
	public void singleItem_doesNotHaveDiscountIfContainsNoProductsWorthAtLeast100() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(99);

		Assert.assertFalse(shoppingCart.hasDiscount());
	}

	@Test
	public void doubleItem_numberOfProductsInTheCart() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(10);
		shoppingCart.add(15);

		Assert.assertEquals(2, shoppingCart.numberOfProducts());
	}

	@Test
	public void doubleItem_totalPrice() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(10);
		shoppingCart.add(15);

		Assert.assertEquals(25, shoppingCart.calculateTotalPrice());
	}

	@Test
	public void doubleItem_hasNoDiscountIfContainsNoProductWorthAtLeast100() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(90);
		shoppingCart.add(10);

		Assert.assertFalse(shoppingCart.hasDiscount());
	}

	@Test
	public void doubleItem_hasDiscountIfContainsAtLeastOneProductWorthAtLeast100() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(100);
		shoppingCart.add(10);

		Assert.assertTrue(shoppingCart.hasDiscount());
	}
}