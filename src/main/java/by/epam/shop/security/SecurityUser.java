package by.epam.shop.security;

import by.epam.shop.model.Status;
import by.epam.shop.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isAcive;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isAcive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isAcive = isAcive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAcive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAcive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAcive;
    }

    @Override
    public boolean isEnabled() {
        return isAcive;
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities());
    }
}
