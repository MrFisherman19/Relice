package com.mrfisherman.relice.Entity.User;

import com.mrfisherman.relice.Entity.NamedEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

@Entity
public class User extends NamedEntity implements UserDetails {

    @Email
    private String email;

    private String password;

    @NotNull
    private UserRole userRole = UserRole.ROLE_USER;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private UserGroup group;

    private boolean isEnabled = false;
    private boolean isNonLocked = true;
    private boolean isNonExpired = true;
    private boolean isCredentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroup getGroup() { return group; }

    public void setGroup(UserGroup group) { this.group = group; }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setNonLocked(boolean nonLocked) {
        isNonLocked = nonLocked;
    }

    public void setNonExpired(boolean nonExpired) {
        isNonExpired = nonExpired;
    }

    public void setCredentialsNotExpired(boolean credentialsNotExpired) {
        isCredentialsNonExpired = credentialsNotExpired;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", isEnabled=" + isEnabled +
                ", isNonLocked=" + isNonLocked +
                ", isNonExpired=" + isNonExpired +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                '}';
    }
}
