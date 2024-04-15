package com.application.userauth.converter;

import com.application.userauth.entity.UserEntity;
import com.application.userauth.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity convertModelToEntity(UserModel userModel){

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userModel.getName());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setPhoneNumber(userModel.getPhoneNumber());

        return userEntity;
    }
}
