package view;

import controllers.MainController;
import model.Fgos;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainForm extends JFrame implements MainView {
    private JPanel choseFgosPanel;
    private JLabel lbFgosName;
    private JButton btnChoseFile;
    private JButton btnContinue;
    private JProgressBar pbParsing;
    private MainController mainController;
    private File currentFile;
    private Fgos fgos;

    public MainForm() {
        mainController = new MainController(this);
        initUI();
        btnChoseFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
                fileopen.setFileFilter(filter);
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    currentFile = fileopen.getSelectedFile();
                    lbFgosName.setText("Файл: " + currentFile.getName());
                }
            }
        });
        btnContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentFile != null) {
                    mainController.startParsing(currentFile.getPath());
                }
            }
        });
    }

    private void initUI() {
        setSize(600, 600);
        setName("Ontology FGOS");
        setTitle("Онтология ФГОС");

        setContentPane(choseFgosPanel);
        pbParsing.setVisible(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        Font font = new Font("Verdana", Font.PLAIN, 11);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu(font));
        menuBar.add(createHelpMenu(font));
        return menuBar;
    }

    private JMenu createFileMenu(Font font) {
        JMenu fileMenu = new JMenu("Файл");
        fileMenu.setFont(font);

        JMenuItem openItem = new JMenuItem("Открыть ФГОС");
        openItem.setFont(font);
        fileMenu.add(openItem);

        JMenuItem saveFgosItem = new JMenuItem("Сохранить ФГОС");
        saveFgosItem.setFont(font);
        fileMenu.add(saveFgosItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.setFont(font);
        fileMenu.add(exitItem);

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("FgosOnt files", "fgosont");
                fileopen.setFileFilter(filter);
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    try {
                        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                        Fgos fgos = (Fgos) in.readObject();
                        openFgos(fgos);
                    } catch (Exception ex) {
                    }
                }
            }
        });

        if (fgos != null) {
            saveFgosItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileSave = new JFileChooser();
                    FileFilter filter = new FileNameExtensionFilter("FgosOnt files", "fgosont");
                    fileSave.setFileFilter(filter);
                    int ret = fileSave.showSaveDialog(null);
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        try {
                            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileSave.getSelectedFile()));
                            out.writeObject(fgos);
                            out.close();
                        } catch (IOException ex) {
                        }
                    }
                }
            });
        }

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return fileMenu;
    }

    private void openFgos(Fgos fgos) {
        dispose();
        new ConfirmForm(fgos);
    }

    private JMenu createHelpMenu(Font font) {
        JMenu helpMenu = new JMenu("Справка");
        helpMenu.setFont(font);

        JMenuItem aboutItem = new JMenu("О программе");
        aboutItem.setFont(font);
        helpMenu.add(aboutItem);

        return helpMenu;
    }

    public void startParsing() {
        System.out.println("MainForm start parsing");
        pbParsing.setVisible(true);
        pbParsing.setValue(20);
    }

    public void endParsing(Fgos fgos) {
        System.out.println("MainForm end parsing");
        pbParsing.setValue(100);
//        setContentPane(new ConfirmForm(fgos));
        dispose();
        new ConfirmForm(fgos);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        choseFgosPanel = new JPanel();
        choseFgosPanel.setLayout(new GridBagLayout());
        choseFgosPanel.setBackground(new Color(-1));
        lbFgosName = new JLabel();
        lbFgosName.setText("Файл:");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        choseFgosPanel.add(lbFgosName, gbc);
        btnChoseFile = new JButton();
        btnChoseFile.setBackground(new Color(-1842205));
        btnChoseFile.setText("Выбрать файл");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        choseFgosPanel.add(btnChoseFile, gbc);
        btnContinue = new JButton();
        btnContinue.setBackground(new Color(-1842205));
        btnContinue.setEnabled(true);
        btnContinue.setText("Начать парсинг");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        choseFgosPanel.add(btnContinue, gbc);
        pbParsing = new JProgressBar();
        pbParsing.setForeground(new Color(-16719092));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        choseFgosPanel.add(pbParsing, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return choseFgosPanel;
    }
}
