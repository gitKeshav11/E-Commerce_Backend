package com.example.E_CommerceProject.Service;

import com.example.E_CommerceProject.Repository.UserRepo;
import com.example.E_CommerceProject.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User registerUser(User user) {
        try
        {
            User newUser = userRepo.save(user);
            System.out.println("User Added to database");
            return newUser;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;    }

    public User loginUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}

