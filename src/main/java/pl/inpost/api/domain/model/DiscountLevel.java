package pl.inpost.api.domain.model;

public record DiscountLevel<T>(Long productCount, T discountValue) implements Comparable<DiscountLevel> {
    @Override
    public int compareTo(DiscountLevel o) {
        return this.productCount.compareTo(productCount);
    }
}
