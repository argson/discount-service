package pl.inpost.api.domain.repositories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.inpost.api.domain.repositories.model.ProductEntity;

import java.util.UUID;

public interface ProductH2Repository extends JpaRepository<ProductEntity, UUID> {
}
