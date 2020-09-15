package com.mrfisherman.relice.Service.Document;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public byte[] saveByteArrayToPdf(byte[] byteArray) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            Image image = Image.getInstance(byteArray);
            document.add(image);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public byte[] saveByteArraysToPdf(List<byte[]> byteArrays) throws DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        byteArrays.forEach(x->{
            try {
                Image image = Image.getInstance(x);
                image.scalePercent(20);
                document.add(image);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        });

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

}
