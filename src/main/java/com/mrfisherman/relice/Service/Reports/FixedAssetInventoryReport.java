package com.mrfisherman.relice.Service.Reports;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class FixedAssetInventoryReport implements OfficeReport {

    @Override
    public byte[] getFinalReport() throws IOException, DocumentException {
        return new byte[0];
    }
}
