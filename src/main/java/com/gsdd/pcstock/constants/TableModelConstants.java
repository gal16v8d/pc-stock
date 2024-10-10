package com.gsdd.pcstock.constants;

import com.gsdd.pcstock.util.PcStockLanguage;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class TableModelConstants {

  public static final String COMPARE = "Compare";
  public static final String DETAILED_COMPARE = "DetailedCompare";
  public static final String SEE = "See";
  public static final String DETAILED_SEE = "DetailedSee";

  public static final String C_MAIN = PcStockLanguage.getMessageByLocale("tbl.main.column");
  public static final String C_SND = PcStockLanguage.getMessageByLocale("tbl.sec.column");
  public static final String C_DIF = PcStockLanguage.getMessageByLocale("tbl.diff.column");
  public static final String NAME = PcStockLanguage.getMessageByLocale("tbl.name.column");
  public static final String QUANTITY = PcStockLanguage.getMessageByLocale("tbl.quantity.column");
  public static final String SIZE = PcStockLanguage.getMessageByLocale("tbl.size.column");
  public static final String RES = PcStockLanguage.getMessageByLocale("tbl.res.column");
  static final String[] COMPARE_TABLE_TITLES = {
    TableModelConstants.NAME,
    TableModelConstants.C_MAIN,
    TableModelConstants.C_SND,
    TableModelConstants.C_DIF
  };

  public static String[] getCompareTableTitles() {
    return COMPARE_TABLE_TITLES;
  }
}
