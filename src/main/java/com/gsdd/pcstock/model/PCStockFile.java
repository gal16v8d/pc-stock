package com.gsdd.pcstock.model;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Generated
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@ToString
public class PCStockFile implements Serializable, Comparable<PCStockFile> {

  private static final long serialVersionUID = -4683954003144121588L;
  private String name;

  @Override
  public int compareTo(PCStockFile o) {
    return this.getName().compareTo(o.getName());
  }
}
