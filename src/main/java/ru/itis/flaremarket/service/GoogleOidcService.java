package ru.itis.flaremarket.service;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface GoogleOidcService {
    void processOAuthPostLogin(DefaultOidcUser oidcUser, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
