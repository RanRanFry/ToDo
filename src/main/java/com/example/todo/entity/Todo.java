package com.example.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Todo {


    private Long id;
    private String userName;
    private String password;
    private String title;
    private String contents;
    private LocalDate createdAt;
    private LocalDate modifiedAt;


    public Todo(String userName, String password,String title, String contents) {
        this.userName = userName;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.createdAt = LocalDate.now();
        this.modifiedAt = LocalDate.now();
    }




}
