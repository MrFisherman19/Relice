package com.mrfisherman.relice.Entity.Asset;

public enum AssetType {
    LAPTOP("LAP"),
    PC("PC"),
    MONITOR("MON"),
    PRINTER("PRI"),
    TELEPHONE("PHO"),
    KEYBOARD("KEY"),
    MOUSE("MSE"),
    DESK("DSK"),
    TABLE("TAB"),
    CONTAINER("CTN"),
    WARDROBE("WAR"),
    HANGER("HAN"),
    ARMCHAIR("ACH"),
    CHAIR("CH");

    public String prefix;

    AssetType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
