package org.example.bankingportal.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.bankingportal.Util.EmailValidator;
import org.example.bankingportal.Util.PasswordValidator;
import org.example.bankingportal.aop.ToLog;
import org.example.bankingportal.entities.User;
import org.example.bankingportal.payload.UserRegistrationRequest;
import org.example.bankingportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;

    public UserServiceImpl(){};

    @Override
    public User createUser(UserRegistrationRequest user) {
        Objects.requireNonNull(user);
        User newUser = new User();
        String[] names = user.getName().split(" ", 2);
        newUser.setFirstName(names[0]);
        newUser.setLastName(names.length > 1 ? names[1] : "");
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setCountryCode(user.getCountryCode());

        return userRepository.save(newUser);

    }

    @Override
    public User findUserByEmail(String email) {
        if (!EmailValidator.isValidEmail(email) && !userRepository.existsUserByEmail(email)) {
            return null;
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return null;
    }

    @ToLog
    @Transactional
    @Override
    public void updateUser(long id, User user) {
        User user1 = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("user with id, {} , not in database" , id);
                    return new RuntimeException("failed to find user with id, " + id);
                });
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setCountryCode(user.getCountryCode());
        userRepository.save(user1);
    }

    @Transactional
    @Override
    public void deleteUser(long id, User user) {
        User user1 = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user1);
    }
}
