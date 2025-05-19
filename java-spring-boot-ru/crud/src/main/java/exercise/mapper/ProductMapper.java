package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Category;
import exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

// BEGIN
@Mapper(
        uses = { JsonNullableMapper.class, ReferenceMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {

    @Mapping(source = "categoryId", target = "category")
    public abstract Product map(ProductCreateDTO dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    public abstract ProductDTO map(Product model);

    public  void update(ProductUpdateDTO dto, @MappingTarget Product product) {
        if (dto.getCategoryId() != null && dto.getCategoryId().isPresent()) {
            Long categoryId = dto.getCategoryId().get();
            Category category = new Category();
            category.setId(categoryId);
            product.setCategory(category);
        }

        if (dto.getTitle() != null && dto.getTitle().isPresent()) {
            product.setTitle(dto.getTitle().get());
        }

        if (dto.getPrice() != null && dto.getPrice().isPresent()) {
            product.setPrice(dto.getPrice().get());
        }
    }
}
// END
