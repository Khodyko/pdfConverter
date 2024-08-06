package org.example;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.example.itext.TextExtractionStrategyImpl;
import org.example.pdfbox.CoordinateParser;
import org.example.pdfbox.PdfStripperExt;
import org.example.pdfbox.PhraseCoordinate;
import org.example.util.FileWriterUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainPdfReadByItext {

    public static void main(String[] args) {
        String filePath = "/work/learn/inhcheck/src/main/resources/pdf/f0401060.421060.048142001.10072024.pdf";
//        String filePath = "/work/learn/inhcheck/src/main/resources/pdf/f1335ПСД.421335.048142001.10072024.pdf";
        String outPutFileName= "/work/learn/inhcheck/src/main/resources/file/pdfCheck_itext.txt";
        File pdfFile = new File(filePath);
        String text = null;
        PdfReader reader = null;
        try {
            reader = new PdfReader(filePath);
            for (int i = 1; i < reader.getNumberOfPages(); i++) {
                TextExtractionStrategyImpl strategy = new TextExtractionStrategyImpl();

                text = PdfTextExtractor.getTextFromPage(reader, i, strategy);
                for (Pair<Float, String> pair: strategy.getStringsWithCoordinates()) {
                    System.out.println(pair.getKey().toString() + " " + pair.getValue());
                }
//                FileWriterUtil.writeToFile(outPutFileName,"--------------------НОВАЯ СТРАНИЦА-------------------\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(reader!=null){
            reader.close();
            }
        }
//        System.out.println(phraseCoordinates.stream().map(a -> a.getPhrase()).collect(Collectors.toList()));
//            System.out.println(coordinateParser.getStringsOfPhrases(phraseStrings));
        System.out.println(text);

    }



}
