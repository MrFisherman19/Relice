package com.mrfisherman.relice.Service.Reports;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class AssetLocationReport implements OfficeReport {

    @Override
    public byte[] getFinalReport() throws IOException, DocumentException {
        return new byte[0];
    }

}
