package com.mrfisherman.relice.Controller;

import com.itextpdf.text.DocumentException;
import com.mrfisherman.relice.Service.Reports.OfficeReport;
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

    private final OfficeReport officeReport;

    public ReportController(OfficeReport officeReport) {
        this.officeReport = officeReport;
    }

    @RequestMapping(value = "/getAssetsConditionReport", method = RequestMethod.GET)
    public ResponseEntity<?> getAssetConditionReport() throws IOException, DocumentException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition", "inline; filename=officeConditionReport.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(officeReport.getFinalReport());
    }

    @RequestMapping(value = "/getAssetLocationReport", method = RequestMethod.GET)
    public ResponseEntity<?> getAssetLocationReport() throws IOException, DocumentException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition", "inline; filename=officeLocationReport.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(officeReport.getFinalReport());
    }



}
