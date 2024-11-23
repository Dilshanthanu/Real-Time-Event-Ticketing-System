package com.example.Real_Time.Event.Ticketing.System.Service;

import com.example.Real_Time.Event.Ticketing.System.Entity.user;
import com.example.Real_Time.Event.Ticketing.System.Repo.UserRepo;
import com.example.Real_Time.Event.Ticketing.System.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public String loggedIn(user userEntity) {
        try {
            user foundUser = userRepo.findByEmail(userEntity.getEmail());
            if (foundUser != null) {
                return VarList.RSP_SUCCESS;
            }
            return VarList.RSP_NO_DATA_FOUND;
        }catch (Exception e) {
            return VarList.RSP_ERROR;
        }

    }

    public String Sign(user userEntity) {
        try{
            if (!userRepo.existsByEmail(userEntity.getEmail())){
                userRepo.save(userEntity);
                return VarList.RSP_SUCCESS;
            }else {
                return VarList.RSP_NO_DATA_FOUND;
            }
        }catch (Exception e) {
            return VarList.RSP_ERROR;
        }

    }
}
