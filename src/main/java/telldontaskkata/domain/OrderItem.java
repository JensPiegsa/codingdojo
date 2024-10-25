package telldontaskkata.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTaxedAmount() {
        BigDecimal price = product.getPrice();
        BigDecimal taxPercentage = product.getTaxPercentage();
        taxedAmount = price.multiply(taxPercentage.add(new BigDecimal(100))).divide(new BigDecimal(100), HALF_UP).multiply(new BigDecimal(quantity));
        return taxedAmount;
    }

    public void setTaxedAmount(BigDecimal taxedAmount) {
        this.taxedAmount = taxedAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Objects.equals(product, orderItem.product) && Objects.equals(taxedAmount, orderItem.taxedAmount) && Objects.equals(tax, orderItem.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, taxedAmount, tax);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", taxedAmount=" + taxedAmount +
                ", tax=" + tax +
                '}';
    }
}
