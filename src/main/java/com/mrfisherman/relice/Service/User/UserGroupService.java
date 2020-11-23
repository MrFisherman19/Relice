package com.mrfisherman.relice.Service.User;

import com.mrfisherman.relice.Entity.User.UserGroup;

public interface UserGroupService {

    void save(UserGroup userGroup);

    UserGroup findById(Long id);
}
