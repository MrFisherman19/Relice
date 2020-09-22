package com.mrfisherman.relice.Entity.Asset;

public enum AssetType {
    LAPTOP("LAP"),
    COMPUTER("COM"),
    MONITOR("MTR"),
    PRINTER("PRI"),
    TELEPHONE("TEL"),
    KEYBOARD("KEY"),
    MOUSE("MOU"),
    DESK("DSK"),
    TABLE("TBL"),
    CONTAINER("CON"),
    WARDROBE("WAR"),
    HANGER("HNG"),
    ARMCHAIR("ARM"),
    CHAIR("CHR");

    final private String shortcut;

    AssetType(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut() {
        return shortcut;
    }
}
