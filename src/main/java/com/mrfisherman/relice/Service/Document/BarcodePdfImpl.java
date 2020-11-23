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
public class BarcodePdfImpl implements PdfService {

    private static final int PDF_TABLE_COLUMNS = 5;
    private static final int PDF_CELL_IMAGE_SCALE = 18;
    private static final float CEL_PADDING_TOP = 6.4f;
    private static final int CEL_PADDING_BOTTOM = 6;
    private static final int TABLE_WIDTH_PERCENTAGE = 104;
    private static final int BORDER = Rectangle.NO_BORDER;
    private static final int HORIZONTAL_ALIGN = Element.ALIGN_CENTER;

    private static final Rectangle PAGE_SIZE = PageSize.A4;

    public byte[] saveDataToPdf(List<byte[]> barcodes) throws DocumentException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        generateDocument(barcodes, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    private void generateDocument(List<byte[]> barcodes, ByteArrayOutputStream byteArrayOutputStream) throws DocumentException {
        Document document = new Document(PAGE_SIZE);

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        PdfPTable table = new PdfPTable(PDF_TABLE_COLUMNS);
        table.setWidthPercentage(TABLE_WIDTH_PERCENTAGE);
        barcodes.forEach(x->{
            try {
                Image image = Image.getInstance(x);
                image.scalePercent(PDF_CELL_IMAGE_SCALE);
                addImageToRow(image, table);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        });
        table.completeRow();

        document.add(table);
        document.close();
    }

    private void addImageToRow(Image image, PdfPTable table) {
        PdfPCell cell = configureCell(new PdfPCell(image));
        table.addCell(cell);
    }

    private PdfPCell configureCell(PdfPCell cell) {

        cell.setBorder(BORDER);
        cell.setPaddingTop(CEL_PADDING_TOP);
        cell.setPaddingBottom(CEL_PADDING_BOTTOM);
        cell.setHorizontalAlignment(HORIZONTAL_ALIGN);

        return cell;
    }

}
