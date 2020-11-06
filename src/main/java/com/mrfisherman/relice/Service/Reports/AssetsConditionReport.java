package com.mrfisherman.relice.Service.Reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.DocumentException;
import com.mrfisherman.relice.Entity.Asset.AssetConditionState;
import com.mrfisherman.relice.Repository.Projection.AssetConditionStateByAssetType;
import com.mrfisherman.relice.Service.Asset.AssetStatsService;
import com.mrfisherman.relice.Service.Document.PdfColor;
import com.mrfisherman.relice.Service.Document.PdfDocumentService;
import com.mrfisherman.relice.Service.Document.PdfFont;
import com.mrfisherman.relice.Service.Reports.Charts.Chart;
import com.mrfisherman.relice.Service.Reports.Charts.ChartBuilder;
import com.mrfisherman.relice.Service.Reports.Charts.PieChart;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
public class AssetsConditionReport implements OfficeReport {

    private final AssetStatsService assetStatsService;
    private final PdfDocumentService pdfDocumentService;
    private final Rectangle PAGE_SIZE = PageSize.A4;

    public AssetsConditionReport(AssetStatsService assetStatsService, PdfDocumentService pdfDocumentService) {
        this.assetStatsService = assetStatsService;
        this.pdfDocumentService = pdfDocumentService;
    }

    @Override
    public byte[] getFinalReport() throws IOException, DocumentException {
        Document document = new Document(PAGE_SIZE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        document.add(createGraphImage(new PieChart<>(getAssetConditionStatsDataAsMap(), 999, 500)));

        document.add(pdfDocumentService.createLabel(PdfFont.SUCCESS_LABEL_TEXT.getFont(),
                String.format("Overall office health is %.1f%%", countOfficeHealthInPercent())));

        document.add(pdfDocumentService.createLabel(PdfFont.STANDARD_LABEL_TEXT.getFont(),
                "Type of assets by state"));

        document.add(getAssetTypeCountTable());

        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private PdfPTable getAssetTypeCountTable() {
        String[] headers = new String[]{"Type of asset", "Condition state", "Amount"};
        PdfPTable table = pdfDocumentService.createTable(headers.length);
        pdfDocumentService.addTableHeaders(table, headers, PdfColor.BASIC_HEADER.get());
        addAssetConditionByAssetTypeRows(table, assetStatsService.getAssetConditionStateCountByAssetType());
        return table;
    }

    private double countOfficeHealthInPercent() {
        Map<String, Long> stats = getAssetConditionStatsDataAsMap();

        Optional<Long> optGoodCondition = Optional.ofNullable(stats.get(AssetConditionState.GOOD_CONDITION.name()));

        return (double) optGoodCondition.orElse(0L)
                / (double) stats.values().stream().mapToLong(x->x).sum() * 100;
    }

    private void addAssetConditionByAssetTypeRows(PdfPTable table, List<AssetConditionStateByAssetType> cells) {
        cells.forEach(row -> fillCellsWithConditionStateByAssetTypeStats(table, row));
    }

    private void fillCellsWithConditionStateByAssetTypeStats(PdfPTable table, AssetConditionStateByAssetType row) {
        table.addCell(row.getAssetType());
        table.addCell(row.getAssetConditionState());
        table.addCell(row.getTotalCount().toString());
    }

    private Image createGraphImage(Chart chart) throws IOException, BadElementException {
        Image image = Image.getInstance(ChartBuilder.getChartImageFromApi(chart));
        image.scalePercent(50);
        return image;
    }

    private Map<String, Long> getAssetConditionStatsDataAsMap() {
        Map<String, Long> data = new HashMap<>();
        assetStatsService.getAssetConditionStateCount().forEach(x -> data.put(x.getAssetConditionState(), x.getTotalCount()));
        return data;
    }
}
