package com.mrfisherman.relice.Dto;

import com.mrfisherman.relice.Entity.User.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinimalUserDto {

    private String name;
    private String email;

    private UserRole userRole;

    private UserGroupDto group;

    private boolean isNonLocked;

    private boolean isEnabled;

}
