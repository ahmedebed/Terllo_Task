package com.example.Trello_style.Service;

import com.example.Trello_style.DTO.request.TaskRequest;
import com.example.Trello_style.DTO.request.UpdateTaskStatuesRequest;
import com.example.Trello_style.DTO.respnse.TaskResponse;
import com.example.Trello_style.Entity.Project;
import com.example.Trello_style.Entity.Task;
import com.example.Trello_style.Entity.User;
import com.example.Trello_style.Enum.Status;
import com.example.Trello_style.Excptions.ProjectNotFoundExcption;
import com.example.Trello_style.Excptions.TaskNotFoundException;
import com.example.Trello_style.Excptions.UnauthorizedAccessException;
import com.example.Trello_style.Repo.ProjectRepo;
import com.example.Trello_style.Repo.TaskRepo;
import com.example.Trello_style.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final ProjectRepo projectRepo;
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskResponse getTaskById(long pid, long id, long userId) {
        validateProjectOwnership(pid, userId);
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return TaskResponse.mapToTaskResponse(task);

    }
    public TaskResponse addTask(long pid, long userId, TaskRequest taskRequest) {
        validateProjectOwnership(pid, userId);
        Project project = projectRepo.findById(pid)
                .orElseThrow(() -> new ProjectNotFoundExcption(pid));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ProjectNotFoundExcption(userId));
        Task task = Task.builder()
                .title(taskRequest.title())
                .description(taskRequest.description())
                .build();
        task.setStatus(Status.TODO);
        task.setProject(project);
        task.setUser(user);

        taskRepo.save(task);
        return TaskResponse.mapToTaskResponse(task);
    }
    public void validateProjectOwnership(long projectId, long userId)  {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundExcption(projectId));

        if (project.getUser().getId() != userId) {
            throw new UnauthorizedAccessException("This project does not belong to the user");
        }
    }


    public List<TaskResponse> getAllTasks(long pid, long userId) {
        validateProjectOwnership(pid, userId);
        Project project = projectRepo.findById(pid)
                .orElseThrow(() -> new ProjectNotFoundExcption(pid));
        List<Task> tasks = taskRepo.findByProject(project);
        return tasks.stream()
                .map(TaskResponse::mapToTaskResponse)
                .toList();
    }

    public TaskResponse updateTaskStatus(long pid, long id, long userId, UpdateTaskStatuesRequest status) {
        validateProjectOwnership(pid,userId);
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        if (status.status() == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        task.setStatus(Status.valueOf(status.status()));

        taskRepo.save(task);
        return TaskResponse.mapToTaskResponse(task);
    }

    public void deleteTask(long pid, long id, long userId) {
        validateProjectOwnership(pid, userId);
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        if (task.getProject().getId() != pid) {
            throw new UnauthorizedAccessException("This task does not belong to the project");
        }
        taskRepo.delete(task);
    }
}
