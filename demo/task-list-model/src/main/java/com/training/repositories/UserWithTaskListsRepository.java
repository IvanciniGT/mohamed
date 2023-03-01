package com.training.repositories;

import com.training.models.UserWithTaskLists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWithTaskListsRepository extends JpaRepository<UserWithTaskLists, String> {
}
