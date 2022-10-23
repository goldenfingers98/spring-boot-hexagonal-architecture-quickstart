package tn.ksoftwares.rest.controller.apdater;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.ksoftwares.domain.model.pojo.User;

public interface UserController {

    @PostMapping("/add")
    ResponseEntity<Void> addUser(@RequestBody User user);
    
}
