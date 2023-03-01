package com.training.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // This automatically allow us to store these Objects within a database
@Table(  // This configure the name of the database TABLE
        name = "tasks"
)
public class Task {

    public enum Status{
        PENDING, IN_PROGRESS, DONE
    };

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Getter @Setter
    @Column(length = 50, updatable = false, nullable = false)
    private String name;


    @Getter @Setter
    @Column( updatable = true, nullable = false)
    private Status status = Status.PENDING;

    @Getter @Setter
    @ManyToOne
    @JoinColumn (updatable = false, nullable = false)
    private TaskList taskList;

}
