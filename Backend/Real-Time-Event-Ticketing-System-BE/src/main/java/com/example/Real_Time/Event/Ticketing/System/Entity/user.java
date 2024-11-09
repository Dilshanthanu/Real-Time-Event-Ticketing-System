package com.example.Real_Time.Event.Ticketing.System.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class user {

    @Id
    @GeneratedValue()
    private int id;
    private String name;
    private String email;
    private String password;

}
