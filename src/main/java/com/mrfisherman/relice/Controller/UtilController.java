package com.mrfisherman.relice.Controller;

import com.mrfisherman.relice.Dto.Wrapper.OptionsWrapper;
import com.mrfisherman.relice.Entity.Asset.AssetConditionState;
import com.mrfisherman.relice.Entity.Asset.AssetLocationState;
import com.mrfisherman.relice.Entity.Asset.AssetType;
import com.mrfisherman.relice.Entity.User.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/options")
public class UtilController {

    @GetMapping("/getSelectableOptionsArrays")
    public OptionsWrapper getSelectableOptionsArrays() {
        return new OptionsWrapper(
                AssetType.values(),
                AssetConditionState.values(),
                AssetLocationState.values(),
                UserRole.values());

    }
}
