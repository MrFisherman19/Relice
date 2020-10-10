package com.mrfisherman.relice.Entity.Asset;

import com.mrfisherman.relice.Entity.Property.Color;
import com.mrfisherman.relice.Entity.Property.Dimensions;

public enum AssetType {
    LAPTOP("LAP", new AssetMapDetails(new Dimensions(30,15,10), new Color(128,128,128,255))),
    COMPUTER("COM", new AssetMapDetails(new Dimensions(15,30,9), new Color(96,96,96,255))),
    MONITOR("MTR", new AssetMapDetails(new Dimensions(35,20,11), new Color(112,112,112,255))),
    PRINTER("PRI", new AssetMapDetails(new Dimensions(30,30,9), new Color(64,64,64,255))),
    WALL("WLL", new AssetMapDetails(new Dimensions(500,20,1), new Color(100, 100, 100,255))),
    DESK("DSK", new AssetMapDetails(new Dimensions(90,35,3), new Color(210,130,15,255))),
    TABLE("TBL", new AssetMapDetails(new Dimensions(90,35,3), new Color(230,150,30,255))),
    CONTAINER("CON", new AssetMapDetails(new Dimensions(30,30,2), new Color(200,120, 15,255))),
    WARDROBE("WAR", new AssetMapDetails(new Dimensions(150,60,1), new Color(53,17,85,255))),
    HANGER("HNG", new AssetMapDetails(new Dimensions(10,10,2), new Color(64,64,64,255))),
    ARMCHAIR("ARM", new AssetMapDetails(new Dimensions(30,30,2), new Color(214,111,111,255))),
    CHAIR("CHR", new AssetMapDetails(new Dimensions(28,28,2), new Color(220,140,25,255))),
    SOFA("SOF", new AssetMapDetails(new Dimensions(52,140,1), new Color(255,238,88,255)));


    private final String shortcut;
    private final AssetMapDetails assetMapDetails;

    AssetType(String shortcut, AssetMapDetails assetMapDetails) {
        this.shortcut = shortcut;
        this.assetMapDetails = assetMapDetails;
    }

    public String getShortcut() {
        return shortcut;
    }

    public AssetMapDetails getAssetMapDetails() { return assetMapDetails; };
}
