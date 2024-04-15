package com.application.locationmanagement.converter;

import com.application.locationmanagement.entity.UserEntity;
import com.application.locationmanagement.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Test
    void test_convertModelToEntity(){
        UserModel userModel = new UserModel();
        userModel.setName("Ramesh sharma");
        userModel.setEmail("ramesh@gmail.com");
        userModel.setPassword("Ramesh@123");
        userModel.setPhoneNumber("8975213654");

        UserEntity userEntity = userConverter.convertModelToEntity(userModel);

        Assertions.assertEquals(userModel.getName(), userEntity.getName());
        Assertions.assertEquals(userModel.getEmail(), userEntity.getEmail());
        Assertions.assertEquals(userModel.getPassword(), userEntity.getPassword());
        Assertions.assertEquals(userModel.getPhoneNumber(), userEntity.getPhoneNumber());

    }
}
