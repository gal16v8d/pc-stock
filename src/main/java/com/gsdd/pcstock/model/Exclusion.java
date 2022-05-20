package com.gsdd.pcstock.model;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Generated
@Getter
@Setter
@EqualsAndHashCode(exclude = {"frequency"})
@ToString
public class Exclusion implements Serializable {

  private static final long serialVersionUID = -585144337387223281L;
  private String code;
  private Long frequency;
}
