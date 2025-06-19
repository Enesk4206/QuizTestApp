package QuizApp.backend.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import QuizApp.backend.dtos.LoginRequest;
import QuizApp.backend.dtos.LoginResponse;
import QuizApp.backend.dtos.RegisterRequest;
import QuizApp.backend.dtos.RegisterResponse;
import QuizApp.backend.models.Role;
import QuizApp.backend.models.User;
import QuizApp.backend.repositories.UserRepository;
import QuizApp.backend.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil JwtTokenUtil;

    public RegisterResponse register(RegisterRequest request){
        try {
            if(request.getRole() != Role.USER){
                throw new RuntimeException("Only Users can register!");
            }
            checkUsernameAndEmailUniqueness(request.getUsername(), request.getEmail());

            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            user.setAge(request.getAge());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setRole(Role.USER);

            User savedUser = userRepository.save(user);

            //User Details
            UserDetails userDetails = org.springframework.security.core.userdetails.User
            .withUsername(savedUser.getUsername())
            .password(savedUser.getPassword())
            .roles(savedUser.getRole().name())
            .build();

            String token = JwtTokenUtil.generateToken(userDetails);
            return new RegisterResponse(
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getAge(),
                savedUser.getPhoneNumber(),
                savedUser.getRole(),
                token

            );
        } catch (RuntimeException e) {
            throw new RuntimeException("Internal Server Error!"+ e.getMessage(),e);
        }
    }

    public LoginResponse login(LoginRequest request){
        
        User user = userRepository.getByUsername(request.getUsername()).orElseThrow(
            () -> new RuntimeException("User not found!")
        );

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Password isn't correct!");
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User
        .withUsername(user.getUsername())
        .password(user.getPassword())
        .roles(user.getRole().name())
        .build();

        String token = JwtTokenUtil.generateToken(userDetails);

        return new LoginResponse(
                user.getUsername(),
                user.getEmail(),
                user.getAge(),
                user.getPhoneNumber(),
                user.getRole(),
                token

            );
    }


    private void checkUsernameAndEmailUniqueness(String username, String email){
        if(userRepository.existsByUsername(username)){
            throw new RuntimeException("Username already exists: "+username);
        }
        if(userRepository.existsByEmail(email)){
            throw new RuntimeException("Email already exists: "+email);
        }
    }
}
