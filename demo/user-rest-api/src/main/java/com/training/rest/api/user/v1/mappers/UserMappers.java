package com.training.rest.api.user.v1.mappers;

import com.training.models.User;
import com.training.rest.api.user.v1.pojo.UserInputDataCreateV1;
import com.training.rest.api.user.v1.pojo.UserInputDataUpdateV1;
import com.training.rest.api.user.v1.pojo.UserOutputDataV1;
import com.training.service.tasklist.pojo.UserInputDataCreate;
import com.training.service.tasklist.pojo.UserInputDataUpdate;
import com.training.service.tasklist.pojo.UserOutputData;

public interface UserMappers {

    static UserInputDataUpdate updateUser( UserInputDataUpdateV1 userData){
        UserInputDataUpdate user = new UserInputDataCreate();
        user.setPhone(userData.getPhone());
        user.setEmail(userData.getEmail());
        return user;
    }

    static UserInputDataCreate createUser(UserInputDataCreateV1 userData){
        UserInputDataCreate newUser = new UserInputDataCreate();
        newUser.setName(userData.getName());
        newUser.setPhone(userData.getPhone());
        newUser.setEmail(userData.getEmail());
        newUser.setGender(User.Gender.UNKNOWN);
        return newUser;
    }

    static UserOutputDataV1 exportUser(UserOutputData existingUser){
        UserOutputDataV1 output = new UserOutputDataV1();
        output.setName(existingUser.getName());
        output.setEmail(existingUser.getEmail());
        output.setPhone(existingUser.getPhone());
        return output;
    }
}
