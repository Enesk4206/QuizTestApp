package QuizApp.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import QuizApp.backend.dtos.LoginRequest;
import QuizApp.backend.dtos.LoginResponse;
import QuizApp.backend.dtos.RegisterRequest;
import QuizApp.backend.dtos.RegisterResponse;
import QuizApp.backend.services.UserService;
import lombok.RequiredArgsConstructor;

    @CrossOrigin(origins="http://localhost:5173")
    @RestController
    @RequestMapping("/api/auth")    
    @RequiredArgsConstructor
    public class UserController {
        private final UserService userService;

        @PostMapping(value="/register")
        public ResponseEntity<RegisterResponse> registeAPI(@RequestBody RegisterRequest request){
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
        }

        @PostMapping(value="/login")
        public ResponseEntity<LoginResponse> loginAPI(@RequestBody LoginRequest request){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.login(request));
        }
    }
