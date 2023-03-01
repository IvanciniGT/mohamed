package com.training.rest.api.user.v2.mappers;

import com.training.rest.api.user.v2.pojo.UserInputDataCreateV2;
import com.training.rest.api.user.v2.pojo.UserInputDataUpdateV2;
import com.training.rest.api.user.v2.pojo.UserOutputDataV2;
import com.training.service.tasklist.pojo.UserInputDataCreate;
import com.training.service.tasklist.pojo.UserInputDataUpdate;
import com.training.service.tasklist.pojo.UserOutputData;

public interface UserMappers {

    static UserInputDataUpdate updateUser( UserInputDataUpdateV2 userData){
        UserInputDataUpdate user = new UserInputDataCreate();
        user.setPhone(userData.getPhone());
        user.setEmail(userData.getEmail());
        user.setGender(userData.getGender());
        return user;
    }

    static UserInputDataCreate createUser(UserInputDataCreateV2 userData){
        UserInputDataCreate newUser = new UserInputDataCreate();
        newUser.setName(userData.getName());
        newUser.setPhone(userData.getPhone());
        newUser.setEmail(userData.getEmail());
        newUser.setGender(userData.getGender());
        return newUser;
    }

    static UserOutputDataV2 exportUser(UserOutputData existingUser){
        UserOutputDataV2 output = new UserOutputDataV2();
        output.setName(existingUser.getName());
        output.setEmail(existingUser.getEmail());
        output.setPhone(existingUser.getPhone());
        output.setGender(existingUser.getGender());
        return output;
    }
}
