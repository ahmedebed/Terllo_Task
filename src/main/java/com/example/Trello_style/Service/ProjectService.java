package com.example.Trello_style.Service;

import com.example.Trello_style.DTO.request.ProjectRequest;
import com.example.Trello_style.DTO.respnse.ProjectResponse;
import com.example.Trello_style.Entity.Project;
import com.example.Trello_style.Entity.User;
import com.example.Trello_style.Excptions.ProjectNotFoundExcption;
import com.example.Trello_style.Excptions.UserNotFoundExceptions;
import com.example.Trello_style.Repo.ProjectRepo;
import com.example.Trello_style.Repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;

    public List<ProjectResponse> getAllProjects(long id) {
        User user=userRepo.findById(id)
                .orElseThrow(()->new UserNotFoundExceptions(id));
        List<Project> projects=projectRepo.findByUser(user);
        return projects.stream()
                .map(ProjectResponse::mapToBasicProjectResponse)
                .collect(Collectors.toList());
    }

    public ProjectResponse addProject(long id, ProjectRequest projectRequest) {
        User user=userRepo.findById(id)
                .orElseThrow(()->new UserNotFoundExceptions(id));
        Project project= Project.builder()
                .name(projectRequest.name())
                .description(projectRequest.description())
                .build();
        project.setUser(user);
        projectRepo.save(project);
        return ProjectResponse.mapToBasicProjectResponse(project);
    }

    public ProjectResponse getProjectById(long id, long userId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new UserNotFoundExceptions(userId));
        Project project=projectRepo.findById(id)
                .orElseThrow(()->new ProjectNotFoundExcption(id));
        if (project.getUser().getId()!=user.getId()){
            throw new UserNotFoundExceptions(userId);
        }
        // check if list of tasks is empty if true return em

        return ProjectResponse.mapToDetailesProjectResponse(project);
    }
    @Transactional
    public void deleteProject(long id, long userId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new UserNotFoundExceptions(userId));
        Project project=projectRepo.findById(id)
                .orElseThrow(()->new ProjectNotFoundExcption(id));
        if (project.getUser().getId()!=user.getId()){
            throw new UserNotFoundExceptions(userId);
        }
        if (!project.getTasks().isEmpty()) {
            throw new IllegalStateException("Project cannot be deleted because it has tasks associated with it.");
        }
        projectRepo.delete(project);
    }

    public ProjectResponse updateProject(long id, long userId, ProjectRequest projectRequest) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new UserNotFoundExceptions(userId));
        Project project=projectRepo.findById(id)
                .orElseThrow(()->new ProjectNotFoundExcption(id));
        if (project.getUser().getId()!=user.getId()){
            throw new UserNotFoundExceptions(userId);
        }
        if (projectRequest.name()!=null){
            project.setName(projectRequest.name());
        }
        if (projectRequest.description()!=null){
            project.setDescription(projectRequest.description());
        }
        project.setUser(user);
        projectRepo.save(project);
        return ProjectResponse.mapToBasicProjectResponse(project);
    }
}
