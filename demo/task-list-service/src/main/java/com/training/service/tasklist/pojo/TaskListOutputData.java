package com.training.service.tasklist.pojo;

import com.training.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TaskListOutputData {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private List<String> userNames;

    @Getter @Setter
    private List<TaskOutputData> tasks;
}
