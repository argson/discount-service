package pl.inpost.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.inpost.api.DiscountPolicyApi;
import pl.inpost.api.domain.model.DiscountLevel;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.domain.services.DiscountLevelService;
import pl.inpost.api.dto.mappers.DiscountLevelDTOMapper;
import pl.inpost.api.dto.mappers.PolicyDTOMapper;
import pl.inpost.api.dto.model.DiscountLevelDTO;
import pl.inpost.api.dto.model.PolicyDTO;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class PolicyApiController implements DiscountPolicyApi {
    private final DiscountLevelService discountLevelService;
    private final PolicyDTOMapper policyDTOMapper;
    private final DiscountLevelDTOMapper discountLevelDTOMapper;
    @Override
    public ResponseEntity<Void> changeDiscountLevel(PolicyDTO policy, DiscountLevelDTO discountLevel) throws Exception {
        throw new NotImplementedException("This functionality id under construction!");
    }

    @Override
    public ResponseEntity<Void> deleteDiscountLevel(PolicyDTO policy, Long productCountThreshold) throws Exception {
        throw new NotImplementedException("This functionality id under construction!");
    }

    @Override
    public ResponseEntity<List<DiscountLevelDTO>> getDiscountLevels(PolicyDTO policy) throws Exception {
        var discountLevels = discountLevelService.getDiscountLevels(policyDTOMapper.mapFrom(policy));
        var discountLevelDTOS = discountLevels.stream()
                .map(discountLevelDTOMapper::mapFrom)
                .toList();
        return ResponseEntity.ok(discountLevelDTOS);

    }

    @Override
    public ResponseEntity<List<PolicyDTO>> getPolicies() throws Exception {
        return ResponseEntity.ok(List.of(PolicyDTO.values()));
    }
}
