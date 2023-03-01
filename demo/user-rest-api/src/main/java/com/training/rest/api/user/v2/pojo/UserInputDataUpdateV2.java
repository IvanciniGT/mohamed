package com.training.rest.api.user.v2.pojo;

import com.training.models.User;
import lombok.Getter;
import lombok.Setter;

public class UserInputDataUpdateV2 {

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private User.Gender gender;

}
