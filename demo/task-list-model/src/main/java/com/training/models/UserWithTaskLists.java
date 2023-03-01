package com.training.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // This automatically allow us to store these Objects within a database
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
public class UserWithTaskLists extends User {


    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name="AssignedUsers",
            joinColumns = @JoinColumn(name = "UserName"),
            inverseJoinColumns = @JoinColumn(name = "TaskListId")
    )

    private List<TaskList> taskLists=new ArrayList<>();
}
