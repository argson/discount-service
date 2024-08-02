package pl.inpost.api.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.inpost.api.persistance.model.ProductEntity;

import java.util.UUID;

public interface ProductRepo extends JpaRepository<ProductEntity, UUID> {
}
