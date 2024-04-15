package com.application.userauth.service;

import com.application.userauth.converter.UserConverter;
import com.application.userauth.entity.UserEntity;
import com.application.userauth.exception.BusinessException;
import com.application.userauth.exception.ErrorModel;
import com.application.userauth.model.UserModel;
import com.application.userauth.repository.UserEntityRepository;
import com.application.userauth.constant.ErrorType;
import com.application.userauth.validation.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserValidator userValidator;

    @Override
    public boolean login(UserModel userModel) throws BusinessException {
        logger.debug("Entering Method Login");
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
            logger.warn("Invalid Login attempt");
            throw new BusinessException(errorList);
        }else {
            result = true;
            logger.info("Login successful");
        }
        logger.debug("Exiting Method Login");

        return result;
    }

    @Override
    public Long register(UserModel userModel) throws BusinessException {
        logger.debug("Entering Method Register");
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
        logger.debug("Exiting Method Register");
        return userEntity1.getUserId();
    }
}
