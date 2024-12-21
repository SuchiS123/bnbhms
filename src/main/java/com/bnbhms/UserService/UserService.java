package com.bnbhms.UserService;

import com.bnbhms.Entity.User;
import com.bnbhms.PayLoad.LoginDto;
import com.bnbhms.PayLoad.UserDto;
import com.bnbhms.Repository.UserRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private JwtService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper,JwtService jwtService)
    {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }


    public ResponseEntity<?> addUser(UserDto userDto) {
        User user=mapToEntiy(userDto);
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()) {
            return new ResponseEntity<>("UserName already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if(opMobile .isPresent()) {
            return new ResponseEntity<>("Mobile already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        user.setRole("ROLE_USER");
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        User savedUser = userRepository.save(user);
        UserDto userDto1 = mapToDto(savedUser);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }


    public User mapToEntiy(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto mapToDto(User user) {
        return modelMapper.map(user, UserDto.class);

    }

    public String login(LoginDto loginDto) {
        Optional<User> opUsername = userRepository.findByUsername(loginDto.getUsername());
        if(opUsername.isPresent()) {
            User user = opUsername.get();
          if(BCrypt.checkpw(loginDto.getPassword(),user.getPassword())) {
             String token=jwtService.generateToken(user.getUsername());
             return token;
          }

        }
            return null;

    }

    public ResponseEntity<?> addOwner(UserDto userDto) {
        User user=mapToEntiy(userDto);
        Optional<User> opEmail = userRepository.findByEmail(user.getEmail());
        if(opEmail.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()) {
            return new ResponseEntity<>("UserName already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> opMobile = userRepository.findByMobile(user.getMobile());
        if(opMobile .isPresent()) {
            return new ResponseEntity<>("Mobile already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        user.setRole("ROLE_OWNER");
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10)));
        User savedUser = userRepository.save(user);
        UserDto userDto1 = mapToDto(savedUser);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }


}
