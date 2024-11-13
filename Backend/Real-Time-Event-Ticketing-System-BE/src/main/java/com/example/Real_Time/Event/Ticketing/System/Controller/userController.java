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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user")
@CrossOrigin
@RequiredArgsConstructor
public class userController {

    private final jwtconfig jwtconfig;
    private final UserService userService;  // Make it final for proper injection

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping ("/login")
    public ResponseEntity login(@RequestBody @Validated userDto userDto) {
        user userEntity = modelMapper.map(userDto, user.class);

        boolean isLoggedIn = userService.loggedIn(userEntity);

        if (isLoggedIn) {
            var token = jwtconfig.jwt(1L, userDto.getEmail(), List.of("user"));
            var response = loginResponseDto.builder()
                    .access_token(token)
                    .email(userDto.getEmail())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
