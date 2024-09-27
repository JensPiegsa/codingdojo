package telldontaskkata.domain;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(taxPercentage, category.taxPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, taxPercentage);
    }
}
