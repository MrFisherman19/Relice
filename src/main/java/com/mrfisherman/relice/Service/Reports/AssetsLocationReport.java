package com.mrfisherman.relice.Service.Reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mrfisherman.relice.Dto.AssetDto;
import com.mrfisherman.relice.Dto.FloorDto;
import com.mrfisherman.relice.Service.Asset.AssetService;
import com.mrfisherman.relice.Service.Document.PdfColor;
import com.mrfisherman.relice.Service.Document.PdfDocumentService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Supplier;

@Service
public class AssetsLocationReport implements OfficeReport {

    private final AssetService assetService;
    private final PdfDocumentService pdfDocumentService;

    private final Rectangle PAGE_SIZE = PageSize.A4.rotate();

    public AssetsLocationReport(AssetService assetService, PdfDocumentService pdfDocumentService) {
        this.assetService = assetService;
        this.pdfDocumentService = pdfDocumentService;
    }

    @Override
    public byte[] getFinalReport() throws IOException, DocumentException {
        Document document = new Document(PAGE_SIZE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        document.add(pdfDocumentService.createLabel(String.format("Report generated at: %s", getCurrentDateString())));
        document.add(pdfDocumentService.createLabel("Assets to relocation:"));
        document.add(getAssetTable());

        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private PdfPTable getAssetTable() {
        String[] headers = new String[]{"Barcode", "Type", "Previous location", "Current location", "Planned location", "Additional note"};
        PdfPTable table = pdfDocumentService.createTable(headers.length);
        pdfDocumentService.addTableHeaders(table, headers, PdfColor.BASIC_HEADER.get());
        addAssetToTable(table, assetService.findAssetsToRelocation());
        return table;
    }

    private void addAssetToTable(PdfPTable table, List<AssetDto> cells) {
        cells.forEach(row -> fillCellsWithAssetInfo(table, row));
    }

    private void fillCellsWithAssetInfo(PdfPTable table, AssetDto asset) {
        table.addCell(asset.getBarcode());
        table.addCell(asset.getAssetType());

        table.addCell(createLocationString(asset.getLocalization().getFloor_previous()));
        table.addCell(createLocationString(asset.getLocalization().getFloor()));
        table.addCell(createLocationString(asset.getLocalization().getFloor_planned()));

        table.addCell(asset.getAdditionalNote());
    }

    @SuppressWarnings({"Replace", "Convert2MethodRef"})
    private String createLocationString(FloorDto floor) {
        return (nullGuard(() -> floor.getName()) + " in " + nullGuard(() -> floor.getBuilding().getName()))
                .replaceAll("null in null", "-");
    }

    private static <T> T nullGuard(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private String getCurrentDateString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
        return dateTimeFormatter.format(LocalDateTime.now());
    }

}
