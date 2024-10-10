package com.gsdd.pcstock.controller;

import com.gsdd.file.util.ByteConverter;
import com.gsdd.pcstock.constants.PcStockGeneralConstants;
import com.gsdd.pcstock.constants.TableModelConstants;
import com.gsdd.pcstock.model.CompareFile;
import com.gsdd.pcstock.model.DetailedFile;
import com.gsdd.pcstock.model.Directory;
import com.gsdd.pcstock.model.GeneralFile;
import com.gsdd.pcstock.ui.model.CompareModel;
import com.gsdd.pcstock.ui.model.SeeDetailedModel;
import com.gsdd.pcstock.ui.model.SeeModel;
import com.gsdd.pcstock.util.DetailedFileDirectoryHelper;
import com.gsdd.pcstock.util.GeneralFileDirectoryHelper;
import com.gsdd.pcstock.util.PcStockLanguage;
import com.gsdd.pcstock.util.RenameUtil;
import com.gsdd.pcstock.view.PcStockView;
import java.awt.Color;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PcStockController {

  private static final String ADDING_LOG = "Adding --> {}";
  private final PcStockView view;
  private Directory directories;
  private String[] arrayFilter;
  private GeneralFileDirectoryHelper generalFileHelper;
  private DetailedFileDirectoryHelper detailedFileHelper;

  public void initWindow() {
    initVar();
    initComp();
  }

  public void initVar() {
    this.directories = new Directory();
    this.generalFileHelper = new GeneralFileDirectoryHelper();
    this.detailedFileHelper = new DetailedFileDirectoryHelper();
  }

  public void initComp() {
    setUpCompareView();
  }

  public void enableButtons(boolean b) {
    view.getMainDirectoryButton().setEnabled(b);
    view.getSecondaryDirectoryButton().setEnabled(b);
    view.getCompareButton().setEnabled(b);
    view.getSeeButton().setEnabled(b);
    view.getRenameButton().setEnabled(!b);
  }

  private void clearDataTable() {
    view.getDataTable().setVisible(true);
    view.getTableScroll().setVisible(true);
    view.getDataTable().removeAll();
  }

  public void setUpCompareView() {
    view.getCompareBackground().setVisible(true);
    view.getSeeBackground().setVisible(false);
    view.getRenameBackground().setVisible(false);
    view.getRenameCheck().setSelected(false);
    view.getSeeCheck().setSelected(false);
    view.getCompareCheck().setSelected(true);
    view.getDetailCheck().setSelected(false);
    view.getResolution().setSelected(false);
    view.getDetailCheck().setVisible(true);
    view.getResolution().setVisible(false);
    view.getMainDirectoryButton().setVisible(true);
    view.getSecondaryDirectoryButton().setVisible(true);
    view.getCompareButton().setEnabled(true);
    view.getCompareButton().setVisible(true);
    view.getSeeButton().setVisible(false);
    view.getRenameButton().setVisible(false);
    clearDataTable();
  }

  public void setUpSeeView() {
    view.getCompareBackground().setVisible(false);
    view.getSeeBackground().setVisible(true);
    view.getRenameBackground().setVisible(false);
    view.getRenameCheck().setSelected(false);
    view.getSeeCheck().setSelected(true);
    view.getCompareCheck().setSelected(false);
    view.getDetailCheck().setSelected(false);
    view.getResolution().setSelected(false);
    view.getDetailCheck().setVisible(true);
    view.getResolution().setVisible(true);
    view.getMainDirectoryButton().setVisible(false);
    view.getSecondaryDirectoryButton().setVisible(false);
    view.getCompareButton().setVisible(false);
    view.getSeeButton().setEnabled(true);
    view.getSeeButton().setVisible(true);
    view.getRenameButton().setVisible(false);
    clearDataTable();
  }

  public void setUpRenameView() {
    view.getCompareBackground().setVisible(false);
    view.getSeeBackground().setVisible(false);
    view.getRenameBackground().setVisible(true);
    view.getRenameCheck().setSelected(true);
    view.getSeeCheck().setSelected(false);
    view.getCompareCheck().setSelected(false);
    view.getDetailCheck().setSelected(false);
    view.getResolution().setSelected(false);
    view.getDetailCheck().setVisible(false);
    view.getResolution().setVisible(false);
    view.getMainDirectoryButton().setVisible(false);
    view.getSecondaryDirectoryButton().setVisible(false);
    view.getCompareButton().setVisible(false);
    view.getSeeButton().setVisible(false);
    view.getRenameButton().setEnabled(true);
    view.getRenameButton().setVisible(true);
    view.getDataTable().setVisible(false);
    view.getTableScroll().setVisible(false);
  }

  public String getUserInput(String title, String msj) {
    String str;
    do {
      str = JOptionPane.showInputDialog(null, msj, title, 1);
    } while (str == null || str.trim().isEmpty());
    return str;
  }

  public void getFilters(boolean flag) {
    String str =
        getUserInput(
            PcStockLanguage.getMessageByLocale(PcStockLanguage.ADD_FILTERS_TXT),
            PcStockLanguage.getMessageByLocale(PcStockLanguage.ADD_FILTERS_DESC_TXT));
    if (str != null) {
      arrayFilter = str.split(PcStockGeneralConstants.COMMA);
      enableButtons(flag);
    } else {
      JOptionPane.showMessageDialog(
          null,
          PcStockLanguage.getMessageByLocale(PcStockLanguage.ADD_FILTERS_ERR_TXT),
          PcStockGeneralConstants.ERROR,
          JOptionPane.ERROR_MESSAGE);
      enableButtons(false);
    }
  }

  public String getDirectory(String msg) {
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File(PcStockGeneralConstants.ROOT_DIR));
    chooser.setDialogTitle(msg);
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setAcceptAllFileFilterUsed(false);
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      log.info("Directory: {}", chooser.getSelectedFile().getAbsolutePath());
      return chooser.getSelectedFile().getAbsolutePath();
    } else {
      return PcStockGeneralConstants.EMPTY;
    }
  }

  public void mainDirectoryAction(String msg) {
    String dir = getCheckedDirectory(msg);
    view.getMainDirectoryLabel().setText(dir);
    directories.setMain(new File(dir));
  }

  public void secondaryDirectoryAction(String msj) {
    String dir = getCheckedDirectory(msj);
    view.getSecondaryDirectoryLabel().setText(dir);
    directories.setSecondary(new File(dir));
  }

  private String getCheckedDirectory(String message) {
    String dir;
    do {
      dir = getDirectory(message);
    } while (dir == null || dir.trim().isEmpty());
    return dir;
  }

  private void setUpTablePerModel(TableModel model) {
    view.getDataTable().removeAll();
    view.getDataTable().setAutoCreateRowSorter(true);
    view.getDataTable().setModel(model);
    view.getDataTable().setGridColor(new java.awt.Color(0, 0, 0));
    view.getDataTable().setSelectionBackground(new java.awt.Color(255, 255, 255));
    view.getDataTable().setSelectionForeground(new java.awt.Color(153, 153, 153));
  }

  public void changeTableModel(String action) {
    switch (action) {
      case TableModelConstants.COMPARE:
      case TableModelConstants.DETAILED_COMPARE:
        setUpTablePerModel(new CompareModel(new Object[][] {}, CompareModel.getColumns()));
        break;
      case TableModelConstants.SEE:
        setUpTablePerModel(new SeeModel(new Object[][] {}, SeeModel.getColumns()));
        break;
      case TableModelConstants.DETAILED_SEE:
        setUpTablePerModel(
            new SeeDetailedModel(
                new Object[][] {}, SeeDetailedModel.getColumns(view.getResolution().isSelected())));
        break;
      default:
        break;
    }
  }

  public void executeSeeAction() {
    getCheckedFilters();
    mainDirectoryAction(PcStockLanguage.getMessageByLocale(PcStockLanguage.SELECT_MAIN_DIR_TXT));
    if (view.getDetailCheck().isSelected()) {
      seeWithDetails();
    } else {
      seeWithoutDetails();
    }
  }

  private void seeWithDetails() {
    changeTableModel(TableModelConstants.DETAILED_SEE);
    detailedFileHelper.setWithResolution(view.getResolution().isSelected());
    List<DetailedFile> searchList =
        detailedFileHelper.getFileList(arrayFilter, directories.getMain());
    DefaultTableModel model = (DefaultTableModel) view.getDataTable().getModel();
    clearTable(model);
    int pos = 0;
    for (DetailedFile dto : searchList) {
      model.addRow(new Object[1]);
      view.getDataTable().getModel().setValueAt(dto.getName(), pos, 0);
      view.getDataTable()
          .getModel()
          .setValueAt(ByteConverter.readableFileSize(dto.getSize()), pos, 1);
      if (detailedFileHelper.isWithResolution()) {
        view.getDataTable().getModel().setValueAt(dto.getResolution(), pos, 2);
      }
      log.debug(ADDING_LOG, dto);
      pos++;
    }
  }

  private void seeWithoutDetails() {
    changeTableModel(TableModelConstants.SEE);
    List<GeneralFile> searchList =
        generalFileHelper.getFileList(arrayFilter, directories.getMain());
    DefaultTableModel model = (DefaultTableModel) view.getDataTable().getModel();
    clearTable(model);
    int pos = 0;
    for (GeneralFile dto : searchList) {
      model.addRow(new Object[1]);
      view.getDataTable().getModel().setValueAt(dto.getName(), pos, 0);
      view.getDataTable().getModel().setValueAt(dto.getQuantity(), pos, 1);
      log.debug(ADDING_LOG, dto);
      pos++;
    }
  }

  private void getCheckedFilters() {
    while (arrayFilter == null || arrayFilter.length == 0) {
      getFilters(true);
    }
  }

  public void executeCompareAction() {
    getCheckedFilters();
    if (view.getDetailCheck().isSelected()) {
      compareWithDetail();
    } else {
      compareWithoutDetail();
    }
  }

  private void compareWithDetail() {
    changeTableModel(TableModelConstants.COMPARE);
    List<CompareFile> searchList =
        detailedFileHelper.getCompareFileList(
            arrayFilter, directories.getMain(), directories.getSecondary());
    DefaultTableModel model = (DefaultTableModel) view.getDataTable().getModel();
    clearTable(model);
    int pos = 0;
    for (CompareFile dto : searchList) {
      model.addRow(new Object[1]);
      String ppal = ByteConverter.readableFileSize(dto.getOnMain());
      String snd = ByteConverter.readableFileSize(dto.getOnSecondary());
      view.getDataTable().getModel().setValueAt(dto.getName(), pos, 0);
      view.getDataTable().getModel().setValueAt(ppal, pos, 1);
      view.getDataTable().getModel().setValueAt(snd, pos, 2);
      view.getDataTable().getModel().setValueAt(dto.getDifferent(), pos, 3);
      log.debug(ADDING_LOG, dto);
      pos++;
    }
  }

  private void compareWithoutDetail() {
    changeTableModel(TableModelConstants.COMPARE);
    List<CompareFile> searchList =
        generalFileHelper.getCompareFileList(
            arrayFilter, directories.getMain(), directories.getSecondary());
    DefaultTableModel model = (DefaultTableModel) view.getDataTable().getModel();
    clearTable(model);
    int pos = 0;
    for (CompareFile dto : searchList) {
      model.addRow(new Object[1]);
      view.getDataTable().getModel().setValueAt(dto.getName(), pos, 0);
      view.getDataTable().getModel().setValueAt(dto.getOnMain(), pos, 1);
      view.getDataTable().getModel().setValueAt(dto.getOnSecondary(), pos, 2);
      view.getDataTable().getModel().setValueAt(dto.getDifferent(), pos, 3);
      log.debug(ADDING_LOG, dto);
      pos++;
    }
  }

  private void clearTable(DefaultTableModel model) {
    int tam = model.getRowCount();
    for (int a = 0; a < tam; a++) {
      model.removeRow(model.getRowCount() - 1);
    }
    view.getDataTable().setBackground(Color.LIGHT_GRAY);
  }

  public void executeRenameAction() {
    getCheckedFilters();
    mainDirectoryAction(PcStockLanguage.getMessageByLocale(PcStockLanguage.RENAME_SELECT_DIR_TXT));
    String str =
        getUserInput(
            PcStockLanguage.getMessageByLocale(PcStockLanguage.RENAME_TITLE_TXT),
            PcStockLanguage.getMessageByLocale(PcStockLanguage.RENAME_MSG_TXT));
    boolean b = RenameUtil.renameAllFilesOnDir(directories.getMain(), arrayFilter, str);
    if (b) {
      JOptionPane.showMessageDialog(
          null,
          PcStockLanguage.getMessageByLocale(PcStockLanguage.RENAME_SUCCESS_TXT),
          PcStockLanguage.getMessageByLocale(PcStockLanguage.SUCCESS_TXT),
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          null,
          PcStockLanguage.getMessageByLocale(PcStockLanguage.RENAME_FAIL_TXT),
          PcStockGeneralConstants.ERROR,
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
