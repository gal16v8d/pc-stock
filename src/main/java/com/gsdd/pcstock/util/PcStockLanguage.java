package com.gsdd.pcstock.util;

import java.util.Locale;
import java.util.ResourceBundle;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class PcStockLanguage {

  private static final String BUNDLE_RESOURCE = "msg/messages";
  public static final String MAIN_DIR_BTN_TXT = "main.directory.button.text";
  public static final String SEC_DIR_BTN_TXT = "sec.directory.button.text";
  public static final String COMPARE_BTN_TXT = "compare.button.text";
  public static final String RENAME_BTN_TXT = "rename.button.text";
  public static final String SEE_BTN_TXT = "see.button.text";
  public static final String DETAIL_CHK_TXT = "detail.check.text";
  public static final String RES_CHK_TXT = "res.check.text";
  public static final String OPTION_MENU_TXT = "options.menu.text";
  public static final String COMPARE_MENU_TXT = "compare.menu.text";
  public static final String COMPARE_MENU_TOOLTIP = "compare.menu.tooltip";
  public static final String SEE_MENU_TXT = "see.menu.text";
  public static final String SEE_MENU_TOOLTIP = "see.menu.tooltip";
  public static final String RENAME_MENU_TXT = "rename.menu.text";
  public static final String RENAME_MENU_TOOLTIP = "rename.menu.tooltip";
  public static final String SUCCESS_TXT = "success.text";
  public static final String SELECT_MAIN_DIR_TXT = "select.main.dir.text";
  public static final String SELECT_SEC_DIR_TXT = "select.sec.dir.text";
  public static final String ADD_FILTERS_TXT = "add.filters.text";
  public static final String ADD_FILTERS_DESC_TXT = "add.filters.desc.text";
  public static final String RENAME_TITLE_TXT = "rename.title.text";
  public static final String RENAME_MSG_TXT = "rename.msg.text";
  public static final String RENAME_SELECT_DIR_TXT = "rename.select.dir.text";
  public static final String ADD_FILTERS_ERR_TXT = "add.filters.err.text";
  public static final String RENAME_SUCCESS_TXT = "rename.success.text";
  public static final String RENAME_FAIL_TXT = "rename.fail.text";

  private static ResourceBundle bundle;

  public static void initBundle(Locale locale) {
    try {
      bundle = ResourceBundle.getBundle(BUNDLE_RESOURCE, locale);
    } catch (Exception e) {
      bundle = ResourceBundle.getBundle(BUNDLE_RESOURCE, Locale.ENGLISH);
    }
  }

  public static String getMessageByLocale(String property) {
    return bundle.getString(property);
  }
}
