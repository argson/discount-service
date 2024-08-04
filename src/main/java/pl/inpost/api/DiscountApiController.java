package pl.inpost.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.inpost.api.domain.model.Policy;
import pl.inpost.api.dto.mappers.PolicyDTOMapper;
import pl.inpost.api.dto.model.DiscountDTO;
import pl.inpost.api.domain.services.DiscountService;
import pl.inpost.api.dto.mappers.DiscountDTOMapper;
import pl.inpost.api.dto.model.PolicyDTO;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class DiscountApiController implements DiscountApi {
    private final DiscountService discountService;
    private final DiscountDTOMapper discountDTOMapper;
    private final PolicyDTOMapper policyDTOMapper;


    @Override
    public ResponseEntity<DiscountDTO> getDiscount(UUID productID, Long productCount, List<@Valid PolicyDTO> discountPolicy) throws Exception {
        var policyList = discountPolicy.stream()
                .map(policyDTOMapper::mapFrom)
                .toList();
        var discount = discountService.calculateDiscount(productID, productCount, policyList);
        return ResponseEntity.ok(discountDTOMapper.map(discount));
    }
}
