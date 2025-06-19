package QuizApp.backend.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import QuizApp.backend.models.Role;
import QuizApp.backend.models.User;
import QuizApp.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitiliazer implements CommandLineRunner{
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        if(!userRepository.existsByUsername("admin")){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@example.com");
            admin.setAge(24);
            admin.setPhoneNumber("555 444 2233");
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
        }
    }
}
