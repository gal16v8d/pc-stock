package com.gsdd.pcstock.model;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Generated
@Getter
@Setter
@SuperBuilder
@ToString
public class GralFile extends PCStockFile {

  private static final long serialVersionUID = 1103464076140700986L;
  private Long quantity;

}
