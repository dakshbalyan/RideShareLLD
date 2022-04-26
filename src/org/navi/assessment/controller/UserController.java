package org.navi.assessment.controller;

import org.navi.assessment.service.UserService;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public void createUser(String userName, String name, int age, String gender){
        userService.addUser(userName,name,age,gender);
    }

    public void printUserRideStats(){
        userService.printUserRideStats();
    }
}
