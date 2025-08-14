package com.backend.shop.presentation.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.user.UserRequest;
import com.backend.shop.applications.interfaces.IUserService;
import com.backend.shop.domains.ResponseMessage;

@RestController
@RequestMapping("${application.api.prefix}/user-management")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody UserRequest request){
        userService.createUser(request);
        return ResponseEntity.ok(new ResponseMessage());
    }
}
