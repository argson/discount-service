package pl.inpost.api.repositories.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.inpost.api.repositories.ProductDaoInterface;
import pl.inpost.api.repositories.model.mappers.ProductEntityMapper;
import pl.inpost.api.domain.model.Product;
import pl.inpost.api.repositories.repositories.ProductH2Repository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductDAOH2Service implements ProductDaoInterface {
    private final ProductEntityMapper productEntityMapper;
    private final ProductH2Repository h2Repository;

    @Override
    public Product findById(UUID productId) {
        return h2Repository.findById(productId)
                .map(productEntityMapper::map)
                .orElseThrow(() -> new NoSuchElementException(String.format("Product id: %s not found!", productId)));
    }
}
