package com.mrfisherman.relice.Service.Document;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class StandardPdfDocument implements PdfDocumentService {

    public PdfPTable createTable(int columns) {
        PdfPTable table = new PdfPTable(columns);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);
        return table;
    }

    public Paragraph createLabel(Font font, String text) {
        Chunk chunk = new Chunk(text, font);
        return createParagraph(chunk);
    }

    public Paragraph createLabel(String text) {
        Chunk chunk = new Chunk(text, PdfFont.STANDARD_LABEL_TEXT.getFont());
        return createParagraph(chunk);
    }

    private Paragraph createParagraph(Chunk chunk) {
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }

    public void addTableHeaders(PdfPTable table, String[] headersNames, BaseColor headerColor) {
        Arrays.stream(headersNames).forEach(headerName -> createHeaderCell(table, headerColor, headerName));
    }

    private void createHeaderCell(PdfPTable table, BaseColor headerColor, String headerName) {
        PdfPCell header = new PdfPCell();

        configureHeaderStyle(header, headerColor);

        header.addElement(createHeaderParagraph(headerName));

        table.addCell(header);
    }

    private void configureHeaderStyle(PdfPCell header, BaseColor headerColor) {
        header.setBackgroundColor(headerColor);
        header.setBorderWidth(1);
        header.setPadding(10);
        header.setPaddingTop(0);
    }

    private Paragraph createHeaderParagraph(String headerName) {
        Chunk chunk = new Chunk(headerName, PdfFont.HEADER_TEXT.getFont());
        return createParagraph(chunk);
    }
}
