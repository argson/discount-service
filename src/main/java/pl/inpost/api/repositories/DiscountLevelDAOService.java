package pl.inpost.api.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.repositories.mappers.DiscountLevelEntityMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
class DiscountLevelDAOService implements DiscountLevelDAOInterface {
    private final DiscountLevelH2Repository discountLevelH2Repository;
    private final DiscountLevelEntityMapper mapper;

    @Override
    public List<DiscountLevel> getDiscountLevels(Policy policy) {
        var discountLevelEntities = discountLevelH2Repository.findByPolicy(policy);
        return discountLevelEntities.stream()
                .map(dle -> mapper.mapFrom(dle))
                .toList();
    }

    @Override
    public List<DiscountLevel> save(List<DiscountLevel> discountLevels) {
        var discountLevelEntities = discountLevels.stream()
                .map(mapper::mapTo)
                .toList();
        var resultDiscountLevelEntities = discountLevelH2Repository.saveAllAndFlush(discountLevelEntities);
        return resultDiscountLevelEntities.stream()
                .map(dle -> mapper.mapFrom(dle))
                .toList();
    }
}
