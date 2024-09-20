package telldontaskkata.doubles;

import telldontaskkata.domain.Product;
import telldontaskkata.repository.ProductCatalog;

import java.util.List;

public class InMemoryProductCatalog implements ProductCatalog {
    private final List<Product> products;

    public InMemoryProductCatalog(List<Product> products) {
        this.products = products;
    }

    public Product getByName(final String name) {
        return products.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }
}
