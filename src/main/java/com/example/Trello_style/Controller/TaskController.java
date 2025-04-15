package com.example.Trello_style.Controller;
import com.example.Trello_style.DTO.request.TaskRequest;
import com.example.Trello_style.DTO.request.UpdateTaskStatuesRequest;
import com.example.Trello_style.DTO.respnse.TaskResponse;
import com.example.Trello_style.Service.GlobalService;
import com.example.Trello_style.Service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("user-api/{pid}/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final GlobalService globalService;
    private final TaskService taskService;
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable long pid,
                                                    @PathVariable long id,
                                                           HttpServletRequest request){
        long userId=globalService.extractUserIdFromToken(request);
        TaskResponse taskResponse=taskService.getTaskById(pid,id,userId);
        return ResponseEntity.ok(taskResponse);
    }
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(@PathVariable long pid,
                                                    HttpServletRequest request){
        long userId=globalService.extractUserIdFromToken(request);
       List<TaskResponse> taskResponse=taskService.getAllTasks(pid,userId);
        return ResponseEntity.ok(taskResponse);
    }
    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@PathVariable long pid,
                                                @Valid
                                                @RequestBody TaskRequest taskRequest,
                                                HttpServletRequest request){
        long userId=globalService.extractUserIdFromToken(request);
        TaskResponse newTask=taskService.addTask(pid,userId,taskRequest);
        return ResponseEntity.ok(newTask);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable long pid,
                                                          @PathVariable long id,
                                                          @RequestBody UpdateTaskStatuesRequest status,
                                                          HttpServletRequest request){
        long userId=globalService.extractUserIdFromToken(request);
        TaskResponse taskResponse=taskService.updateTaskStatus(pid,id,userId,status);
        return ResponseEntity.ok(taskResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long pid,
                                            @PathVariable long id,
                                            HttpServletRequest request){
        long userId=globalService.extractUserIdFromToken(request);
        taskService.deleteTask(pid,id,userId);
        return ResponseEntity.noContent().build();
    }


}
