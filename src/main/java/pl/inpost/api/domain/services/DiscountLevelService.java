package pl.inpost.api.domain.services;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.domain.repositories.DiscountLevelDAOInterface;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountLevelService {
    private final DiscountLevelDAOInterface daoInterface;

    public List<DiscountLevel> getDiscountLevels(Policy policy) {
        return daoInterface.getDiscountLevels(policy);
    }
}
