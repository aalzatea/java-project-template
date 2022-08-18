package com.aalzatea.todo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ToDoController {

    @GetMapping
    public String getSomeText() {
        return "Some Text";
    }
}
