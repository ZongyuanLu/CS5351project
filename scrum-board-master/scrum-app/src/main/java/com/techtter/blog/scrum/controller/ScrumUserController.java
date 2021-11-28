package com.techtter.blog.scrum.controller;

import com.techtter.blog.scrum.model.ScrumUserDTO;
import com.techtter.blog.scrum.model.ScrumUser;
import com.techtter.blog.scrum.service.ScrumUserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/scrumUser")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9000")
public class ScrumUserController {

    private final ScrumUserService scrumUserService;

//    @GetMapping("")
//    @ApiOperation(value="Find a Scrum board info by its title", response = ScrumUser.class)
//    public ResponseEntity<?> getScrumUserByScrumId(@RequestParam Long scrum_id){
//        try {
//            Optional<ScrumUser> optScrumUser = scrumUserService.getScrumUserByScrumId(scrum_id);
//            if (optScrumUser.isPresent()) {
//                return new ResponseEntity<>(
//                        optScrumUser.get(),
//                        HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("No scrumUser found with a scrumid: " + scrum_id, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return errorResponse();
//        }
//    }

    @PostMapping("/join/{scrumid}/{phone}")
    @ApiOperation(value="Save new Scrum board", response = ScrumUser.class) //join in
    public ResponseEntity<?> createScrumUser(@PathVariable int scrumid, @PathVariable String phone){
        try {
            return new ResponseEntity<>(
                    scrumUserService.saveNewScrumUserByScrumIdUserId((long)scrumid, phone),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}