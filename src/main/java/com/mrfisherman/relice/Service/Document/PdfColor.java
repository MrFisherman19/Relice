package com.mrfisherman.relice.Service.Document;

import com.itextpdf.text.BaseColor;

public enum PdfColor {
    BASIC_HEADER{
        @Override
        public BaseColor get() {
            return new BaseColor(153, 0, 51);
        }
    }, BASIC_TEXT {
        @Override
        public BaseColor get() {
            return BaseColor.BLACK;
        }
    }, BASIC_WHITE {
        @Override
        public BaseColor get() {
            return BaseColor.WHITE;
        }
    }, LABEL_GREEN {
        @Override
        public BaseColor get() {
            return new BaseColor(46,125,102);
        }
    };

    public abstract BaseColor get();
}
