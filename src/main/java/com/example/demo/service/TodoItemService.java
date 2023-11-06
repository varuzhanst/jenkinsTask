package com.example.demo.service;

import com.example.demo.enitity.TodoItem;
import com.example.demo.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemRepository repository;

    public List<TodoItem> getAllTodoItems() {
        return repository.findAll();
    }

    public TodoItem createTodoItem(TodoItem todoItem) {
        return repository.save(todoItem);
    }

    public TodoItem updateTodoItem(Long id, TodoItem updatedTodoItem) {
        TodoItem todoItem = repository.findById(id).orElse(null);
        if (todoItem != null) {
            todoItem.setTitle(updatedTodoItem.getTitle());
            todoItem.setDescription(updatedTodoItem.getDescription());
            return repository.save(todoItem);
        }
        return null;
    }

    public void deleteTodoItem(Long id) {
        repository.deleteById(id);
    }
}