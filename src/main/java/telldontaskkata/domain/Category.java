package telldontaskkata.domain;

import java.math.BigDecimal;

public class Category {
    private String name;
    private BigDecimal taxPercentage;

    public Category(String name, BigDecimal taxPercentage) {
        this.name = name;
        this.taxPercentage = taxPercentage;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", taxPercentage=" + taxPercentage +
                '}';
    }
}
