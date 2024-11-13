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

    public boolean loggedIn(user userEntity) {
        user foundUser = userRepo.findByEmail(userEntity.getEmail());
        if (foundUser != null) {
            return foundUser.getPassword().equals(userEntity.getPassword());
        }
        return false;
    }
}
