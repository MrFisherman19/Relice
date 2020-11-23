package com.mrfisherman.relice.Service.User;

import com.mrfisherman.relice.Entity.User.UserGroup;
import com.mrfisherman.relice.Repository.UserGroupRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository groupRepository;

    public UserGroupServiceImpl(UserGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void save(UserGroup userGroup) {
        groupRepository.save(userGroup);
    }

    @Override
    public UserGroup findById(Long id) {
        return groupRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
