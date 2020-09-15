package com.mrfisherman.relice.Service.Barcode;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BarcodeServiceImpl implements BarcodeService {

    private final int BARCODE_RESOLUTION = 512;

    @Override
    public byte[] generateBarcodeCode128ByteArray(String barcodeText) {
        Code128Bean barcodeGenerator = new Code128Bean();
        barcodeGenerator.setFontSize(5);
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(BARCODE_RESOLUTION, BufferedImage.TYPE_BYTE_BINARY, true, 0);
        barcodeGenerator.generateBarcode(canvas, barcodeText);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(canvas.getBufferedImage(), "jpg", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public List<byte[]> generateListOfBarcodeCode128ByteArray(List<String> barcodesTexts) {
        return barcodesTexts.stream().map(this::generateBarcodeCode128ByteArray).collect(Collectors.toList());
    }
}
