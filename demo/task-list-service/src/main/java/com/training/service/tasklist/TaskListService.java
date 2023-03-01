package com.training.service.tasklist;

import com.training.models.Task;
import com.training.models.TaskList;
import com.training.models.UserWithTaskLists;
import com.training.repositories.TaskListRepository;
import com.training.repositories.TaskRepository;
import com.training.repositories.UserWithTaskListsRepository;
import com.training.service.tasklist.mappers.TaskListMapper;
import com.training.service.tasklist.pojo.TaskListOutputData;
import com.training.service.tasklist.pojo.TaskOutputData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // This allows to use this class as a Singleton
         // Only one instance of this class is going to exists
         // And we can ask Spring for that instance wherever we want
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final UserWithTaskListsRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskListService(UserWithTaskListsRepository userRepository, TaskListRepository taskListRepository, TaskRepository taskRepository){
        this.userRepository=userRepository;
        this.taskListRepository = taskListRepository;
        this.taskRepository = taskRepository;
    }

    public List<TaskListOutputData> getTaskLists() {
        List<TaskList> taskLists = this.taskListRepository.findAll();
        return taskLists.stream().map( TaskListMapper::exportTaskList )
                                 .collect(Collectors.toList());
    }

    public Optional<TaskListOutputData> createTaskList(String userName, String name){
        TaskList newTaskList = new TaskList();
        newTaskList.setName(name);
        Optional<UserWithTaskLists> user = userRepository.findById(userName);
        if(user.isPresent()) {
            newTaskList.getUsers().add(user.orElseThrow());
            return Optional.ofNullable(TaskListMapper.exportTaskList(taskListRepository.save(newTaskList)));
        }
        return Optional.empty();
    }

    public Optional<TaskListOutputData> deleteTaskList(Long id) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if(taskList.isPresent()){
            taskListRepository.delete(taskList.orElseThrow());
            return Optional.ofNullable(TaskListMapper.exportTaskList(taskList.orElseThrow()));
        }
        return Optional.empty();
    }

    public Optional<TaskListOutputData> getTaskList(Long id) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if(taskList.isPresent()){
            return Optional.ofNullable(TaskListMapper.exportTaskList(taskList.orElseThrow()));
        }
        return Optional.empty();
    }

    public Optional<TaskListOutputData> updateTaskList(Long id, String newName) {
        Optional<TaskList> optionalTaskList = taskListRepository.findById(id);
        if(optionalTaskList.isPresent()){
            TaskList taskList= optionalTaskList.orElseThrow();
            taskList.setName(newName);
            return Optional.ofNullable(TaskListMapper.exportTaskList(taskListRepository.save(taskList)));
        }
        return Optional.empty();
    }

    public Optional<TaskListOutputData> assignUserToTaskList(String userName, Long taskListId){
        Optional<TaskList> optionalTaskList = taskListRepository.findById(taskListId);
        Optional<UserWithTaskLists> user = userRepository.findById(userName);

        if(optionalTaskList.isPresent() && user.isPresent()){
            TaskList taskList= optionalTaskList.orElseThrow();
            taskList.getUsers().add(user.orElseThrow());
            return Optional.ofNullable(TaskListMapper.exportTaskList(taskListRepository.save(taskList)));
        }
        return Optional.empty();
    }
    public Optional<TaskListOutputData> removeUserFromTaskList(String userName, Long taskListId){
        Optional<TaskList> optionalTaskList = taskListRepository.findById(taskListId);
        Optional<UserWithTaskLists> user = userRepository.findById(userName);

        if(optionalTaskList.isPresent() && user.isPresent()){
            TaskList taskList= optionalTaskList.orElseThrow();
            taskList.getUsers().remove(user.orElseThrow());
            return Optional.ofNullable(TaskListMapper.exportTaskList(taskListRepository.save(taskList)));
        }
        return Optional.empty();
    }

    public Optional<TaskOutputData> createTask(Long taskListId, String name){
        Optional<TaskList> optionalTaskList = taskListRepository.findById(taskListId);

        if(optionalTaskList.isPresent()){
            TaskList taskList= optionalTaskList.orElseThrow();

            Task newTask = new Task();
            newTask.setName(name);
            newTask.setTaskList(taskList);

            return Optional.ofNullable(TaskListMapper.exportTask(taskRepository.save(newTask)));
        }
        return Optional.empty();
    }


    public Optional<TaskOutputData> deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            taskRepository.delete(task.orElseThrow());
            return Optional.ofNullable(TaskListMapper.exportTask(task.orElseThrow()));
        }
        return Optional.empty();
    }

    public Optional<TaskOutputData> getTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            return Optional.ofNullable(TaskListMapper.exportTask(task.orElseThrow()));
        }
        return Optional.empty();
    }

    public Optional<TaskOutputData> updateTaskName(Long id, String newName) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()){
            Task task = optionalTask.orElseThrow();
            task.setName(newName);
            return Optional.ofNullable(TaskListMapper.exportTask(taskRepository.save(task)));
        }
        return Optional.empty();
    }
    public Optional<TaskOutputData> updateTaskStatus(Long id, Task.Status newStatus) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()){
            Task task = optionalTask.orElseThrow();
            task.setStatus(newStatus);
            return Optional.ofNullable(TaskListMapper.exportTask(taskRepository.save(task)));
        }
        return Optional.empty();    }



}
