package com.mrfisherman.relice.Service.Barcode;

import java.util.List;

public interface BarcodeService {

    byte[] generateBarcodeCode128ByteArray(String barcodeText);

    List<byte[]> generateListOfBarcodeCode128ByteArray(List<String> barcodesTexts);

}
