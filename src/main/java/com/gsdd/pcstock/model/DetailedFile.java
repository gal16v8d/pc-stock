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
public class DetailedFile extends PCStockFile {

  private static final long serialVersionUID = -4729365422888418889L;
  private Long size;
  private String resolution;
}
