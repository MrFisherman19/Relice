package com.mrfisherman.relice.Entity.User;

import com.mrfisherman.relice.Entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class UserConfirmationToken extends BaseEntity {

    private String confirmationToken;
    private LocalDate createDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    public UserConfirmationToken() { }

    public UserConfirmationToken(User user) {
        this.user = user;
        this.createDate = LocalDate.now();
        this.confirmationToken = UUID.randomUUID().toString();
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
