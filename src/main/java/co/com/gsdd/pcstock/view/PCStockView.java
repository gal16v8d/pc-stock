package co.com.gsdd.pcstock.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

import co.com.gsdd.pcstock.controller.PCStockController;
import co.com.gsdd.pcstock.ui.model.CompareModel;
import co.com.gsdd.pcstock.util.PCStockLanguage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PCStockView extends JFrame {

    private static final long serialVersionUID = -1504352262642886494L;
    private static final String ARIAL_BLACK = "Arial Black";
    private static PCStockController controller;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton compareButton;
    private JButton seeButton;
    private JButton renameButton;
    private JButton mainDirectoryButton;
    private JButton secondaryDirectoryButton;
    private JCheckBox renameCheck;
    private JCheckBox seeCheck;
    private JCheckBox compareCheck;
    private JCheckBox detailCheck;
    private JCheckBox resolution;
    private JLabel compareBackground;
    private JLabel seeBackground;
    private JLabel renameBackground;
    private JLabel mainDirectoryLabel;
    private JLabel secondaryDirectoryLabel;
    private JTable dataTable;
    private JScrollPane tableScroll;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form InventarioPCFrame
     */
    private PCStockView() {
        PCStockLanguage.initBundle(getLocale());
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        mainDirectoryButton = new JButton();
        secondaryDirectoryButton = new JButton();
        mainDirectoryLabel = new JLabel();
        secondaryDirectoryLabel = new JLabel();
        compareButton = new JButton();
        tableScroll = new JScrollPane();
        dataTable = new JTable();
        compareCheck = new JCheckBox();
        renameCheck = new JCheckBox();
        seeCheck = new JCheckBox();
        detailCheck = new JCheckBox();
        resolution = new JCheckBox();
        seeButton = new JButton();
        renameButton = new JButton();
        compareBackground = new JLabel();
        seeBackground = new JLabel();
        renameBackground = new JLabel();
        Font arialBlack10 = new Font(ARIAL_BLACK, 0, 10);
        Font arialBlack12 = new Font(ARIAL_BLACK, 0, 12);
        Color redRGB = new Color(255, 0, 51);

        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/cfg/frame_icon.png")));
            setIconImage(icon.getImage());
        } catch (Exception e) {
            log.error("Error setting frame icon", e);
        }
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(750, 670));
        setResizable(false);
        getContentPane().setLayout(null);

        mainDirectoryButton.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.MAIN_DIR_BTN_TXT));
        mainDirectoryButton.addActionListener(this::botonPrincipalActionPerformed);
        getContentPane().add(mainDirectoryButton);
        mainDirectoryButton.setBounds(200, 90, 160, 23);

        secondaryDirectoryButton.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.SEC_DIR_BTN_TXT));
        secondaryDirectoryButton.addActionListener(this::botonSecundarioActionPerformed);
        getContentPane().add(secondaryDirectoryButton);
        secondaryDirectoryButton.setBounds(200, 120, 160, 23);

        mainDirectoryLabel.setFont(arialBlack12);
        mainDirectoryLabel.setForeground(redRGB);
        getContentPane().add(mainDirectoryLabel);
        mainDirectoryLabel.setBounds(370, 90, 250, 20);

        secondaryDirectoryLabel.setFont(arialBlack12);
        secondaryDirectoryLabel.setForeground(redRGB);
        getContentPane().add(secondaryDirectoryLabel);
        secondaryDirectoryLabel.setBounds(380, 160, 250, 20);

        dataTable.setAutoCreateRowSorter(true);
        dataTable.setModel(new CompareModel(new Object[][] {}, CompareModel.getColumns()));
        dataTable.setGridColor(new Color(0, 0, 0));
        dataTable.setSelectionBackground(new Color(255, 255, 255));
        dataTable.setSelectionForeground(new Color(153, 153, 153));
        tableScroll.setViewportView(dataTable);
        
        seeCheck.setFont(arialBlack10);
        seeCheck.setForeground(redRGB);
        seeCheck.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.SEE_BTN_TXT));
        seeCheck.addActionListener(this::prepareSeeView);
        getContentPane().add(seeCheck);
        seeCheck.setBounds(20, 10, 140, 23);

        compareCheck.setFont(arialBlack10);
        compareCheck.setForeground(redRGB);
        compareCheck.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.COMPARE_BTN_TXT));
        compareCheck.addActionListener(this::prepareCompareView);
        getContentPane().add(compareCheck);
        compareCheck.setBounds(20, 40, 140, 23);

        renameCheck.setFont(arialBlack10);
        renameCheck.setForeground(new Color(255, 0, 51));
        renameCheck.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.RENAME_BTN_TXT));
        renameCheck.addActionListener(this::prepareRenameView);
        getContentPane().add(renameCheck);
        renameCheck.setBounds(20, 70, 140, 23);

        detailCheck.setFont(arialBlack10);
        detailCheck.setForeground(redRGB);
        detailCheck.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.DETAIL_CHK_TXT));
        getContentPane().add(detailCheck);
        detailCheck.setBounds(20, 120, 140, 23);
        
        resolution.setFont(arialBlack10);
        resolution.setForeground(redRGB);
        resolution.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.RES_CHK_TXT));
        getContentPane().add(resolution);
        resolution.setBounds(20, 150, 140, 23);

        seeButton.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.SEE_BTN_TXT));
        seeButton.addActionListener(this::botonVerActionPerformed);
        getContentPane().add(seeButton);
        seeButton.setBounds(20, 180, 160, 20);

        compareButton.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.COMPARE_BTN_TXT));
        compareButton.addActionListener(this::botonCompararActionPerformed);
        getContentPane().add(compareButton);
        compareButton.setBounds(20, 180, 160, 20);

        renameButton.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.RENAME_BTN_TXT));
        renameButton.addActionListener(this::botonRenombrarActionPerformed);
        getContentPane().add(renameButton);
        renameButton.setBounds(20, 180, 160, 20);
        
        getContentPane().add(tableScroll);
        tableScroll.setBounds(20, 210, 610, 359);

        addImageToBackground("/cfg/compare.jpg", compareBackground);
        addImageToBackground("/cfg/see.jpg", seeBackground);
        addImageToBackground("/cfg/rename.jpg", renameBackground);
        setJMenuBar(initMenuBar());
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addImageToBackground(String resource, JLabel label) {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(resource)));
            label.setIcon(icon); // NOI18N
            getContentPane().add(label);
            label.setBounds(0, 0, 770, 650);
        } catch (Exception e) {
            log.error("Can not add image to background", e);
        }
    }

    private JMenuBar initMenuBar() {
        JMenuBar menuBar = new javax.swing.JMenuBar();
        JMenu optionsMenu = new JMenu();
        optionsMenu.setText(PCStockLanguage.getMessageByLocale(PCStockLanguage.OPTION_MENU_TXT));

        JMenuItem compareMenu = initJMenuItem(PCStockLanguage.getMessageByLocale(PCStockLanguage.COMPARE_MENU_TXT),
                PCStockLanguage.getMessageByLocale(PCStockLanguage.COMPARE_MENU_TOOLTIP), '1', KeyEvent.VK_NUMPAD1);
        compareMenu.addActionListener(this::prepareCompareView);
        optionsMenu.add(compareMenu);
        optionsMenu.add(new JSeparator());

        JMenuItem seeMenu = initJMenuItem(PCStockLanguage.getMessageByLocale(PCStockLanguage.SEE_MENU_TXT),
                PCStockLanguage.getMessageByLocale(PCStockLanguage.SEE_MENU_TOOLTIP), '2', KeyEvent.VK_NUMPAD2);
        seeMenu.addActionListener(this::prepareSeeView);
        optionsMenu.add(seeMenu);
        optionsMenu.add(new JSeparator());

        JMenuItem renameMenu = initJMenuItem(PCStockLanguage.getMessageByLocale(PCStockLanguage.RENAME_MENU_TXT),
                PCStockLanguage.getMessageByLocale(PCStockLanguage.RENAME_MENU_TOOLTIP), '3', KeyEvent.VK_NUMPAD3);
        renameMenu.addActionListener(this::prepareRenameView);
        optionsMenu.add(renameMenu);

        menuBar.add(optionsMenu);
        return menuBar;
    }

    private JMenuItem initJMenuItem(String text, String toolTip, char mnemonic, int shortcut) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcut, InputEvent.ALT_MASK));
        menuItem.setMnemonic(mnemonic);
        menuItem.setText(text);
        menuItem.setToolTipText(toolTip);
        return menuItem;
    }

    private void botonPrincipalActionPerformed(ActionEvent evt) {
        controller.mainDirectoryAction(PCStockLanguage.getMessageByLocale(PCStockLanguage.SELECT_MAIN_DIR_TXT));
    }

    private void botonSecundarioActionPerformed(ActionEvent evt) {
        controller.secondaryDirectoryAction(PCStockLanguage.getMessageByLocale(PCStockLanguage.SELECT_SEC_DIR_TXT));
    }

    private void prepareCompareView(ActionEvent evt) {
        controller.setUpCompareView();
    }

    private void prepareRenameView(ActionEvent evt) {
        controller.setUpRenameView();
    }

    private void prepareSeeView(ActionEvent evt) {
        controller.setUpSeeView();
    }

    private void botonCompararActionPerformed(ActionEvent evt) {
        controller.executeCompareAction();
    }

    private void botonRenombrarActionPerformed(ActionEvent evt) {
        controller.executeRenameAction();
    }

    private void botonVerActionPerformed(ActionEvent evt) {
        controller.executeSeeAction();
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        try {
            Optional<LookAndFeelInfo> lookAndFeelInfo = Stream.of(UIManager.getInstalledLookAndFeels())
                    .filter(lookAndFeel -> "Nimbus".equals(lookAndFeel.getName())).findAny();
            UIManager.setLookAndFeel(lookAndFeelInfo.map(LookAndFeelInfo::getName)
                    .orElseGet(UIManager::getCrossPlatformLookAndFeelClassName));
        } catch (Exception e) {
            log.error("Can not set lookAndFeel", e);
        }

        /* Create and display the form */
        EventQueue.invokeLater(() -> {
            PCStockView gui = new PCStockView();
            gui.setVisible(true);
            gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            controller = new PCStockController(gui);
            controller.initWindow();
        });
    }

}
