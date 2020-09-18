package com.mrfisherman.relice.Controller;

import com.itextpdf.text.DocumentException;
import com.itextpdf.xmp.impl.Base64;
import com.mrfisherman.relice.Dto.Wrapper.BarcodeTextWrapper;
import com.mrfisherman.relice.Service.Barcode.BarcodeService;
import com.mrfisherman.relice.Service.Document.PdfService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/barcode")
public class BarcodeController {

    final BarcodeService barcodeService;
    final PdfService pdfService;

    public BarcodeController(BarcodeService barcodeService, PdfService pdfService) {
        this.barcodeService = barcodeService;
        this.pdfService = pdfService;
    }

    @GetMapping(value = "/generateBarcodeBase64Jpg")
    public byte[] generateBarcode(@RequestParam String prefix, @RequestParam Long id) {
        return Base64.encode(barcodeService.generateBarcodeCode128ByteArray(prefix + "-" + id));
    }

    @PostMapping(value = "/generateBarcodesPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateBarcodesPdf(@RequestBody BarcodeTextWrapper barcodeTexts) throws IOException, DocumentException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition", "inline; filename=barcodes.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfService.saveByteArraysToPdf(
                        barcodeService.generateListOfBarcodeCode128ByteArray(barcodeTexts.getBarcodeTexts())));
    }
}
