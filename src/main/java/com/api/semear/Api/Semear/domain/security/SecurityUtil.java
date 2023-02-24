package com.api.semear.Api.Semear.domain.security;

import com.api.semear.Api.Semear.domain.security.modal.UserSS;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.api.semear.Api.Semear.domain.enums.Profile.CUSTOMER;
import static com.api.semear.Api.Semear.domain.enums.Profile.TEACHER;

@UtilityClass
public class SecurityUtil {

    public static UserSS getUserSS() {
        return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getTypeUser(UserSS userSS) {
        String typeUser = String.valueOf(CUSTOMER);
        if (userSS.getAuthorities().contains(new SimpleGrantedAuthority(TEACHER.getDescription())))
            typeUser = String.valueOf(TEACHER);

        return typeUser;
    }
}
