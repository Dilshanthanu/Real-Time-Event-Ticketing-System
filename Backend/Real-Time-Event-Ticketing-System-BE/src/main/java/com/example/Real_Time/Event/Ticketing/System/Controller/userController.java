package com.example.Real_Time.Event.Ticketing.System.Controller;


import com.example.Real_Time.Event.Ticketing.System.DTO.ResponseDTO;
import com.example.Real_Time.Event.Ticketing.System.DTO.SignInResponseDto;
import com.example.Real_Time.Event.Ticketing.System.DTO.loginResponseDto;
import com.example.Real_Time.Event.Ticketing.System.DTO.userDto;
import com.example.Real_Time.Event.Ticketing.System.Entity.user;
import com.example.Real_Time.Event.Ticketing.System.Security.jwtconfig;
import com.example.Real_Time.Event.Ticketing.System.Service.UserService;
import com.example.Real_Time.Event.Ticketing.System.util.VarList;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final UserService userService;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private ResponseDTO responseDTO = new ResponseDTO();


    @PostMapping ("/login")
    public ResponseEntity login(@RequestBody @Validated userDto userDto) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            user userEntity = modelMapper.map(userDto, user.class);

            user isLoggedIn = userService.loggedIn(userEntity);


            if (isLoggedIn != null) {
                var token = jwtconfig.jwt(isLoggedIn.getId(), userDto.getEmail(), List.of("user"));
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setContent(loginResponseDto.builder()
                        .access_token(token)
                        .email(userDto.getEmail())
                                .id(isLoggedIn.getId())
                                .role(isLoggedIn.getRole())
                        .build());
                responseDTO.setMessage("Login successful");
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (isLoggedIn == null) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setContent(userDto);
                responseDTO.setMessage("User Not Found");
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setContent(userDto);
                responseDTO.setMessage("Internal Server Error");
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setContent(userDto);
            responseDTO.setMessage("Internal Server Error");
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/sign_up")
    public ResponseEntity sign_up_user(@RequestBody userDto userDto){
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            user res = userService.Sign(modelMapper.map(userDto, user.class));
            if (res != null) {
                var token = jwtconfig.jwt(res.getId(), userDto.getEmail(), List.of("user"));
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setContent(SignInResponseDto.builder()
                        .access_token(token)
                        .email(userDto.getEmail())
                                .name(userDto.getName())

                        .id(res.getId())
                        .role(res.getRole())
                        .build());
                responseDTO.setMessage("SignUp successful");
                responseDTO.setContent(userDto);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res == null) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Email is already entered");
                responseDTO.setContent(userDto);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(userDto);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception x) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Internal Server Error");
            responseDTO.setContent(userDto);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
