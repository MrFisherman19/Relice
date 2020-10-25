package com.mrfisherman.relice.Service.Document;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;
import java.util.List;
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
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }

    public void addTableHeaders(PdfPTable table, String[] headersNames, BaseColor headerColor) {
        Arrays.stream(headersNames).forEach(headerName -> createHeaderCell(table, headerColor, headerName));
    }

    private void createHeaderCell(PdfPTable table, BaseColor headerColor, String headerName) {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(headerColor);
        header.setBorderWidth(1);
        Chunk chunk = new Chunk(headerName);

        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        chunk.setFont(createFont(FontFactory.TIMES_BOLD, 14, PdfColor.BASIC_WHITE.get()));
        header.addElement(paragraph);
        header.setPadding(10);
        header.setPaddingTop(0);
        table.addCell(header);
    }
}
