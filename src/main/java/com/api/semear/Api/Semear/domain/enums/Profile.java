package com.api.semear.Api.Semear.domain.enums;

import java.util.stream.Stream;

public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER"),
    TEACHER(3, "ROLE_TEACHER");

    private int cod;
    private String description;

    private Profile(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }
    public String getDescription() {
        return description;
    }

    public static Profile toEnum(Integer cod) {
        return Stream.of(Profile.values())
                .filter(e -> cod.equals(e.getCod()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + cod));
    }
}
