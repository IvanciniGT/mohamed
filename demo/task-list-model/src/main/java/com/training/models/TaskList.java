package com.training.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // This automatically allow us to store these Objects within a database
@Table(  // This configure the name of the database TABLE
        name = "tasklists"
)
public class TaskList {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Getter @Setter
    @Column(length = 50, updatable = false, nullable = false)
    private String name;

    @Getter @Setter
    @ManyToMany(mappedBy = "taskLists")
    private List<UserWithTaskLists> users=new ArrayList<>();

    @Getter @Setter
    @OneToMany(mappedBy = "taskList")
    private List<Task> tasks=new ArrayList<>();

}
