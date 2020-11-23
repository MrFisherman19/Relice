package com.mrfisherman.relice.Entity.User;

import com.mrfisherman.relice.Entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserGroup extends BaseEntity {

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "group")
    private Set<User> users;

}
