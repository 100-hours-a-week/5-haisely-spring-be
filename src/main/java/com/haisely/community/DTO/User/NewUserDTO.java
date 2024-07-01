package com.haisely.community.DTO.User;

public record NewUserDTO(String nickname,
                         String email,
                         String password,
                         String profileImage) {
}
