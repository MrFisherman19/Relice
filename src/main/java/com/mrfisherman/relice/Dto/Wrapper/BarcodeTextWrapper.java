package com.mrfisherman.relice.Dto.Wrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Validated
public class BarcodeTextWrapper {

    private List<@Pattern(regexp = "^([A-Z]){3}$")String> barcodeTexts;

}
