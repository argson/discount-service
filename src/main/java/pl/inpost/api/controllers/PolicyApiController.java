package pl.inpost.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.inpost.api.DiscountPolicyApi;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.dto.model.DiscountLevelDTO;
import pl.inpost.api.dto.model.PolicyDTO;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class PolicyApiController implements DiscountPolicyApi {
    @Override
    public ResponseEntity<Void> changeDiscountLevel(PolicyDTO policy, DiscountLevelDTO discountLevel) throws Exception {
        return DiscountPolicyApi.super.changeDiscountLevel(policy, discountLevel);
    }

    @Override
    public ResponseEntity<Void> deleteDiscountLevel(PolicyDTO policy, Long productCountThreshold) throws Exception {
        return DiscountPolicyApi.super.deleteDiscountLevel(policy, productCountThreshold);
    }

    @Override
    public ResponseEntity<List<DiscountLevelDTO>> getDiscountLevels(PolicyDTO policy) throws Exception {
        var price = Price.builder().denomination(Price.DenominationEnum.PLN).value(BigDecimal.TEN).build();
        var objectMapper = new ObjectMapper();
        var json = objectMapper.writeValueAsString(price);
        return ResponseEntity.ok(List.of(new DiscountLevelDTO().productCountThreshold(BigDecimal.TEN).discountValue(json)));

    }

    @Override
    public ResponseEntity<List<PolicyDTO>> getPolicies() throws Exception {
        return ResponseEntity.ok(List.of(PolicyDTO.values()));
    }
}
