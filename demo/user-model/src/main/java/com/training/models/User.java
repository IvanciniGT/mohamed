package com.training.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // This automatically allow us to store these Objects within a database
@Table(  // This configure the name of the database TABLE
        name = "users"
)
public class User {

    public enum Gender{
        MALE, FEMALE, OTHER, UNKNOWN
    };

    @Getter @Setter
    @Id
    @Column(length = 50, updatable = false, nullable = false)
    private String name;

    @Getter @Setter
    @Column(length = 15, updatable = true, nullable = true)
    private String phone;

    @Getter @Setter
    @Column(length = 100, updatable = true, nullable = true)
    private String email;

    @Getter @Setter
    @Column( updatable = true, nullable = false)
    private Gender gender=Gender.UNKNOWN;

}
