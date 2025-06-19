package QuizApp.backend.services;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import QuizApp.backend.models.User;
import QuizApp.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username){
        User existsUser = userRepository.getByUsername(username).orElseThrow(
            ()-> new RuntimeException("User not Found!")
        );

        return new org.springframework.security.core.userdetails.User(
            existsUser.getUsername(),
            existsUser.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_"+existsUser.getRole().name()))
        );
    }
}
