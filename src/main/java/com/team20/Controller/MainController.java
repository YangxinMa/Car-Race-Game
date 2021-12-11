package com.team20.Controller;

import com.team20.Wrapper.RecordWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    private static List<RecordWrapper> users = new ArrayList<>();

//    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.OK)
//    public String login(@RequestBody String username) {
//        for (RecordWrapper user : users) {
//            if (user.user.equals(username)) {
//                return username;
//            }
//        }
//        RecordWrapper newUser = new RecordWrapper(username);
//        users.add(newUser);
//        return username;
//    }
//
//    @GetMapping("/rank")
//    public List<RecordWrapper> getRank(){
//        return users;
//    }
}

