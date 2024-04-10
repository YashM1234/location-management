package com.application.locationmanagement.converter;

import com.application.locationmanagement.entity.UserEntity;
import com.application.locationmanagement.model.UserModel;
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
