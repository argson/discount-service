package pl.inpost.api.repositories.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.inpost.api.repositories.model.ProductEntity;
import pl.inpost.api.domain.model.Price;
import pl.inpost.api.domain.model.Product;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    @Mapping(target = "price", source = ".", qualifiedByName = "buildPrice")
    Product map(ProductEntity source);

    @Named("buildPrice")
    default Price buildPrice(ProductEntity entity){
        return Price.builder()
                .denomination(entity.getPriceDenomination())
                .value(entity.getPrice())
                .build();
    }
}
