package com.example.Real_Time.Event.Ticketing.System.Controller;


import com.example.Real_Time.Event.Ticketing.System.DTO.loginResponseDto;
import com.example.Real_Time.Event.Ticketing.System.DTO.userDto;
import com.example.Real_Time.Event.Ticketing.System.Entity.user;
import com.example.Real_Time.Event.Ticketing.System.Security.jwtconfig;
import com.example.Real_Time.Event.Ticketing.System.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "api/v1/user")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class userController {

   private final jwtconfig jwtconfig;

   private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping ("/login")
    public loginResponseDto login(@RequestBody @Validated userDto userDto) {


//        boolean x =  userService.loggedIn(modelMapper.map(userDto.getEmail(), user.class),
//                modelMapper.map(userDto.getPassword(), user.class));
//
//        if (x) {
            var token = jwtconfig.jwt(1L,userDto.getEmail(), List.of("user"));
            return loginResponseDto.builder()
                    .access_token(token)
                    .email(userDto.getEmail())
                    .build();
//        }else {
//            return null;
//        }
    }

}
