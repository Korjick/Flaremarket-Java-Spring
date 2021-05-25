
package ru.itis.flaremarket.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.itis.flaremarket.service.CustomOAuth2UserService;
import ru.itis.flaremarket.service.GoogleOidcService;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService;

    @Autowired
    private GoogleOidcService googleOidcService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/signIn")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/recommendation")
                .failureUrl("/?authError")
                .permitAll()

                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/signIn").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/sms").authenticated()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/myitems").authenticated()
                .antMatchers("/auction/**").authenticated()
                .antMatchers("/recommendation/**").authenticated()
                .antMatchers("/sell/**").authenticated()
                .antMatchers("/solds/**").authenticated()
                .antMatchers("/games/**").authenticated()
                .antMatchers("/files/**").authenticated()
                .antMatchers("/users").hasAnyAuthority("ADMIN")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")

                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(oidcUserService)
                .userService(oauthUserService)
                .and()
                .successHandler(((request, response, authentication) -> {
                    DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
                    authentication.setAuthenticated(true);
                    googleOidcService.processOAuthPostLogin(oidcUser, request, response);
                }))

                .and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository).tokenValiditySeconds(86400)

                .and()
                .csrf()

                .and()
                .exceptionHandling().accessDeniedPage("/error");
    }
}
