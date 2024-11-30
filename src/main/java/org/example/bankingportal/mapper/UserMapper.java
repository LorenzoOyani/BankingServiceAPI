package org.example.bankingportal.mapper;

import org.example.bankingportal.entities.User;
import org.example.bankingportal.payload.UserDTO;
import org.example.bankingportal.payload.UserRegistrationRequest;
import org.example.bankingportal.payload.UserResponse;
import org.mapstruct.*;

@Mapper( componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "password", ignore = true)
    User toUser (UserRegistrationRequest user);

    UserResponse toUserResponse(User user);

    UserResponse toUserResponse(UserDTO userDTO);

    UserResponse toUserResponse(UserRegistrationRequest user);

    User userToUserDTO(UserRegistrationRequest user);

    User userDTOToUser(UserDTO userDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(User user, @MappingTarget User userDTO);


//    void updateUser(UserRegistrationRequest user, UserDTO userDTO);



}
