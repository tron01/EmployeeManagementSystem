package com.Abhijith.EmployeeManagementSystem.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class EmployeeController {

    @GetMapping("/")
    private  String home(){
        return "home";
    }

}
