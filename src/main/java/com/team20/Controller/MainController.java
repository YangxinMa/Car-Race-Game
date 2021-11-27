package com.team20.Controller;

import com.team20.Wrapper.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody User info) {
        // Check the user in db or not: yes return 200, no return 404
        // test user info
        System.out.println(info.username);
        System.out.println(info.password);
    }
}

