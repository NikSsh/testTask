package com.my.testproject.controller.api;

import com.my.testproject.model.businessLayer.User;
import com.my.testproject.model.businessLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        User user = userService.getUserById(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(user);
    }
}
