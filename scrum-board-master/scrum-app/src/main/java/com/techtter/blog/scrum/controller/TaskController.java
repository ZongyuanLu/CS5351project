package com.techtter.blog.scrum.controller;

import com.techtter.blog.scrum.model.Task;
import com.techtter.blog.scrum.model.TaskStatus;
import com.techtter.blog.scrum.model.TaskDTO;
import com.techtter.blog.scrum.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    @ApiOperation(value="View a list of all tasks", response = Task.class, responseContainer = "List")
    public ResponseEntity<?> getAllTasks(){
        try {
            return new ResponseEntity<>(
                    taskService.getAllTasks(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Find a task info by its id", response = Task.class)
    public ResponseEntity<?> getTask(@PathVariable Long id){
        try {
            Optional<Task> optTask = taskService.getTaskById(id);
            if (optTask.isPresent()) {
                return new ResponseEntity<>(
                        optTask.get(),
                        HttpStatus.OK);
            } else {
                return noTaskFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/title")
    @ApiOperation(value="Find a task info by its title", response = Task.class)
    public ResponseEntity<?> getTaskByTitle(@RequestParam String title){
        try {
            Optional<Task> optTask = taskService.getTaskByTitle(title);
            if (optTask.isPresent()) {
                return new ResponseEntity<>(
                        optTask.get(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No task found with a title: " + title, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/todo")
    @ApiOperation(value="Find a list of TODO tasks info by their scrum_id and sprint number",
            response = Task.class,
            responseContainer = "List")
    public ResponseEntity<?> getTODOTasksByScrumIdAndSprintNum(@RequestParam Long id,
                                                               @RequestParam Integer num){
        try {
            return new ResponseEntity<>(
                    taskService.getTasksByTaskStatusScrumIdAndSprintNum(TaskStatus.TODO, id, num),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/inprogress")
    @ApiOperation(value="Find a list of INPROGRESS tasks info by their scrum_id and sprint number",
            response = Task.class,
            responseContainer = "List")
    public ResponseEntity<?> getINPROGRESSTasksByScrumIdAndSprintNum(@RequestParam Long id,
                                                               @RequestParam Integer num){
        try {
            return new ResponseEntity<>(
                    taskService.getTasksByTaskStatusScrumIdAndSprintNum(TaskStatus.INPROGRESS, id, num),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/done")
    @ApiOperation(value="Find a list of DONE tasks info by their scrum_id and sprint number",
            response = Task.class,
            responseContainer = "List")
    public ResponseEntity<?> getDONETasksByScrumIdAndSprintNum(@RequestParam Long id,
                                                               @RequestParam Integer num){
        try {
            return new ResponseEntity<>(
                    taskService.getTasksByTaskStatusScrumIdAndSprintNum(TaskStatus.DONE, id, num),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/worktodo")
    @ApiOperation(value="Find the remaining work of a sprint of a scrum", response = Integer.class)
    public ResponseEntity<?> getWorkToDoByScrumIdAndSprintNum(@RequestParam Long id,
                                                              @RequestParam Integer num){
        try {
            return new ResponseEntity<>(
                    taskService.getWorkTodoByScrumIdAndSprintNum(id, num),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/")
    @ApiOperation(value="Save new task", response = Task.class)
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO){
        try {
            return new ResponseEntity<>(
                    taskService.saveNewTask(taskDTO),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getClass());
            return errorResponse();
        }
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value="Update a task with specific id", response = Task.class)
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        try {
            Optional<Task> optTask = taskService.getTaskById(id);
            if (optTask.isPresent()) {
                return new ResponseEntity<>(
                        taskService.updateTask(optTask.get(), taskDTO),
                        HttpStatus.OK);
            } else {
                return noTaskFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Delete Task with specific id", response = String.class)
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        try {
            Optional<Task> optTask = taskService.getTaskById(id);
            if (optTask.isPresent()) {
                taskService.deleteTask(optTask.get());
                return new ResponseEntity<>(String.format("Task with id: %d was deleted", id), HttpStatus.OK);
            } else {
                return noTaskFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/description/{id}")
    @ApiOperation(value="Find a task's description by its id", response = String.class)
    public ResponseEntity<?> getTaskDescriptionById(@PathVariable Long id){
        try {
            Optional<Task> optTask = taskService.getTaskById(id);
            return optTask.map(task -> new ResponseEntity<>(
                    task.getDescription(),
                    HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("No task found with a id: " + id, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/enquiry/todo")
    @ApiOperation(value="display all todo-tasks", response = String.class)
    public ResponseEntity<?> getAllTODOTasks(){
        try {
            return new ResponseEntity<>(
                    taskService.getAllTODOTasks(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/enquiry/doing")
    @ApiOperation(value="display all todo-tasks", response = String.class)
    public ResponseEntity<?> getAllDOINGTasks(){
        try {
            return new ResponseEntity<>(
                    taskService.getAllDOINGTask(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/enquiry/done")
    @ApiOperation(value="display all todo-tasks", response = String.class)
    public ResponseEntity<?> getAllDONETasks(){
        try {
            return new ResponseEntity<>(
                    taskService.getAllDONETask(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/enquiry/scrumid/{id}")
    @ApiOperation(value="get all tasks by scrum id",
            response = String.class,
            responseContainer = "List")
    public ResponseEntity<?> getTasksByScrumId(@PathVariable Long id){
        try {
            return new ResponseEntity<>(
                    taskService.getTasksByScrumId(id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noTaskFoundResponse(Long id){
        return new ResponseEntity<>("No task found with id: " + id, HttpStatus.NOT_FOUND);
    }

}
