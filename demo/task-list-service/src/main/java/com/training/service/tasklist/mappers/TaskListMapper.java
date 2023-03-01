package com.training.service.tasklist.mappers;

import com.training.models.Task;
import com.training.models.TaskList;
import com.training.service.tasklist.pojo.TaskListOutputData;
import com.training.service.tasklist.pojo.TaskOutputData;

import java.util.stream.Collectors;

public interface TaskListMapper {
    static TaskListOutputData exportTaskList(TaskList taskList){
        TaskListOutputData toReturn = new TaskListOutputData();
        toReturn.setId(taskList.getId());
        toReturn.setName(taskList.getName());
        toReturn.setTasks(
                taskList.getTasks().stream()
                                   .map( task -> {
                                       TaskOutputData taskOutputData=new TaskOutputData();
                                       taskOutputData.setId(task.getId());
                                       taskOutputData.setName(task.getName());
                                       taskOutputData.setStatus(task.getStatus());
                                       taskOutputData.setTaskListId(taskList.getId());
                                       return taskOutputData;
                                       })
                        .collect(Collectors.toList())
        );
        return toReturn;
    }

    static TaskOutputData exportTask(Task save) {
        return null;
    }
}
