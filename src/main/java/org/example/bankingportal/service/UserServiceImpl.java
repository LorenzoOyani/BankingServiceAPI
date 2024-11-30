package org.example.bankingportal.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.example.bankingportal.Exception.UserAlreadyExistException;
import org.example.bankingportal.Util.EmailValidator;
import org.example.bankingportal.Util.PasswordValidator;
import org.example.bankingportal.aop.ToLog;
import org.example.bankingportal.entities.User;
import org.example.bankingportal.mapper.UserMapper;
import org.example.bankingportal.payload.UserRegistrationRequest;
import org.example.bankingportal.payload.UserResponse;
import org.example.bankingportal.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;


    @Override
    public UserResponse createUser(UserRegistrationRequest userRequest) {
        Objects.requireNonNull(userRequest, "userRequest can't be null");

        log.info("Creating user...");
        if (existByEmail(userRequest)) {
            log.warn("user with {} already exists", userRequest.getEmail());
            throw new UserAlreadyExistException("user already exists");
        }
        User user = userMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return userMapper.toUserResponse(user);

    }

    private boolean existByEmail(UserRegistrationRequest userRequest) {
        String emails = userRequest.getEmail();
        return userRepository.existsUserByEmail(emails);
    }

    @Override
    public Optional<UserResponse> findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .stream().findFirst().orElse(null);

        return Optional.ofNullable(userMapper.toUserResponse(user));
    }

//    @Override
//    public Optional<User> authenticateUser(String email, String password) {
//
//        return Optional.empty();
//    }

    @Override
    public void updateUser(long id, User user) {

    }

    @Override
    public void deleteUserById(long id) {

    }
}
