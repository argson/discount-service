package pl.inpost.api.repositories;

import pl.inpost.api.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductDAOInterface {
    Product findById(UUID productId);
    List<Product> save(List<Product> products);
}
