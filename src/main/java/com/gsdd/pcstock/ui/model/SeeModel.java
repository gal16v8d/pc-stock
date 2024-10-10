package com.gsdd.pcstock.ui.model;

import com.gsdd.pcstock.constants.TableModelConstants;
import java.io.Serial;
import javax.swing.table.DefaultTableModel;

public class SeeModel extends DefaultTableModel {

  @Serial private static final long serialVersionUID = 2373032138268517497L;

  private static final Class<?>[] TYPES = new Class[] {Object.class, Object.class};

  private static final String[] COLUMNS = {TableModelConstants.NAME, TableModelConstants.QUANTITY};

  public SeeModel(Object[][] data, Object[] columnNames) {
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
