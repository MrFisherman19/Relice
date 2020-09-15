package com.mrfisherman.relice.Service.Document;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.List;

public interface PdfService {

    byte[] saveByteArrayToPdf(byte[] bufferedImage) throws IOException, DocumentException;

    byte[] saveByteArraysToPdf(List<byte[]> bufferedImageList) throws DocumentException, IOException;

}
