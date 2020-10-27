package com.mrfisherman.relice.Service.Barcode;

import java.util.List;

public interface BarcodeService {

    byte[] generateBarcodeCode128Image(String barcodeText);

    List<byte[]> generateListOfBarcodeCode128Images(List<String> barcodesTexts);

}
