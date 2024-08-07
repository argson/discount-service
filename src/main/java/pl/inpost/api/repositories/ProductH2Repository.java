package pl.inpost.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.inpost.api.repositories.model.ProductEntity;

import java.util.UUID;

public interface ProductH2Repository extends JpaRepository<ProductEntity, UUID> {
}
