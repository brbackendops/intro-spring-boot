package net.javaguides.springboot_api.mapper;

import net.javaguides.springboot_api.dto.UserDto;
import net.javaguides.springboot_api.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto (User user) {
        UserDto userDto = new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );

        return userDto;
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
