package pl.inpost.api.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.model.Product;
import pl.inpost.api.repositories.ProductDAOInterface;

import java.util.UUID;

@Service
@AllArgsConstructor
class ProductService {
    private final ProductDAOInterface productRepo;

    Product getProduct(UUID productId) {
        return productRepo.findById(productId);
    }
}
