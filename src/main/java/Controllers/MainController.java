package Controllers;

import View.MainView;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;

public class MainController {
    private MainView mainView;
    private PdfReader reader;

    public MainController(MainView view) {
        this.mainView = view;
    }

    public void startParsing(String path) {
        mainView.startParsing();
        try {
            reader = new PdfReader(path);
            // pageNumber = 1
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 5);
            System.out.println(textFromPage);
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
