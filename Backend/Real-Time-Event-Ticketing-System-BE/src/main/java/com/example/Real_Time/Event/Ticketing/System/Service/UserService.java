package com.example.Real_Time.Event.Ticketing.System.Service;

import com.example.Real_Time.Event.Ticketing.System.Entity.user;
import com.example.Real_Time.Event.Ticketing.System.Repo.UserRepo;
import com.example.Real_Time.Event.Ticketing.System.util.VarList;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public user loggedIn(user userEntity) {
        try {
            user foundUser = userRepo.findByEmail(userEntity.getEmail());
            if (foundUser != null) {
               if (foundUser.getPassword().equals(userEntity.getPassword())) {
                   return foundUser;
               }else {
                   return null;
               }
            }
            return null;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public user Sign(user userEntity) {
        try{
            if (!userRepo.existsByEmail(userEntity.getEmail())){
                userRepo.save(userEntity);
                return  userRepo.findByEmail(userEntity.getEmail());
            }else {
                return null;
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
