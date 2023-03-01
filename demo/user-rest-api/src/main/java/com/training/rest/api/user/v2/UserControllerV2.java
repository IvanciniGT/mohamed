package com.training.rest.api.user.v2;

import com.training.rest.api.user.v2.mappers.UserMappers;
import com.training.rest.api.user.v2.pojo.UserInputDataCreateV2;
import com.training.rest.api.user.v2.pojo.UserOutputDataV2;
import com.training.service.tasklist.UserService;
import com.training.service.tasklist.pojo.UserOutputData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2")
public class UserControllerV2 {

    private final UserService userService;

    public UserControllerV2(UserService userService){ // Spring is going to inject a USerRepository
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserOutputDataV2>> getAllUsers(){
        List<UserOutputData> users = userService.getUsers();
        return new ResponseEntity<>(users.stream()
                                        .map( UserMappers::exportUser )
                                        .collect(Collectors.toList()),
                                    HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserOutputDataV2> createUser(@RequestBody UserInputDataCreateV2 userData){
        UserOutputData newUser = userService.createUser(UserMappers.createUser(userData));
        return new ResponseEntity<>(UserMappers.exportUser(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{name}")
    public ResponseEntity<UserOutputDataV2> deleteUser(@PathVariable("name") String userName){
        Optional<UserOutputData> user = userService.deleteUser(userName);
        if(user.isPresent()){ // That means the user exists
            return new ResponseEntity<>(UserMappers.exportUser(user.orElseThrow()), HttpStatus.OK);
        }else {               // That means the user does not exists
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<UserOutputDataV2> getUser(@PathVariable("name") String userName){
        Optional<UserOutputData> user = userService.getUser(userName);
        if(user.isPresent()){ // That means the user exists
            return new ResponseEntity<>(UserMappers.exportUser(user.orElseThrow()), HttpStatus.OK);
        }else {               // That means the user does not exists
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{name}")
    public ResponseEntity<UserOutputDataV2> updateUser(@PathVariable("name") String userName,
                                           @RequestBody UserOutputDataV2 userData){
        Optional<UserOutputData> user = userService.updateUser(userName, UserMappers.updateUser(userData));
        if(user.isPresent()){
            return new ResponseEntity<>(UserMappers.exportUser(user.orElseThrow()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
