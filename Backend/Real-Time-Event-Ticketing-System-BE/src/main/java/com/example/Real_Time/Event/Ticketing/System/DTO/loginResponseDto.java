package com.example.Real_Time.Event.Ticketing.System.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class loginResponseDto {

    private final String access_token;
    private String email;

}
