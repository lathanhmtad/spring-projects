package com.example.todo.controller;

import com.example.todo.dto.TodoDTO;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private TodoService todoService;

    // Build Add Todo Rest API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO savedTodo = todoService.addTodo(todoDTO);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    // Build Get Todo Rest API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDTO> getTodo(@PathVariable("id") Long todoId) {
        TodoDTO todoDTO = todoService.getTodo(todoId);

        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    // Build Get All Todo Rest API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    // Build Update Todo Rest API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO, @PathVariable("id") Long todoId) {
        TodoDTO updatedTodo = todoService.updateTodo(todoDTO, todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build Delete Todo Rest API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodo(todoId);

        return ResponseEntity.ok("Todo deleted successfully");
    }

    // Build Complete Todo Rest API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDTO> completeTodo(@PathVariable("id") Long todoId) {
        TodoDTO updatedTodo = todoService.completeTodo(todoId);

        return ResponseEntity.ok(updatedTodo);
    }

    // Build In Complete Todo Rest API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDTO> inCompleteTodo(@PathVariable("id") Long todoId) {
        TodoDTO updatedTodo = todoService.inCompleteTodo(todoId);

        return ResponseEntity.ok(updatedTodo);
    }

}
