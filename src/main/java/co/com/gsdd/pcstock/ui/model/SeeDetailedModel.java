package co.com.gsdd.pcstock.ui.model;

import javax.swing.table.DefaultTableModel;

import co.com.gsdd.pcstock.constants.TableModelConstants;

public class SeeDetailedModel extends DefaultTableModel {

	private static final long serialVersionUID = 2373032138268517497L;

	private static final Class<?>[] TYPES = new Class[] { Object.class, Object.class, Object.class };
	private static final String[] COLUMNS_RES = { TableModelConstants.NAME, TableModelConstants.SIZE,
			TableModelConstants.RES };
	private static final String[] COLUMNS = { TableModelConstants.NAME, TableModelConstants.SIZE };

	public SeeDetailedModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return TYPES[columnIndex];
	}

	public static String[] getColumns(boolean withResolution) {
		return withResolution ? COLUMNS_RES : COLUMNS;
	}

}
