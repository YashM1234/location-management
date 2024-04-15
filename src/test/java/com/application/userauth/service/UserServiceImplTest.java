package com.application.userauth.service;

import com.application.userauth.converter.UserConverter;
import com.application.userauth.entity.UserEntity;
import com.application.userauth.exception.BusinessException;
import com.application.userauth.exception.ErrorModel;
import com.application.userauth.model.UserModel;
import com.application.userauth.repository.UserEntityRepository;
import com.application.userauth.validation.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserEntityRepository userEntityRepository;

    @Mock
    private UserConverter userConverter;

    @Test
    void test_login_error(){
        UserModel userModel = new UserModel();
        List<ErrorModel> errorModelList = new ArrayList<>();

        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("INVALID_REQUEST");
        errorModel.setMessage("User Model or Email or Password cannot be empty");
        errorModelList.add(errorModel);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);

        Assertions.assertThrows(BusinessException.class, () -> {
            userService.login(userModel);
        });
    }

    @Test
    void test_login_with_wrong_credential() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setEmail("ramesh@gmail.com");
        userModel.setPassword("Ramesh@123");
        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = null;

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(userEntityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword())).thenReturn(userEntity);

        Assertions.assertThrows(BusinessException.class, () -> {
            userService.login(userModel);

        });
    }

    @Test
    void test_login_with_correct_credential() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setEmail("ramesh@gmail.com");
        userModel.setPassword("Ramesh@123");
        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("ramesh@gmail.com");
        userEntity.setPassword("Ramesh@123");

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(userEntityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword())).thenReturn(userEntity);

        boolean result = userService.login(userModel);

        Assertions.assertTrue(result);
    }

    @Test
    void test_register_error(){
        UserModel userModel = new UserModel();
        List<ErrorModel> errorModelList = new ArrayList<>();

        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("INVALID_REQUEST");
        errorModel.setMessage("Email or Password cannot be empty");
        errorModelList.add(errorModel);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);

        Assertions.assertThrows(BusinessException.class, () -> {
            userService.register(userModel);
        });
    }

    @Test
    void test_register_with_existing_user() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setEmail("ramesh@gmail.com");
        userModel.setPassword("Ramesh@123");
        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("ramesh@gmail.com");

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(userEntityRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);

        Assertions.assertThrows(BusinessException.class, () -> {
            userService.register(userModel);

        });
    }

    @Test
    void test_register_with_new_user() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setEmail("ramesh@gmail.com");
        userModel.setPassword("Ramesh@123");
        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = null;

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setEmail("ramesh@gmail.com");
        userEntity1.setPassword("Ramesh@123");

        UserEntity userEntity2 = new UserEntity();
        userEntity1.setEmail("ramesh@gmail.com");
        userEntity1.setPassword("Ramesh@123");
        userEntity2.setUserId(1L);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(userEntityRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);

        Mockito.when(userConverter.convertModelToEntity(userModel)).thenReturn(userEntity1);
        Mockito.when(userEntityRepository.save(userEntity1)).thenReturn(userEntity2);

        Long userId = userService.register(userModel);
        Assertions.assertEquals(1, userId);

    }
}
