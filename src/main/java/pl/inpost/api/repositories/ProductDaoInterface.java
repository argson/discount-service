package pl.inpost.api.repositories;

import pl.inpost.api.domain.model.Product;

import java.util.UUID;

public interface ProductDaoInterface {
    Product findById(UUID productId);
}
