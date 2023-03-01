package com.training.service.tasklist.pojo;

import com.training.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserWithTaskListsOutputData {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private User.Gender gender;

    @Getter @Setter
    private List<TaskListOutputData> taskLists;
}
