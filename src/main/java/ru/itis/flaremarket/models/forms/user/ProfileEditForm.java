package ru.itis.flaremarket.models.forms.user;

import lombok.Data;

@Data
public class ProfileEditForm {
    private String email;
    private String password;
    private String nickname;
    private String code;
    private String confirmCode;
}
