package pl.inpost.api.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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

