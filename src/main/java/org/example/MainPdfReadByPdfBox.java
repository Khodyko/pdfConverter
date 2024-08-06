package org.example;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.example.pdfbox.CoordinateParser;
import org.example.pdfbox.PdfStripperExt;
import org.example.pdfbox.PhraseCoordinate;
import org.example.util.FileWriterUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainPdfReadByPdfBox {
    public static void main(String[] args) {
        String folderPath = "/work/learn/inhcheck/src/main/resources/ЭКР";
        String outputPathFolder = "/work/learn/inhcheck/src/main/resources/output";
        String outputFileExtension = ".txt";

        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();
        if (folderFiles != null) {
            for (File file : folderFiles) {
                String outPutFileName = file.getName().replace(".PDF", "");
                createTxtFile(file.getPath(), outputPathFolder +File.separator + outPutFileName + outputFileExtension);
            }
        }
    }


    public static void createTxtFile(String pdfPath, String outPutFilePath) {
        File pdfFile = new File(pdfPath);
        String text = null;
        PDDocument doc = null;

        List<PhraseCoordinate> phraseCoordinates = null;
        Map<Integer, List<PhraseCoordinate>> phraseStrings = null;
        CoordinateParser coordinateParser = new CoordinateParser();
        try {
            doc = Loader.loadPDF(pdfFile);
            PdfStripperExt pdfStripperExt = null;
            Integer pagesCount = doc.getPages().getCount();
            for (int i = 1; i < pagesCount+1; i++) {
                pdfStripperExt = new PdfStripperExt();
                pdfStripperExt.setStartPage(i);
                pdfStripperExt.setEndPage(i);
                text = pdfStripperExt.getText(doc);
                phraseCoordinates = coordinateParser.getPhraseCoordinates(text, PdfStripperExt.UNIQ_COORDINATE_PDF_SEPARATOR);
                phraseStrings = coordinateParser.getStringsOfPhrases(phraseCoordinates);

                FileWriterUtil.writeToFile(outPutFilePath, coordinateParser.getStringsOfPhrases(phraseStrings));
                FileWriterUtil.writeToFile(outPutFilePath, "--------------------НОВАЯ СТРАНИЦА-------------------\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(text);
    }

}
