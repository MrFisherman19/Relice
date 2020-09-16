package com.mrfisherman.relice.Service.Document;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.List;

public interface PdfService {

    byte[] saveByteArraysToPdf(List<byte[]> bufferedImageList) throws DocumentException, IOException;

}
