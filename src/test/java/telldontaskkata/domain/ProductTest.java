package telldontaskkata.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductTest {
    @Test
    void canCalculateTaxAmount() {
        Category smallTax = new Category("smallTax", new BigDecimal("0.1"));
        Product product = new Product("some food", new BigDecimal(123), smallTax);


    }
}