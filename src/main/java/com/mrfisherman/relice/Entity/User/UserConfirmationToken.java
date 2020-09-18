package com.mrfisherman.relice.Entity.User;

import com.mrfisherman.relice.Entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserConfirmationToken extends BaseEntity {

    private String confirmationToken;
    private LocalDate createDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    public UserConfirmationToken(User user) {
        this.user = user;
        this.createDate = LocalDate.now();
        this.confirmationToken = UUID.randomUUID().toString();
    }

}
