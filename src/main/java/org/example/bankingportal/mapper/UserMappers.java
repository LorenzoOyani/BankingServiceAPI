package org.example.bankingportal.mapper;

import org.example.bankingportal.Util.BaseEntity;
import org.example.bankingportal.entities.User;
import org.example.bankingportal.payload.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class UserMappers extends BaseEntity<UserDTO, User> {
    @Override
    public User convertToEntity(UserDTO userDTO, Object... args) {
        User user = new User();
        if (!ObjectUtils.isEmpty(userDTO)) {
            BeanUtils.copyProperties(userDTO, user);
        }
        return user;
    }

    @Override
    public UserDTO convertFromEntity(User user, Object... args) {
        UserDTO userDTO = new UserDTO();
        if (!ObjectUtils.isEmpty(user)) {
            BeanUtils.copyProperties(user, userDTO);
        }
        return userDTO;
    }
}
