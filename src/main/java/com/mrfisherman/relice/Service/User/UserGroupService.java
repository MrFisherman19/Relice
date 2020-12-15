package com.mrfisherman.relice.Service.User;

import com.mrfisherman.relice.Entity.User.UserGroup;

import java.util.Optional;

public interface UserGroupService {

    void save(UserGroup userGroup);

    Optional<UserGroup> findById(Long id);
}
