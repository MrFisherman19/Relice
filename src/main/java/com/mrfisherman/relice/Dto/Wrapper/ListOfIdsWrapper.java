package com.mrfisherman.relice.Dto.Wrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListOfIdsWrapper {
    List<Long> listOfIds;
}
