package com.application.locationmanagement.service;

import com.application.locationmanagement.converter.UserConverter;
import com.application.locationmanagement.entity.UserEntity;
import com.application.locationmanagement.exception.BusinessException;
import com.application.locationmanagement.exception.ErrorModel;
import com.application.locationmanagement.model.UserModel;
import com.application.locationmanagement.repository.UserEntityRepository;
import com.application.locationmanagement.constant.ErrorType;
import com.application.locationmanagement.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserValidator userValidator;

    @Override
    public boolean login(UserModel userModel) throws BusinessException {
        //check email and password emptiness
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        boolean result = false;
        UserEntity userEntity = userEntityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
        if (userEntity == null) {
            List<ErrorModel> errorList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModel.setMessage("Incorrect email or password!");

            errorList.add(errorModel);
            throw new BusinessException(errorList);
        }else {
            result = true;
        }
        return result;
    }

    @Override
    public Long register(UserModel userModel) throws BusinessException {
        //check email and password emptiness
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        //check if user already exist
        UserEntity ue = userEntityRepository.findByEmail(userModel.getEmail());
        if(null != ue){
            List<ErrorModel> errorList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.ALREADY_EXISTS.toString());
            errorModel.setMessage("User with this email already exist, try another email!");

            errorList.add(errorModel);
            throw new BusinessException(errorList);
        }

        UserEntity userEntity = userConverter.convertModelToEntity(userModel);
        UserEntity userEntity1 = userEntityRepository.save(userEntity);

        return userEntity1.getUserId();
    }
}
