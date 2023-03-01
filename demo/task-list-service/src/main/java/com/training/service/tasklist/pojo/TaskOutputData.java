package com.training.service.tasklist.pojo;

import com.training.models.Task;
import com.training.models.User;
import lombok.Getter;
import lombok.Setter;

public class TaskOutputData {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Task.Status status;

    @Getter @Setter
    private Long taskListId;
}
