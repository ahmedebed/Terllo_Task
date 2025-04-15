package com.example.Trello_style.Repo;

import com.example.Trello_style.Entity.Project;
import com.example.Trello_style.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long> {
    List<Project> findByUser(User user);
}
