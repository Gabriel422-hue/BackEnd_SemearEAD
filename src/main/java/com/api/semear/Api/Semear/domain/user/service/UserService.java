package com.api.semear.Api.Semear.domain.user.service;

import com.api.semear.Api.Semear.core.exception.AuthorizationException;
import com.api.semear.Api.Semear.domain.enums.Profile;
import com.api.semear.Api.Semear.domain.security.modal.UserSS;
import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.model.UserProfileApiResponse;
import com.api.semear.Api.Semear.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Transactional
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException(email);

        var userSS = new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
        userSS.setTypeUser(userSS.getTypeUser());
        return userSS;
    }

    public UserProfileApiResponse getUserProfile(UserSS userSS){
        return new UserProfileApiResponse(userSS);
    }

    public static UserSS getAuthenticatedUser() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }
    public static void validateIfUserHasAuthoritation(Integer userId) {
        var user = getAuthenticatedUser();
        if (isAnUserValid(userId, user))
            throw new AuthorizationException("Acesso negado");
    }

    public static boolean userHasAuthoritation(Integer userId) {
        var user = getAuthenticatedUser();
        return isAnUserValid(userId, user);
    }

    private static boolean isAnUserValid(Integer userId, UserSS user) {
        return isNull(user) || !user.hasRole(Profile.ADMIN) && !userId.equals(user.getId());
    }

    public boolean isValidUser(UserSS authenticatedUser, Integer userId, String typeUser) {
        return nonNull(authenticatedUser) && nonNull(userId) && nonNull(typeUser)
                && authenticatedUser.getId().equals(userId)
                && typeUser.equals(authenticatedUser.getTypeUser());
    }
}
