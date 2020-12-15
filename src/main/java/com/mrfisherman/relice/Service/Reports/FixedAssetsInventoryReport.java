package com.mrfisherman.relice.Service.Reports;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FixedAssetsInventoryReport implements OfficeReport {

    @Override
    public byte[] getFinalReport() throws IOException, DocumentException {
        return new byte[0];
    }
}
