package com.training.service.tasklist;

import com.training.models.User;
import com.training.repositories.UserRepository;
import com.training.service.tasklist.mappers.UserMappers;
import com.training.service.tasklist.pojo.UserInputDataCreate;
import com.training.service.tasklist.pojo.UserInputDataUpdate;
import com.training.service.tasklist.pojo.UserOutputData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // This allows to use this class as a Singleton
         // Only one instance of this class is going to exists
         // And we can ask Spring for that instance wherever we want
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<UserOutputData> getUsers(){
        List<User> users = userRepository.findAll();
        // A place to add logic in case I need to
        return users.stream()
                .map(UserMappers::exportUser)
                .collect(Collectors.toList());
    }
    public UserOutputData createUser(UserInputDataCreate data){
        User newUser = userRepository.save( UserMappers.createUser(data) );
        // We are sending an email here
        return UserMappers.exportUser(newUser);
    }

    public Optional<UserOutputData> deleteUser(String userName) {
        Optional<User> user = userRepository.findById(userName);
        if(user.isPresent()){
            userRepository.delete(user.orElseThrow());
            return Optional.of(UserMappers.exportUser(user.orElseThrow()));
        }else {               // That means the user does not exists
            return Optional.empty();
        }
    }

    public Optional<UserOutputData> getUser(String userName) {
        Optional<User> user = userRepository.findById(userName);
        if(user.isPresent()){
            return Optional.of(UserMappers.exportUser(user.orElseThrow()));
        }else {               // That means the user does not exists
            return Optional.empty();
        }
    }


    public Optional<UserOutputData> updateUser(String userName, UserInputDataUpdate newData) {
        Optional<User> optionalUser = userRepository.findById(userName);
        if(optionalUser.isPresent()){
            User existingUser = optionalUser.orElseThrow();
            existingUser= userRepository.save( UserMappers.updateUser(existingUser, newData));
            return Optional.of(UserMappers.exportUser(existingUser));
        }else {               // That means the user does not exists
            return Optional.empty();
        }
    }

}
