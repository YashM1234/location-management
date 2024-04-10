package com.application.locationmanagement.service;

import com.application.locationmanagement.exception.BusinessException;
import com.application.locationmanagement.model.UserModel;

public interface UserService {
    boolean login(UserModel userModel) throws BusinessException;
    Long register(UserModel userModel) throws BusinessException;

}
