package com.techtter.blog.scrum.controller;

import com.techtter.blog.scrum.model.*;
import com.techtter.blog.scrum.service.ScrumService;
import com.techtter.blog.scrum.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scrums")
@RequiredArgsConstructor
@CrossOrigin
public class ScrumController {

    private final ScrumService scrumService;
    private final UserService userService;

    @GetMapping("/")
    @ApiOperation(value="View a list of all Scrum boards", response = Scrum.class, responseContainer = "List")
    public ResponseEntity<?> getAllScrums(){
        try {
            return new ResponseEntity<>(
                    scrumService.getAllScrumBoards(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value="Find a Scrum board info by its id", response = Scrum.class)
    public ResponseEntity<?> getScrum(@PathVariable Long id){
        try {
            Optional<Scrum> optScrum = scrumService.getScrumById(id);
            if (optScrum.isPresent()) {
                return new ResponseEntity<>(
                        optScrum.get(),
                        HttpStatus.OK);
            } else {
                return noScrumFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

/*    @GetMapping("/{title}")
    @ApiOperation(value="Find a Scrum board info by its title", response = Scrum.class)
    public ResponseEntity<?> getScrumByTitle(@PathVariable String title){
        try {
            Optional<Scrum> optScrum = scrumService.getScrumByTitle(title);
            if (optScrum.isPresent()) {
                return new ResponseEntity<>(
                        optScrum.get(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No scrum found with a title: " + title, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }*/

    @PostMapping("/create")
    @ApiOperation(value="Save new Scrum board", response = Scrum.class) //create
    public ResponseEntity<?> createScrum(@RequestBody AddScrumDTO addScrumDTO){

        List<Scrum> list = scrumService.getAllScrumBoards();

        Long id = list.get(list.size() - 1).getId() + 1;
        System.out.println("id" + id);
        ScrumDTO scrumDTO = new ScrumDTO(id, addScrumDTO.getTitle(), addScrumDTO.getAuthor(), addScrumDTO.getDuration());
        String phone = addScrumDTO.getPhone();

        System.out.println(phone);
        Optional<User> user = userService.getScrumUserByPhone(phone);

        //System.out.println(user.get().getId());
        try {
            System.out.println(phone);
            return new ResponseEntity<>(
                    scrumService.saveNewScrumWithUserId(user.get().getId(), scrumDTO),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResponse();
        }
    }

    @PutMapping("/updateScrum/{id}")
    @ApiOperation(value="Update a Scrum board with specific id", response = Scrum.class)
    public ResponseEntity<?> updateScrum(@PathVariable Long id, @RequestBody ScrumDTO scrumDTO){
        try {
            Optional<Scrum> optScrum = scrumService.getScrumById(id);
            if (optScrum.isPresent()) {
                return new ResponseEntity<>(
                        scrumService.updateScrum(optScrum.get(), scrumDTO),
                        HttpStatus.OK);
            } else {
                return noScrumFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @DeleteMapping("/delete{id}")
    @ApiOperation(value="Delete Scrum board with specific id", response = String.class)
    public ResponseEntity<?> deleteScrum(@PathVariable Long id){
        try {
            Optional<Scrum> optScrum = scrumService.getScrumById(id);
            if (optScrum.isPresent()) {
                scrumService.deleteScrum(optScrum.get());
                return new ResponseEntity<>(
                        String.format("Scrum with id: %d was deleted", id),
                        HttpStatus.OK);
            } else {
                return noScrumFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/enquiry/{scrumId}/tasks/")
    @ApiOperation(value="View a list of all tasks for a Scrum with provided id", response = Task.class, responseContainer = "List")
    public ResponseEntity<?> getAllTasksInScrum(@PathVariable Long scrumId){
         try {
            Optional<Scrum> optScrum = scrumService.getScrumById(scrumId);
            if (optScrum.isPresent()) {
                return new ResponseEntity<>(
                        optScrum.get().getTasks(),
                        HttpStatus.OK);
            } else {
                return noScrumFoundResponse(scrumId);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/enquiry/{id}/{user_id}/createTasks/")
    @ApiOperation(value="Save new Task and assign it to Scrum board", response = Scrum.class)
    public ResponseEntity<?> createTaskAssignedToScrum(@PathVariable Long id, @PathVariable Integer user_id, @RequestBody TaskDTO taskDTO){
        try {
            return new ResponseEntity<>(
                    scrumService.addNewTaskToScrum(id, user_id, taskDTO),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResponse();
        }
    }

    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noScrumFoundResponse(Long id){
        return new ResponseEntity<>("No scrum found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
