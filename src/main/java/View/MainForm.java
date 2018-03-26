package view;

import controllers.MainController;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainForm extends JFrame implements MainView{
    private JPanel choseFgosPanel;
    private JLabel lbFgosName;
    private JButton btnChoseFile;
    private JButton btnContinue;
    private JProgressBar pbParsing;
    private MainController mainController;
    private File currentFile;

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
        setSize(400, 400);
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

        JMenuItem openItem = new JMenuItem("Открыть");
        openItem.setFont(font);
        fileMenu.add(openItem);

        JMenuItem closeItem = new JMenuItem("Закрыть");
        closeItem.setFont(font);
        fileMenu.add(closeItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.setFont(font);
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return fileMenu;
    }

    private JMenu createHelpMenu(Font font) {
        JMenu helpMenu = new JMenu("Справка");
        helpMenu.setFont(font);

        JMenu newMenu = new JMenu("О программе");
        newMenu.setFont(font);
        helpMenu.add(newMenu);

        return helpMenu;
    }

    public void startParsing() {
        System.out.println("MainForm start parsing");
        pbParsing.setVisible(true);
        pbParsing.setValue(20);
    }

    public void endParsing() {
        System.out.println("MainForm end parsing");
        pbParsing.setValue(100);
    }


}
