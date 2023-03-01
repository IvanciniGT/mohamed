package com.training.service.tasklist;

import com.training.models.UserWithTaskLists;
import com.training.repositories.UserWithTaskListsRepository;
import com.training.service.tasklist.mappers.UserWithTaskListsMappers;
import com.training.service.tasklist.pojo.UserWithTaskListsOutputData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // This allows to use this class as a Singleton
         // Only one instance of this class is going to exists
         // And we can ask Spring for that instance wherever we want
public class UserWithTaskListsService {

    private final UserWithTaskListsRepository userWithTaskListsRepository;

    public UserWithTaskListsService(UserWithTaskListsRepository userWithTaskListsRepository){
        this.userWithTaskListsRepository = userWithTaskListsRepository;
    }

    public List<UserWithTaskListsOutputData> getUsers(){
        List<UserWithTaskLists> users = userWithTaskListsRepository.findAll();
        // A place to add logic in case I need to
        return users.stream()
                .map(UserWithTaskListsMappers::exportUser)
                .collect(Collectors.toList());
    }
    public Optional<UserWithTaskListsOutputData> getUser(String name) {
        Optional<UserWithTaskLists> user = userWithTaskListsRepository.findById(name);
        if(user.isPresent())
            return Optional.of(UserWithTaskListsMappers.exportUser(user.orElseThrow()));
        else
            return Optional.empty();
    }
}
