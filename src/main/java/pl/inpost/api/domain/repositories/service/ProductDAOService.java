package pl.inpost.api.domain.repositories.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.model.Product;
import pl.inpost.api.domain.repositories.mappers.ProductEntityMapper;
import pl.inpost.api.domain.repositories.repositories.ProductH2Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductDAOService implements pl.inpost.api.repositories.ProductDAOInterface {
    private final ProductEntityMapper mapper;
    private final ProductH2Repository h2Repository;

    @Override
    public Product findById(UUID productId) {
        return h2Repository.findById(productId)
                .map(mapper::mapFrom)
                .orElseThrow(() -> new NoSuchElementException(String.format("Product id: %s not found!", productId)));
    }

    public List<Product> save(List<Product> products) {
        var productEntities = products.stream()
                .map(mapper::mapTo)
                .toList();
        var savedProductEntities = h2Repository.saveAllAndFlush(productEntities);
        return savedProductEntities.stream()
                .map(mapper::mapFrom)
                .toList();
    }
}
