package com.example.demo;
import com.example.demo.enitity.TodoItem;
import com.example.demo.repository.TodoItemRepository;
import com.example.demo.service.TodoItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class Demo2ApplicationTests {

    @InjectMocks
    private TodoItemService todoItemService;
    @Mock
    private TodoItemRepository todoItemRepository;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTodoItems() {
        // Arrange
        List<TodoItem> todoItemList = new ArrayList<>();
        todoItemList.add(new TodoItem(1L, "Task 1", "Description 1"));
        todoItemList.add(new TodoItem(2L, "Task 2", "Description 2"));

        when(todoItemRepository.findAll()).thenReturn(todoItemList);

        // Act
        List<TodoItem> result = todoItemService.getAllTodoItems();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateTodoItem() {
        // Arrange
        TodoItem newTodoItem = new TodoItem(null, "New Task", "New Description");
        TodoItem savedTodoItem = new TodoItem(1L, "New Task", "New Description");

        when(todoItemRepository.save(newTodoItem)).thenReturn(savedTodoItem);

        // Act
        TodoItem result = todoItemService.createTodoItem(newTodoItem);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Task", result.getTitle());
        assertEquals("New Description", result.getDescription());
    }

    @Test
    public void testUpdateTodoItem() {
        // Arrange
        Long itemId = 1L;
        TodoItem existingTodoItem = new TodoItem(itemId, "Task 1", "Description 1");
        TodoItem updatedTodoItem = new TodoItem(itemId, "Updated Task", "Updated Description");

        when(todoItemRepository.findById(itemId)).thenReturn(Optional.of(existingTodoItem));
        when(todoItemRepository.save(existingTodoItem)).thenReturn(updatedTodoItem);

        // Act
        TodoItem result = todoItemService.updateTodoItem(itemId, updatedTodoItem);

        // Assert
        assertNotNull(result);
        assertEquals(itemId, result.getId());
        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
    }

    @Test
    public void testDeleteTodoItem() {
        // Arrange
        Long itemId = 1L;

        // Act
        todoItemService.deleteTodoItem(itemId);

        // Assert
        verify(todoItemRepository, times(1)).deleteById(itemId);
    }
}
