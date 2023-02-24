package com.api.semear.Api.Semear.domain.user.model;

import com.api.semear.Api.Semear.domain.security.modal.UserSS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileApiResponse {

    private String accessToken;
    private Integer id;
    private String email;
    private String name;
    private String typeUser;
    private Long accountNumber;

    public UserProfileApiResponse(UserSS userSS) {
        BeanUtils.copyProperties(userSS, this);
    }
}
