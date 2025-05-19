package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withPriceGt(params.getPriceGt())
                        .and(withPriceLt(params.getPriceLt()))
                        .and(withRatingGt(params.getRatingGt()))
                        .and(withTitleCont(params.getTitleCont())));
    }

    private Specification<Product> withTitleCont(String title) {
        return (root, query, criteriaBuilder) -> title == null ?
                criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("title"), title);
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return ((root, query, criteriaBuilder) -> categoryId == null ?
                criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("category").get("id"), categoryId));
    }

    private Specification<Product> withPriceLt(Integer price) {
        return (root, query, criteriaBuilder) -> price == null ?
                criteriaBuilder.conjunction() : criteriaBuilder.lessThan(root.get("price"), price);
    }

    private Specification<Product> withPriceGt(Integer price) {
        return ((root, query, criteriaBuilder) -> price == null ?
                criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("price"), price));
    }

    private Specification<Product> withRatingGt(Double rating) {
        return (root, query, criteriaBuilder) -> rating == null ?
                criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("rating"), rating);
    }
}
// END
