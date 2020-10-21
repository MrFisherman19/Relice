package com.mrfisherman.relice.Service.Reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mrfisherman.relice.Service.Asset.AssetStatsService;
import com.mrfisherman.relice.Service.Reports.Charts.Chart;
import com.mrfisherman.relice.Service.Reports.Charts.ChartBuilder;
import com.mrfisherman.relice.Service.Reports.Charts.PieChart;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class AssetsConditionReport implements OfficeReport {

    private final AssetStatsService assetStatsService;
    private final Rectangle PAGE_SIZE = PageSize.A4;

    public AssetsConditionReport(AssetStatsService assetStatsService) {
        this.assetStatsService = assetStatsService;
    }

    @Override
    public byte[] getFinalReport() throws DocumentException, IOException {
        Document document = new Document(PAGE_SIZE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PieChart<Long> pieChart = new PieChart<>(getAssetConditionStatsData(), 999, 500);

        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        document.add(createGraphImage(pieChart));

        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private Image createGraphImage(Chart chart) throws IOException, BadElementException {

        Image image = Image.getInstance(ChartBuilder.getChartImageFromApi(chart));
        image.scalePercent(50);

        return image;
    }

    private Map<String, Long> getAssetConditionStatsData() {
        Map<String, Long> data = new HashMap<>();
        assetStatsService.getAssetConditionStateCount()
                .forEach(x -> data.put(x.getAssetConditionState(), x.getTotalCount()));
        return data;
    }
}
