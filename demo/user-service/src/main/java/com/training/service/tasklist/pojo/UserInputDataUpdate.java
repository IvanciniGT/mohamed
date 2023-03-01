package com.training.service.tasklist.pojo;

import com.training.models.User;
import lombok.Getter;
import lombok.Setter;

public class UserInputDataUpdate {

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private User.Gender gender;

}
