package com.example.Real_Time.Event.Ticketing.System.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class userDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
}
