package com.project.Hungerism.service;

import com.project.Hungerism.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;
    
    public User findUserByEmail(String email) throws Exception;

    

    

}
