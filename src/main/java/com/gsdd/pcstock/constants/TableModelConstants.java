package com.gsdd.pcstock.constants;

import com.gsdd.pcstock.util.PCStockLanguage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TableModelConstants {

  public static final String COMPARE = "Compare";
  public static final String DETAILED_COMPARE = "DetailedCompare";
  public static final String SEE = "See";
  public static final String DETAILED_SEE = "DetailedSee";

  public static final String C_MAIN = PCStockLanguage.getMessageByLocale("tbl.main.column");
  public static final String C_SND = PCStockLanguage.getMessageByLocale("tbl.sec.column");
  public static final String C_DIF = PCStockLanguage.getMessageByLocale("tbl.diff.column");
  public static final String NAME = PCStockLanguage.getMessageByLocale("tbl.name.column");
  public static final String QUANTITY = PCStockLanguage.getMessageByLocale("tbl.quantity.column");
  public static final String SIZE = PCStockLanguage.getMessageByLocale("tbl.size.column");
  public static final String RES = PCStockLanguage.getMessageByLocale("tbl.res.column");
  protected static final String[] COMPARE_TABLE_TITLES = {
    TableModelConstants.NAME,
    TableModelConstants.C_MAIN,
    TableModelConstants.C_SND,
    TableModelConstants.C_DIF
  };

  public static String[] getCompareTableTitles() {
    return COMPARE_TABLE_TITLES;
  }
}
