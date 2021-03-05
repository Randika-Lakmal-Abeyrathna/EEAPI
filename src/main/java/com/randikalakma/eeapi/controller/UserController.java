package com.randikalakma.eeapi.controller;

import com.randikalakma.eeapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/accountverification/{token}")
    public ResponseEntity<?> activateUser(@PathVariable("token") String token){
        userService.activateUser(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
