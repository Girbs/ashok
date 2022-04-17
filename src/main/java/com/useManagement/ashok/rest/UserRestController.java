package com.useManagement.ashok.rest;

import com.useManagement.ashok.bindings.UserForm;
import com.useManagement.ashok.entity.Users;
import com.useManagement.ashok.services.Impl.UserServiceMgmtImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserServiceMgmtImpl userServiceMgmt;

    @PostMapping(value="/user/new")
    public String saveUser(@RequestBody UserForm userForm){
        System.out.println(userForm);
        return userServiceMgmt.saveUser(userForm);
    }
}
