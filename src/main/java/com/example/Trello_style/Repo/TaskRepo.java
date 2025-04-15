package com.example.Trello_style.Repo;

import com.example.Trello_style.Entity.Project;
import com.example.Trello_style.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {
    List<Task> findByProject(Project project);
}
