package parallelchange.com.pduda.field;

import org.junit.Assert;
import org.junit.Test;
import parallelchange.field.ImagineThisIsAClientInADifferentRepository;

public class ImagineThisIsAClientInADifferentRepositoryTest {


	@Test
	public void singleItem_numberOfProductsInTheCart() throws Exception {
		ImagineThisIsAClientInADifferentRepository client = new ImagineThisIsAClientInADifferentRepository();

		Assert.assertEquals("Total price is 50 euro", client.formattedTotalPrice(50));
	}

}