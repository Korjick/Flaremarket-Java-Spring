
package ru.itis.flaremarket.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.repository.UserRepository;

import java.util.Optional;

@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userCandidate = userRepository.findByEmailOrNickname(email, email);
        if (userCandidate.isPresent()) {
            return new UserDetailsImpl(userCandidate.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
