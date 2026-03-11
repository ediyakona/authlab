package com.example.authlab.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authlab.jwt.JwtHmacProvider;
import com.example.authlab.user.dto.UserLoginRequest;
import com.example.authlab.user.dto.UserLoginResponse;
import com.example.authlab.user.dto.UserSignupRequest;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHmacProvider jwtProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHmacProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public void signup(UserSignupRequest request) {
        if (userRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException();
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
            request.getLoginId(),
            encodedPassword,
            UserRole.USER
        );

        userRepository.save(user);
    }

    public UserLoginResponse login(UserLoginRequest request) {
        User user = userRepository.findByLoginId(request.getLoginId())
            .orElseThrow(() -> new IllegalArgumentException(UserMessage.MSG_2.getLocaleMessage()));

        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new IllegalArgumentException(UserMessage.MSG_2.getLocaleMessage());
        }

        String accessToken = jwtProvider.createToken(user);

        return new UserLoginResponse(
            user.getLoginId(),
            user.getRole().name(),
            accessToken
        );
    }

}
