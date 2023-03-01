package com.api.semear.Api.Semear.domain.security.modal;

import com.api.semear.Api.Semear.domain.enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class UserSS  implements UserDetails {

    private Long id;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserSS (Long id, String email, String password, Set<Profile> profiles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = profiles.stream().map(e -> new SimpleGrantedAuthority(e.getDescription()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole (Profile profile){
        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
    }
}
