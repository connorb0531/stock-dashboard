package dev.connorbuckley.invest_mock.dto;

import dev.connorbuckley.invest_mock.entity.User;

public record UserResponse(
    Long id,
    String username,
    double cashBalance
) {
    public static UserResponse from(User user) {
                return new UserResponse(user.getId(), 
                                user.getUsername(), 
                                user.getAccount().getCashAmount());
    }
}
