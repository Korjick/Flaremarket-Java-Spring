package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import ru.itis.flaremarket.dto.UserDto;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.models.enums.user.UserState;
import ru.itis.flaremarket.models.forms.user.UserForm;
import ru.itis.flaremarket.security.details.UserDetailsImpl;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class GoogleOidcServiceImpl implements GoogleOidcService {

    @Autowired
    private UserService userService;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public void processOAuthPostLogin(DefaultOidcUser oidcUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();
        User user = userService.getRawUserByEmail(email);
        if (user.getEmail() != null) {
            if(user.getOauthLinked()) {
                if(user.getState().equals(UserState.NOT_CONFIRMED)) response.sendError(HttpStatus.BAD_REQUEST.value(), "Аккаунт не подтвержден");
                else {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    response.sendRedirect("/");
                }
            }
            else response.sendError(HttpStatus.BAD_REQUEST.value(), "Пользователь уже существует");
        } else {
            UserForm userForm = new UserForm();
            userForm.setEmail(email);
            userForm.setNickname(name);
            userForm.setPassword(" ");
            userForm.setOauthLinked(true);
            signUpService.signUp(userForm);
            response.sendRedirect("/?acceptYourAccount");
        }
    }
}
