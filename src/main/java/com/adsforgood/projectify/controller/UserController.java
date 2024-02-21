package com.adsforgood.projectify.controller;

import com.adsforgood.projectify.dto.UserDto;
import com.adsforgood.projectify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/User")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/finduserbyeap")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findUserByEmailAndPassword(@RequestBody UserDto userDto){
        try {
            return ResponseEntity.ok().body(userService.findAnUserByEmailAndPassword(userDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/saveuser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        try {
            return ResponseEntity.ok().body(userService.saveUser(userDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/modifyuser")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> modifyuser(@RequestBody UserDto userDto){

        try {
            return ResponseEntity.ok().body(userService.modifyUser(userDto));
        }catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteuser")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteuser(@RequestParam("id") Long userId){

        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok(userId);
        }catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
