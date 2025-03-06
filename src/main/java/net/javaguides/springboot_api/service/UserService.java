package net.javaguides.springboot_api.service;

import net.javaguides.springboot_api.dto.UserDto;
import net.javaguides.springboot_api.entity.User;
import net.javaguides.springboot_api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    private ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User findOneuser(long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User createUser(UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println("dto" + userDto);
        User user = modelMapper.map(userDto,User.class);
        System.out.println("from dto" + user);
        return userRepo.save(user);
    }

    public User updateUser(User user, long user_id){
        Optional<User> existingUser = userRepo.findById(user_id);
        if (existingUser.isPresent()) {
            User userExist = existingUser.get();

            if (user.getEmail() != null) {
                userExist.setEmail(user.getEmail());
            }
            userExist.setUsername(user.getUsername());

            userRepo.save(userExist);
        } else {
            throw new RuntimeException("User not found");
        }

        return null;
    }
}
