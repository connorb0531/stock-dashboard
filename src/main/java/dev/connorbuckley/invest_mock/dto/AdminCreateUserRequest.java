package dev.connorbuckley.invest_mock.dto;

import dev.connorbuckley.invest_mock.entity.User.Role;

public record AdminCreateUserRequest (
    String username,
    String password,
    Role role
){}
