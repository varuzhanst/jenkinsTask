package com.example.demo.controller;

import com.example.demo.enitity.TodoItem;
import com.example.demo.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo-items")
public class TodoItemController {
    @Autowired
    private TodoItemService service;

    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return service.getAllTodoItems();
    }

    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
        return service.createTodoItem(todoItem);
    }

    @PutMapping("/{id}")
    public TodoItem updateTodoItem(@PathVariable Long id, @RequestBody TodoItem updatedTodoItem) {
        return service.updateTodoItem(id, updatedTodoItem);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable Long id) {
        service.deleteTodoItem(id);
    }
}