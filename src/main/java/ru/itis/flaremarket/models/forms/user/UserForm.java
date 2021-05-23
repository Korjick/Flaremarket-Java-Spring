package ru.itis.flaremarket.models.forms.user;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserForm {
    @NotEmpty(message = "Email cannot be emptyEmail")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Wrong format of email")
    private String email;
    @NotEmpty(message = "Nickname cannot be empty")
    @Pattern(regexp = "[a-zA-Z0-9\\\\._-]{3,}", message = "Wrong format of nickname(minimum 3 symbols, a-z, A-Z, 0-9, points, dashes and underscores)")
    private String nickname;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password should contain minimum 6 symbols")
    private String password;
}
