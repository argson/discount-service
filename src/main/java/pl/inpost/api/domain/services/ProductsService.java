package pl.inpost.api.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.model.Product;
import pl.inpost.api.repositories.ProductDaoInterface;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductsService {
    private final ProductDaoInterface productRepo;

    Product getProduct(UUID productId) {
        return productRepo.findById(productId);
    }
}
