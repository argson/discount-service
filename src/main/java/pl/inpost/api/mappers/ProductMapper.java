package pl.inpost.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.inpost.api.model.Price;
import pl.inpost.api.model.Product;
import pl.inpost.api.persistance.model.ProductEntity;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "price", source = ".", qualifiedByName = "convertPrice")
    Product map(ProductEntity source);

    @Named("convertPrice")
    default Price buildPrice(ProductEntity entity){
        return new Price().denomination(entity.getPriceDenomination()).value(entity.getPrice());
    }
}
