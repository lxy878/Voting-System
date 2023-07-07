package com.assignment.vs.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignment.vs.domain.UserInfo;
import com.assignment.vs.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserInfo saveOrGet(UserInfo user){
        Optional<UserInfo> ou = userRepository.findByName(user.getName());
        if(!ou.isPresent()){
            return userRepository.save(user);
        }else{
            return ou.get();
        }
    }

    public UserInfo getUser(Long id){
        Optional<UserInfo> ou = userRepository.findById(id);
        if(!ou.isPresent()) return null;
        return ou.get();
    }
}
