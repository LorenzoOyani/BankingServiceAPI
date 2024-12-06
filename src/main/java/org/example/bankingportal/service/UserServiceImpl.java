package org.example.bankingportal.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.example.bankingportal.Exception.UserAlreadyExistException;
import org.example.bankingportal.Util.BaseUserService;
import org.example.bankingportal.Util.EmailValidator;
import org.example.bankingportal.Util.PasswordValidator;
import org.example.bankingportal.aop.ToLog;
import org.example.bankingportal.entities.User;
import org.example.bankingportal.mapper.UserMapper;
import org.example.bankingportal.mapper.UserMappers;
import org.example.bankingportal.payload.UserDTO;
import org.example.bankingportal.payload.UserRegistrationRequest;
import org.example.bankingportal.payload.UserResponse;
import org.example.bankingportal.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final UserMappers userMappers;

    private final BaseUserService userService;


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

    @Override
    public User getUser(Map<String, Object> users) {
        return userService.loggedUser(users);
    }

    public ResponseEntity<UserDTO> getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return ResponseEntity.ok(userMappers.convertFromEntity(user));
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

    @Override
    public void updateUser(long id, UserRegistrationRequest registrationRequest) {

        User users = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException(String.format("user with id %s not found", id))
        );
        userMapper.updateUserFromRequest(registrationRequest, users);
        if (registrationRequest.getPassword() != null && !registrationRequest.getPassword().isEmpty()) {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
        }
        userRepository.save(users);

    }

    @Override
    public void deleteUserById(long id) {
        log.info("deleting user with user-id, {}", id);
        if (!userRepository.existsById(id)) {
            log.warn("user with id {} does not exist", id);
            throw new UsernameNotFoundException("user with id " + id + " does not exist");
        }
        userRepository.deleteById(id);


    }
}
