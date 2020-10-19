package com.mrfisherman.relice.Entity.Asset;

import com.mrfisherman.relice.Entity.Property.Color;
import com.mrfisherman.relice.Entity.Property.Dimensions;

public enum AssetType {
    LAPTOP("LAP", new AssetMapDetails(new Dimensions(30,15,10), new Color("#808080"))),
    COMPUTER("COM", new AssetMapDetails(new Dimensions(15,30,9), new Color("#606060"))),
    MONITOR("MTR", new AssetMapDetails(new Dimensions(35,20,11), new Color("#707070"))),
    PRINTER("PRI", new AssetMapDetails(new Dimensions(30,30,9), new Color("#404040"))),
    WALL("WLL", new AssetMapDetails(new Dimensions(500,20,1), new Color("#646464"))),
    DESK("DSK", new AssetMapDetails(new Dimensions(90,35,3), new Color("#d2820f"))),
    TABLE("TBL", new AssetMapDetails(new Dimensions(90,35,3), new Color("#e6961e"))),
    CONTAINER("CON", new AssetMapDetails(new Dimensions(30,30,2), new Color("#c8780f"))),
    WARDROBE("WAR", new AssetMapDetails(new Dimensions(150,60,1), new Color("#351155"))),
    HANGER("HNG", new AssetMapDetails(new Dimensions(10,10,2), new Color("#404040"))),
    ARMCHAIR("ARM", new AssetMapDetails(new Dimensions(30,30,2), new Color("#d66f6f"))),
    CHAIR("CHR", new AssetMapDetails(new Dimensions(28,28,2), new Color("#dc8c19"))),
    SOFA("SOF", new AssetMapDetails(new Dimensions(52,140,1), new Color("#ffee58"))),
    OTHER("OTH", new AssetMapDetails(new Dimensions(100,100,1), new Color("#222222")));


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
