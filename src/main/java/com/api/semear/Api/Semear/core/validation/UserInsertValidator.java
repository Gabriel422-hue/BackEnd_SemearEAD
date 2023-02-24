package com.api.semear.Api.Semear.core.validation;

import com.api.semear.Api.Semear.core.exception.FieldMessage;
import com.api.semear.Api.Semear.domain.user.model.User;
import com.api.semear.Api.Semear.domain.user.model.UserApiRequest;
import com.api.semear.Api.Semear.domain.user.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserInsertValidator  implements ConstraintValidator<UserInsert, UserApiRequest> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(UserApiRequest value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        User aux = userRepository.findByEmail(value.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
