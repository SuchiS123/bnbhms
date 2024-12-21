package com.bnbhms.Controller;


import com.bnbhms.PayLoad.LoginDto;
import com.bnbhms.PayLoad.UserDto;
import com.bnbhms.PayLoad.TokenDto;
import com.bnbhms.UserService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDto userDto, BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ResponseEntity<?> responseEntity = userService.addUser(userDto);
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin(@RequestBody LoginDto loginDto)
    {
        String token = userService.login(loginDto);
       TokenDto tokenDto=new TokenDto();

       tokenDto.setToken(token);
       tokenDto.setType("JWT");

       if(token!=null)
       {
           return new ResponseEntity<>(tokenDto, HttpStatus.OK);
       }

        return new ResponseEntity<>("INVALID LOG IN", HttpStatus.OK);
    }

    @PostMapping("/owner/sign-up")
    public ResponseEntity<?> Ownersignup(@Valid @RequestBody UserDto userDto, BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ResponseEntity<?> responseEntity = userService.addOwner(userDto);
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }



}
