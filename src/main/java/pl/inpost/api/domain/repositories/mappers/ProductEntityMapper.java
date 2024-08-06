package pl.inpost.api.domain.repositories.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.domain.model.Product;
import pl.inpost.api.domain.repositories.model.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    @Mapping(target = "price", source = "price.value")
    @Mapping(target = "priceDenomination", source = "price.denomination")
    ProductEntity mapTo(Product source);

    @Mapping(target = "price", source = ".", qualifiedByName = "buildPrice")
    Product mapFrom(ProductEntity source);

    @Named("buildPrice")
    default Price buildPrice(ProductEntity entity) {
        return Price.builder()
                .denomination(entity.getPriceDenomination())
                .value(entity.getPrice())
                .build();
    }
}
