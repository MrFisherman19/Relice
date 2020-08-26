package com.mrfisherman.relice.Entity.Electronic;

public enum ElectronicEquipmentType {
    LAPTOP("LAP"),
    PC("PC"),
    MONITOR("MON"),
    PRINTER("PRI"),
    TELEPHONE("PHO"),
    KEYBOARD("KEY"),
    MOUSE("MSE");

    public String prefix;

    ElectronicEquipmentType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
