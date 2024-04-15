package com.application.userauth.service;

import com.application.userauth.exception.BusinessException;
import com.application.userauth.model.UserModel;

public interface UserService {
    boolean login(UserModel userModel) throws BusinessException;
    Long register(UserModel userModel) throws BusinessException;

}
