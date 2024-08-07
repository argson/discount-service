package pl.inpost.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.inpost.api.DiscountApi;
import pl.inpost.api.controllers.mappers.DiscountDTOMapper;
import pl.inpost.api.controllers.mappers.PolicyDTOMapper;
import pl.inpost.api.domain.services.DiscountServiceInterface;
import pl.inpost.api.dto.model.DiscountDTO;
import pl.inpost.api.dto.model.PolicyDTO;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class DiscountApiController implements DiscountApi {
    private final DiscountServiceInterface discountServiceInterface;
    private final DiscountDTOMapper discountDTOMapper;
    private final PolicyDTOMapper policyDTOMapper;

    @Override
    public ResponseEntity<DiscountDTO> calculateDiscount(UUID productID, Long productCount, List<@Valid PolicyDTO> discountPolicy) throws Exception {
        var policyList = discountPolicy.stream()
                .map(policyDTOMapper::mapFrom)
                .toList();
        var discount = discountServiceInterface.calculateDiscount(productID, productCount, policyList);
        log.info("Discount for product {} has been calculated.", productID);
        return ResponseEntity.ok(discountDTOMapper.mapTo(discount));
    }
}
