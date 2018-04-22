package controllers;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.parser.*;
import model.Fgos;
import model.profactivity.ProfActivity;
import model.resmastering.ResMastering;
import model.reqtostructure.ReqToStructure;
import utils.StringUtils;
import view.MainView;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MainController {
    private MainView mainView;
    private PdfReader reader;

    private String getResMasteringBlock() throws IOException{
        int startPage = -1;
        String textFromStartPage = null;
        System.out.println(reader.getNumberOfPages());

        int i = 1;
        for (i = 1; i <= reader.getNumberOfPages(); i++) {
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
            textFromPage = getCleanPage(textFromPage);

            System.out.println("TextFromPage:" + textFromPage);
            if (textFromPage.contains(ResMastering.KEY_NAME)) {
                startPage = i;
                textFromStartPage = textFromPage;
                break;
            }
        }

        if (i > reader.getNumberOfPages()) {
            return null;
        }

        StringBuilder allPages = new StringBuilder();
        for (int j = i; j <= reader.getNumberOfPages(); j++) {
            allPages.append(getCleanPage(PdfTextExtractor.getTextFromPage(reader, j)))
                    .append("\n");
        }

        String allPagesString = allPages.toString();

        int startIndex = allPagesString.indexOf(ResMastering.KEY_NAME); // + ResMastering.KEY_NAME.length();
        int pointNumber = StringUtils.nextNumber(textFromStartPage, startIndex);
        String endBlock = (pointNumber + 1) + ".1";
        String block = allPagesString.substring(startIndex, allPagesString.indexOf(endBlock));

        return block;
    }

    private String getCleanPage(String textFromPage) {
        if (textFromPage.contains("Консультант") && textFromPage.contains(("надежная правовая поддержка"))) {
            int indexPoints = textFromPage.indexOf("стандарта");
            int indexConsultant = textFromPage.lastIndexOf("КонсультантПлюс");
            if (indexConsultant != -1) {
                textFromPage = textFromPage.substring(indexPoints + 13, textFromPage.lastIndexOf("КонсультантПлюс"));
            }
        } else {
            return textFromPage;
        }
        return textFromPage;
    }

    private String getReqToStructureBlock() throws IOException{
        int startPage = -1;
        String textFromStartPage = null;
        System.out.println(reader.getNumberOfPages());
        int i = 1;
        for (i = 1; i <= reader.getNumberOfPages(); i++) {
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
            textFromPage = getCleanPage(textFromPage);

            if (textFromPage.contains(ReqToStructure.KEY_NAME)) {
                startPage = i;
                textFromStartPage = textFromPage;
                break;
            }
        }

        if (i > reader.getNumberOfPages()) {
            return null;
        }

        StringBuilder allPages = new StringBuilder();
        for (int j = i; j <= reader.getNumberOfPages(); j++) {
            allPages.append(getCleanPage(PdfTextExtractor.getTextFromPage(reader, j)))
                    .append("\n");
        }

        String allPagesString = allPages.toString();

        int startIndex = allPagesString.indexOf(ReqToStructure.KEY_NAME); // + ResMastering.KEY_NAME.length();
        int pointNumber = StringUtils.nextNumber(textFromStartPage, startIndex);
        String endBlock = (pointNumber + 1) + ".1";
        String block = allPagesString.substring(startIndex, allPagesString.indexOf(endBlock));

        return block;
    }

    private String getProfActivityBlock() throws IOException{
        int startPage = -1;
        String textFromStartPage = null;
        System.out.println(reader.getNumberOfPages());
        int i = 1;
        for (i = 1; i <= reader.getNumberOfPages(); i++) {
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
            textFromPage = getCleanPage(textFromPage);

            if (textFromPage.contains(ProfActivity.KEY_NAME)) {
                startPage = i;
                textFromStartPage = textFromPage;
                break;
            }
        }

        if (i > reader.getNumberOfPages()) {
            return null;
        }

        StringBuilder allPages = new StringBuilder();
        for (int j = i; j <= reader.getNumberOfPages(); j++) {
            allPages.append(getCleanPage(PdfTextExtractor.getTextFromPage(reader, j)))
                    .append("\n");
        }

        String allPagesString = allPages.toString();

        int startIndex = allPagesString.indexOf("4.1"); // + ResMastering.KEY_NAME.length();
        String block = allPagesString.substring(startIndex, allPagesString.indexOf("5.1"));

        return block;
    }

    public MainController(MainView view) {
        this.mainView = view;
    }

    public void startParsing(String path) {
        mainView.startParsing();
        Fgos fgos = new Fgos();
        try {
            reader = new PdfReader(path);
            ResMastering resMastering = new ResMastering(getResMasteringBlock());
            ReqToStructure reqToStructure = new ReqToStructure(getReqToStructureBlock());
            ProfActivity profActivity = new ProfActivity(getProfActivityBlock());
            fgos.resMastering = resMastering;
            fgos.reqToStructure = reqToStructure;
            fgos.profActivity = profActivity;
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Ontology ontology = new Ontology();
        ontology.setResMastering(fgos.resMastering);
        ontology.setReqToStructure(fgos.reqToStructure);
        ontology.setProfActivity(fgos.profActivity);
        mainView.endParsing();
        /*
         * Какие-то действия.
         */
    }
}
