package pl.inpost.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.repositories.model.DiscountLevelEntity;

import java.util.List;

public interface DiscountLevelH2Repository extends JpaRepository<DiscountLevelEntity, Long> {
    List<DiscountLevelEntity> findByPolicy(Policy policy);
}
