package com.mrfisherman.relice.Controller;

import com.itextpdf.text.DocumentException;
import com.itextpdf.xmp.impl.Base64;
import com.mrfisherman.relice.Controller.ExceptionHandler.HandlerUtil;
import com.mrfisherman.relice.Dto.Wrapper.BarcodeTextWrapper;
import com.mrfisherman.relice.Service.Barcode.BarcodeService;
import com.mrfisherman.relice.Service.Document.PdfService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping(value = "/barcode")
public class BarcodeController {

    private final static String WRONG_TYPE_LENGTH_MESSAGE = "Type must be three-letter, uppercase word";

    final BarcodeService barcodeService;
    final PdfService pdfService;

    public BarcodeController(BarcodeService barcodeService, PdfService pdfService) {
        this.barcodeService = barcodeService;
        this.pdfService = pdfService;
    }

    @PostMapping(value = "/generateBarcodesBase64Jpg")
    public ResponseEntity<?> generateBarcodeBase64Image(@RequestBody BarcodeTextWrapper barcodeTexts) {
        List<byte[]> list = barcodeService.generateListOfBarcodeCode128ByteArray(barcodeTexts.getBarcodeTexts())
                .stream()
                .map(Base64::encode)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/generateBarcodesPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateBarcodesPdf(@RequestBody BarcodeTextWrapper barcodeTexts) throws IOException, DocumentException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Content-Disposition", "inline; filename=barcodes.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfService.saveDataToPdf(
                        barcodeService.generateListOfBarcodeCode128ByteArray(barcodeTexts.getBarcodeTexts())));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = WRONG_TYPE_LENGTH_MESSAGE)
    public HashMap<String, String> handleWrongLengthType(Exception e) {
        return HandlerUtil.createResponseWithMessageAndError(WRONG_TYPE_LENGTH_MESSAGE, e);
    }

}
