package com.mrfisherman.relice.Service.Document;
import com.itextpdf.text.DocumentException;
import java.util.List;

public interface PdfService  {

    byte[] saveDataToPdf(List<byte[]> images) throws DocumentException;

}
