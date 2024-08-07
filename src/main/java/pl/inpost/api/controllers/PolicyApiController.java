package pl.inpost.api.controllers;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.inpost.api.DiscountPolicyApi;
import pl.inpost.api.controllers.mappers.DiscountLevelDTOMapper;
import pl.inpost.api.controllers.mappers.PolicyDTOMapper;
import pl.inpost.api.domain.services.DiscountPolicyServiceInterface;
import pl.inpost.api.dto.model.DiscountLevelDTO;
import pl.inpost.api.dto.model.PolicyDTO;

import java.util.List;

@RestController
@AllArgsConstructor
public class PolicyApiController implements DiscountPolicyApi {
    private final DiscountPolicyServiceInterface discountPolicyServiceInterface;
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
        var discountLevels = discountPolicyServiceInterface.getDiscountPolicyLevels(policyDTOMapper.mapFrom(policy));
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
