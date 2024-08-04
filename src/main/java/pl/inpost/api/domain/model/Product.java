package pl.inpost.api.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Product{
  private UUID id;
  private String name;
  private Price price;
}

