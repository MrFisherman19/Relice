package com.mrfisherman.relice.Service.Document;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {

    private static final int PDF_TABLE_COLUMNS = 4;
    private static final int PDF_CELL_IMAGE_SCALE = 25;
    private static final Rectangle PAGE_SIZE = PageSize.A4;

    @Override
    public byte[] saveByteArraysToPdf(List<byte[]> byteArrays) throws DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document(PAGE_SIZE, 0.75F, 0.75F, 0.75F, 0.75F);

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        PdfPTable table = new PdfPTable(PDF_TABLE_COLUMNS);
        table.setPaddingTop(1F);
        byteArrays.forEach(x->{
            try {
                Image image = Image.getInstance(x);
                image.scalePercent(PDF_CELL_IMAGE_SCALE);
                addImageInRows(image, table);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        });
        table.completeRow();
        document.add(table);
        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    private void addImageInRows(Image image, PdfPTable table) {
        PdfPCell cell = new PdfPCell(image);
        cell.setPadding(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

}
