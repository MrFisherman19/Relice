package com.mrfisherman.relice.Entity.Asset;

import com.mrfisherman.relice.Entity.Property.Color;
import com.mrfisherman.relice.Entity.Property.Dimensions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssetMapDetails {

    @Embedded
    private Dimensions dimensions = new Dimensions(100,100,1);

    @Embedded
    private Color color = new Color("#222222");
}
