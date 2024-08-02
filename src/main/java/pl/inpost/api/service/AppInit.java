package pl.inpost.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.inpost.api.model.Price;
import pl.inpost.api.persistance.model.ProductEntity;
import pl.inpost.api.persistance.repo.ProductRepo;

import java.math.BigDecimal;
import java.util.List;

import static pl.inpost.api.model.Price.DenominationEnum.PLN;

@Component
@AllArgsConstructor
@Slf4j
public class AppInit implements ApplicationListener<ApplicationReadyEvent> {
    private final ProductRepo productRepo;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        var products = List.of(
                ProductEntity.builder()
                        .price(BigDecimal.valueOf(100))
                        .priceDenomination(PLN)
                        .name("Product1")
                        .build(),
                ProductEntity.builder()
                        .price(BigDecimal.valueOf(50))
                        .priceDenomination(PLN)
                        .name("Product2")
                        .build()
        );
        log.info("Populate product database");
        var savedProducts = productRepo.saveAllAndFlush(products);
        log.info("Database saved products:");
        savedProducts.forEach(product -> log.info("id: {}\tname: {}\tprice: {} {}", product.getId(), product.getName(), product.getPrice(), product.getPriceDenomination()));
    }
}
