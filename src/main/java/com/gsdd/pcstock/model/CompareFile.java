package com.gsdd.pcstock.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Generated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompareFile implements Serializable, Comparable<CompareFile> {

  private static final long serialVersionUID = 9034112094903638768L;
  private String name;
  private Long onMain;
  private Long onSecondary;
  private Boolean different;

  @Override
  public int compareTo(CompareFile o) {
    return this.getName().compareTo(o.getName());
  }

}
