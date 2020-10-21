package com.mrfisherman.relice.Service.Reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface OfficeReport {

    byte[] getFinalReport() throws DocumentException, IOException;

}
