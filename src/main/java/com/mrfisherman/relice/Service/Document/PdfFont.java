package com.mrfisherman.relice.Service.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public enum PdfFont {
    HEADER_TEXT(FontFactory.getFont(FontFactory.TIMES_BOLD, 14, PdfColor.BASIC_WHITE.get())),
    STANDARD_LABEL_TEXT(FontFactory.getFont(FontFactory.TIMES_BOLD, 16, PdfColor.BASIC_TEXT.get())),
    SUCCESS_LABEL_TEXT(FontFactory.getFont(FontFactory.TIMES_BOLD, 26, PdfColor.LABEL_GREEN.get()));

    private Font font;

    PdfFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
}
