package pl.inpost.api.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.model.Product;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {
    private final pl.inpost.api.repositories.ProductDAOInterface productRepo;

    Product getProduct(UUID productId) {
        return productRepo.findById(productId);
    }
}
