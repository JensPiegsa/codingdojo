package telldontaskkata.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void canCalculateTaxAmount() {
        Category smallTax = new Category("smallTax", new BigDecimal(10));
        Product product = new Product("some food", new BigDecimal(123), smallTax);

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(2);

        assertThat(item.getTaxedAmount()).isEqualTo(new BigDecimal("270.6"));
    }
}