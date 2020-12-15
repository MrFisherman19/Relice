package com.mrfisherman.relice.Controller;

import com.itextpdf.text.DocumentException;
import com.mrfisherman.relice.Service.Reports.OfficeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Qualifier("AssetConditionReport")
    private final OfficeReport assetsConditionReport;

    @Qualifier("AssetLocationReport")
    private final OfficeReport assetsLocationReport;

    @Qualifier("FixedAssetInventoryReport")
    private final OfficeReport fixedAssetsInventoryReport;

    public ReportController(OfficeReport assetsConditionReport, OfficeReport assetsLocationReport, OfficeReport fixedAssetsInventoryReport) {
        this.assetsConditionReport = assetsConditionReport;
        this.assetsLocationReport = assetsLocationReport;
        this.fixedAssetsInventoryReport = fixedAssetsInventoryReport;
    }

    @RequestMapping(value = "/getAssetsConditionReport", method = RequestMethod.GET)
    public ResponseEntity<?> getAssetConditionReport() throws IOException, DocumentException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition", "inline; filename=officeConditionReport.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(assetsConditionReport.getFinalReport());
    }

    @RequestMapping(value = "/getAssetsLocationReport", method = RequestMethod.GET)
    public ResponseEntity<?> getAssetLocationReport() throws IOException, DocumentException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition", "inline; filename=officeLocationReport.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(assetsLocationReport.getFinalReport());
    }



}
