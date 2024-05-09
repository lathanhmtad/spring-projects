package com.example.todo.service;

import com.example.todo.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    TodoDTO addTodo(TodoDTO todoDTO);

    TodoDTO getTodo(Long id);

    List<TodoDTO> getAllTodos();

    TodoDTO updateTodo(TodoDTO todoDTO, Long id);

    void deleteTodo(Long todoId);

    TodoDTO completeTodo(Long todoId);

    TodoDTO inCompleteTodo(Long todoId);
}
