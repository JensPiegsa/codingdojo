package telldontaskkata.repository;

import telldontaskkata.domain.Product;

public interface ProductCatalog {
    Product getByName(String name);
}
