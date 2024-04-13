package com.application.locationmanagement.validation;

import com.application.locationmanagement.constant.ErrorType;
import com.application.locationmanagement.exception.ErrorModel;
import com.application.locationmanagement.model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {
    public List<ErrorModel> validateRequest(UserModel userModel) {

        List<ErrorModel> errorModelList = new ArrayList<>();

        // Check if userModel is null
        if (userModel == null) {
            // Handle null userModel
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.INVALID_REQUEST.toString());
            errorModel.setMessage("User model cannot be null");
            errorModelList.add(errorModel);
        } else {
            // Check if email is null or empty
            if (ObjectUtils.isEmpty(userModel.getEmail())) {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setCode(ErrorType.NOT_EMPTY.toString());
                errorModel.setMessage("Email cannot be empty");
                errorModelList.add(errorModel);
            }

            // Check if password is null or empty
            if (ObjectUtils.isEmpty(userModel.getPassword())) {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setCode(ErrorType.NOT_EMPTY.toString());
                errorModel.setMessage("Password cannot be empty");
                errorModelList.add(errorModel);
            }
        }

        return errorModelList;
    }
}
