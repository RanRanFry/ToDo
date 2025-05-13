package com.example.todo.controller;

import com.example.todo.dto.ToDoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import com.example.todo.service.TodoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    //1. 일정 생성
    @PostMapping
   public ResponseEntity <TodoResponseDto> createTodo(@RequestBody ToDoRequestDto requestDto){
       // 응답(컨트롤러)
       return new ResponseEntity<> (todoService.saveTodo(requestDto), HttpStatus.CREATED);
   }


//   2. 일정 전체 조회(작성자명 이나 일정을 기준으로)
    @GetMapping
    public List <TodoResponseDto> findAllTodos(){

        return todoService.findAllTodos();
    }

    //3. 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity <TodoResponseDto> findTodoById(
            @PathVariable Long id
    ){
        return new ResponseEntity <> (todoService.findTodoById(id), HttpStatus.OK);
    }

}
