package pl.inpost.api.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.inpost.api.domain.model.*;
import pl.inpost.api.domain.repositories.service.DiscountLevelDAOService;
import pl.inpost.api.domain.repositories.service.ProductDAOService;

import java.math.BigDecimal;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import static pl.inpost.api.domain.helpers.DiscountParameterHelper.discountParameterForAmount;
import static pl.inpost.api.domain.helpers.DiscountParameterHelper.discountParameterForPercentage;
import static pl.inpost.api.domain.model.Price.DenominationEnum.PLN;

@Component
@AllArgsConstructor
@Slf4j
public class AppInit implements ApplicationListener<ApplicationReadyEvent> {
    private final ProductDAOService productDAOService;
    private final DiscountLevelDAOService discountLevelDAOService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        var products = List.of(

                Product.builder()
                        .id(UUID.fromString("43bcc57b-4538-411f-8f4c-6af4cf046786"))
                        .price(Price.builder()
                                .value(BigDecimal.valueOf(100))
                                .denomination(PLN)
                                .build())
                        .name("Product1")
                        .build(),
                Product.builder()
                        .id(UUID.fromString("feea4b6c-cf3d-4dfc-ae41-49dd9e6b3d5d"))
                        .price(Price.builder()
                                .value(BigDecimal.valueOf(50))
                                .denomination(PLN)
                                .build())
                        .name("Product2")
                        .build()
        );

        var amountDiscountLevels = List.of(
                new DiscountLevel(10L, discountParameterForAmount(2.0, PLN), Policy.AMOUNT_BASED),
                new DiscountLevel(100L, discountParameterForAmount(5.0, PLN), Policy.AMOUNT_BASED),
                new DiscountLevel(200L, discountParameterForAmount(10.0, PLN), Policy.AMOUNT_BASED)
        );

        var percentageDiscountLevels = List.of(
                new DiscountLevel(10L, discountParameterForPercentage(5), Policy.PERCENTAGE_BASED),
                new DiscountLevel(50L, discountParameterForPercentage(7), Policy.PERCENTAGE_BASED),
                new DiscountLevel(100L, discountParameterForPercentage(10), Policy.PERCENTAGE_BASED)
        );

        log.info("Populate product database");
        var savedProducts = productDAOService.save(products);
        log.info("Database saved products:");
        savedProducts.forEach(product -> log.info("id: {}\tname: {}\tprice: {} {}", product.getId(), product.getName(), product.getPrice().getValue(), product.getPrice().getDenomination()));

        List<DiscountLevel> savedDiscountLevels = discountLevelDAOService.save(amountDiscountLevels);
        log.info("Database saved discountLevels:");
        savedDiscountLevels.forEach(dl -> log.info("productCountThreshold: {}\tparameters: {}\tpolicy: {}", dl.productCountThreshold(), printParameters(dl.discountParameters()), dl.policy()));

        List<DiscountLevel> savedPercentageDiscountLevels = discountLevelDAOService.save(percentageDiscountLevels);
        savedPercentageDiscountLevels.forEach(dl -> log.info("productCountThreshold: {}\tparameters: {}\tpolicy: {}", dl.productCountThreshold(), printParameters(dl.discountParameters()), dl.policy()));

    }

    private String printParameters(List<DiscountParameter> discountParameters) {
        var stringJoiner = new StringJoiner(" | ");
        discountParameters.stream()
                .map(dp -> dp.name().name() + ":" + dp.value())
                .forEach(s -> stringJoiner.add(s));
        return stringJoiner.toString();
    }
}
