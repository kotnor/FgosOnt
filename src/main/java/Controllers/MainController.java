package controllers;

import model.ResMastering;
import utils.StringUtils;
import view.MainView;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;

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
            allPages.append(PdfTextExtractor.getTextFromPage(reader, j))
                    .append("\n");
        }

        String allPagesString = allPages.toString();

        int startIndex = allPagesString.indexOf(ResMastering.KEY_NAME); // + ResMastering.KEY_NAME.length();
        int pointNumber = StringUtils.nextNumber(textFromStartPage, startIndex);
        String endBlock = (pointNumber + 1) + ".1";
        String block = allPagesString.substring(startIndex, allPagesString.indexOf(endBlock));

        return block;
    }

    public MainController(MainView view) {
        this.mainView = view;
    }

    public void startParsing(String path) {
        mainView.startParsing();
        try {
            reader = new PdfReader(path);

            //System.out.println(textFromPage);
            ResMastering resMastering = new ResMastering(getResMasteringBlock());
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        mainView.endParsing();
        /*
         * Какие-то действия.
         */
    }
}
