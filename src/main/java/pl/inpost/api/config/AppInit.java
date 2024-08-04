package pl.inpost.api.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.inpost.api.repositories.model.ProductEntity;
import pl.inpost.api.repositories.repositories.ProductH2Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static pl.inpost.api.domain.model.Price.DenominationEnum.PLN;

@Component
@AllArgsConstructor
@Slf4j
public class AppInit implements ApplicationListener<ApplicationReadyEvent> {
    private final ProductH2Repository productH2Repository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        var products = List.of(
                ProductEntity.builder()
                        .id(UUID.fromString("43bcc57b-4538-411f-8f4c-6af4cf046786"))
                        .price(BigDecimal.valueOf(100))
                        .priceDenomination(PLN)
                        .name("Product1")
                        .build(),
                ProductEntity.builder()
                        .id(UUID.fromString("feea4b6c-cf3d-4dfc-ae41-49dd9e6b3d5d"))
                        .price(BigDecimal.valueOf(50))
                        .priceDenomination(PLN)
                        .name("Product2")
                        .build()
        );
        log.info("Populate product database");
        var savedProducts = productH2Repository.saveAllAndFlush(products);
        log.info("Database saved products:");
        savedProducts.forEach(product -> log.info("id: {}\tname: {}\tprice: {} {}", product.getId(), product.getName(), product.getPrice(), product.getPriceDenomination()));
    }
}
