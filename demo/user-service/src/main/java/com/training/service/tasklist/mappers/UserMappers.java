package com.training.service.tasklist.mappers;

import com.training.models.User;
import com.training.service.tasklist.pojo.UserInputDataCreate;
import com.training.service.tasklist.pojo.UserInputDataUpdate;
import com.training.service.tasklist.pojo.UserOutputData;

public interface UserMappers {

    static User updateUser( User user, UserInputDataUpdate userData){
        user.setPhone(userData.getPhone());
        user.setEmail(userData.getEmail());
        user.setGender(userData.getGender());
        return user;
    }

    static User createUser(UserInputDataCreate userData){
        User newUser = new User();
        newUser.setName(userData.getName());
        newUser.setPhone(userData.getPhone());
        newUser.setEmail(userData.getEmail());
        newUser.setGender(userData.getGender());
        return newUser;
    }

    static UserOutputData exportUser(User existingUser){
        UserOutputData output = new UserOutputData();
        output.setName(existingUser.getName());
        output.setEmail(existingUser.getEmail());
        output.setPhone(existingUser.getPhone());
        output.setGender(existingUser.getGender());
        return output;
    }
}
