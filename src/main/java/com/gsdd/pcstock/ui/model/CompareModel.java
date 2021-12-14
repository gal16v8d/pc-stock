package com.gsdd.pcstock.ui.model;

import javax.swing.table.DefaultTableModel;
import com.gsdd.pcstock.constants.TableModelConstants;

public class CompareModel extends DefaultTableModel {

  private static final long serialVersionUID = 2373032138268517497L;

  private static final Class<?>[] TYPES =
      new Class[] {Object.class, Object.class, Object.class, Boolean.class};

  private static final String[] COLUMNS = {TableModelConstants.NAME, TableModelConstants.C_MAIN,
      TableModelConstants.C_SND, TableModelConstants.C_DIF};

  public CompareModel(Object[][] data, Object[] columnNames) {
    super(data, columnNames);
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return TYPES[columnIndex];
  }

  public static String[] getColumns() {
    return COLUMNS;
  }

}
