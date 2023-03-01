package com.training.service.tasklist.mappers;

import com.training.models.User;
import com.training.service.tasklist.pojo.UserWithTaskListsOutputData;

public interface UserWithTaskListsMappers {

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

    static UserWithTaskListsOutputData exportUser(User existingUser){
        UserWithTaskListsOutputData output = new UserWithTaskListsOutputData();
        output.setName(existingUser.getName());
        output.setEmail(existingUser.getEmail());
        output.setPhone(existingUser.getPhone());
        output.setGender(existingUser.getGender());
        return output;
    }
}
