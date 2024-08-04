package pl.inpost.api.domain.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Discount {

  private Product product;

  private Long orderCount;

  private Price discount;

  private List<Policy> policies;
}

