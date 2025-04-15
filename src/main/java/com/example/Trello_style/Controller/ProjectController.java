package com.example.Trello_style.Controller;

import com.example.Trello_style.DTO.request.ProjectRequest;
import com.example.Trello_style.DTO.respnse.ProjectResponse;
import com.example.Trello_style.Service.GlobalService;
import com.example.Trello_style.Service.ProjectService;
import com.example.Trello_style.Validation.OnCreate;
import com.example.Trello_style.Validation.OnUpdate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final GlobalService globalService;
    private final ProjectService projectService;
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(HttpServletRequest request){
        long id= globalService.extractUserIdFromToken(request);
        List<ProjectResponse> projectResponses=projectService.getAllProjects(id);
        return ResponseEntity.ok(projectResponses);
    }
    @PostMapping
    public ResponseEntity<ProjectResponse> addProject(
            @Validated(OnCreate.class) @RequestBody ProjectRequest projectRequest
            ,HttpServletRequest request){
        long id= globalService.extractUserIdFromToken(request);
        ProjectResponse projectResponse=projectService.addProject(id,projectRequest);
        return ResponseEntity.ok(projectResponse);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable long id,
                                                          HttpServletRequest request){
        long userId=globalService.extractUserIdFromToken(request);
        ProjectResponse projectResponse=projectService.getProjectById(id,userId);
        return ResponseEntity.ok(projectResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable long id,
                                               HttpServletRequest request){
        long userId=globalService.extractUserIdFromToken(request);
        projectService.deleteProject(id,userId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable long id,
                                                          @Validated(OnUpdate.class) @RequestBody ProjectRequest projectRequest,
                                                          HttpServletRequest request){
        long userId=globalService.extractUserIdFromToken(request);
        ProjectResponse projectResponse=projectService.updateProject(id,userId,projectRequest);
        return ResponseEntity.ok(projectResponse);
    }


}
