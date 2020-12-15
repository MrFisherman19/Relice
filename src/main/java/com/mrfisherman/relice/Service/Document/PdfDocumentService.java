package com.mrfisherman.relice.Service.Document;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

public interface PdfDocumentService {

    PdfPTable createTable(int columns);

    Paragraph createLabel(String text);

    Paragraph createLabel(Font font, String text);

    void addTableHeaders(PdfPTable table, String[] headersNames, BaseColor headerColor);

    default Font createFont(String fontStyle, int size, BaseColor color) {
        return FontFactory.getFont(fontStyle, size, color);
    }

}
