package com.example.Real_Time.Event.Ticketing.System.Service;

import com.example.Real_Time.Event.Ticketing.System.Entity.user;
import com.example.Real_Time.Event.Ticketing.System.Repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public boolean loggedIn(user email , user password){
        if (userRepo.existsByEmail(email)){
            if (userRepo.existsByPassword(password)){
                return true;
            }
            return false;
        }
        return false;
    }
}
