package com.mongoplayground.demo.service;

import com.mongoplayground.demo.dto.TodoRequest;
import com.mongoplayground.demo.model.Todo;
import com.mongoplayground.demo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(String id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found: " + id));
    }

    public Todo createTodo(TodoRequest request) {
        Todo todo = new Todo(request.getTitle(), request.isCompleted());
        return todoRepository.save(todo);
    }

    public Todo updateTodo(String id, TodoRequest request) {
        Todo todo = getTodoById(id);
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());
        return todoRepository.save(todo);
    }

    public void deleteTodo(String id) {
        if (!todoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found: " + id);
        }
        todoRepository.deleteById(id);
    }
}
