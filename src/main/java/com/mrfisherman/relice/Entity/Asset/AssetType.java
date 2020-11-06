package com.mrfisherman.relice.Entity.Asset;

import com.mrfisherman.relice.Entity.Property.Color;
import com.mrfisherman.relice.Entity.Property.Dimensions;

public enum AssetType {
    LAPTOP(new AssetMapDetails(new Dimensions(30,15,10), new Color("#808080"))),
    COMPUTER(new AssetMapDetails(new Dimensions(15,30,9), new Color("#606060"))),
    MONITOR(new AssetMapDetails(new Dimensions(35,20,11), new Color("#707070"))),
    PRINTER(new AssetMapDetails(new Dimensions(30,30,9), new Color("#404040"))),
    WALL(new AssetMapDetails(new Dimensions(500,20,1), new Color("#646464"))),
    DESK(new AssetMapDetails(new Dimensions(90,35,3), new Color("#d2820f"))),
    TABLE(new AssetMapDetails(new Dimensions(90,35,3), new Color("#e6961e"))),
    CONTAINER(new AssetMapDetails(new Dimensions(30,30,2), new Color("#c8780f"))),
    WARDROBE(new AssetMapDetails(new Dimensions(150,60,1), new Color("#351155"))),
    HANGER(new AssetMapDetails(new Dimensions(10,10,2), new Color("#404040"))),
    ARMCHAIR(new AssetMapDetails(new Dimensions(30,30,2), new Color("#d66f6f"))),
    CHAIR(new AssetMapDetails(new Dimensions(28,28,2), new Color("#dc8c19"))),
    SOFA(new AssetMapDetails(new Dimensions(52,140,1), new Color("#ffee58"))),
    OTHER(new AssetMapDetails(new Dimensions(100,100,1), new Color("#222222")));


    private final AssetMapDetails assetMapDetails;

    AssetType(AssetMapDetails assetMapDetails) {
        this.assetMapDetails = assetMapDetails;
    }

    public AssetMapDetails getAssetMapDetails() { return assetMapDetails; };
}
