package org.example.pdfbox;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.IOException;
import java.util.List;

public class PdfStripperExt extends PDFTextStripper {


    public static final String UNIQ_COORDINATE_PDF_SEPARATOR = "bp5zl";
    boolean startOfLine = true;

    @Override
    protected void startPage(PDPage page) throws IOException {
        startOfLine = true;
        super.startPage(page);
    }

    @Override
    protected void writeLineSeparator() throws IOException {
        startOfLine = true;
//        super.writeLineSeparator();
    }

    @Override
    protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
        StringBuilder newText = new StringBuilder();
//        if (startOfLine) {

            for (int i = 0; i < text.length(); i++) {

                newText.append(String.format(UNIQ_COORDINATE_PDF_SEPARATOR + "[%s - %s]" + UNIQ_COORDINATE_PDF_SEPARATOR, textPositions.get(i).getXDirAdj(), textPositions.get(i).getYDirAdj()));
                newText.append(text.charAt(i));

//                writeString(newText.toString());

            }
//            TextPosition firstPosition = textPositions.get(0);
//            TextPosition first2Position = textPositions.get(1);
//
//            TextPosition lastPosition = textPositions.get(textPositions.size() - 1);
//            writeString(String.format(UNIQ_COORDINATE_PDF_SEPARATOR +"[%s - %s]"+ UNIQ_COORDINATE_PDF_SEPARATOR, firstPosition.getXDirAdj(), firstPosition.getYDirAdj()));
//            writeString(String.format(UNIQ_COORDINATE_PDF_SEPARATOR +"[%s - %s]"+ UNIQ_COORDINATE_PDF_SEPARATOR, first2Position.getXDirAdj(), first2Position.getYDirAdj()));
//            startOfLine = false;
//        }
//        super.writeString(text, textPositions);
        super.writeString( newText.toString().replaceAll("\\n", ""),textPositions);
    }


}
