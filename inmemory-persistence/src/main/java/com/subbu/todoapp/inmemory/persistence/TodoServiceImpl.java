package com.subbu.todoapp.inmemory.persistence;

import com.subbu.todoapp.model.Todo;
import com.subbu.todoapp.model.TodoService;
import org.osgi.service.component.annotations.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by subbu on 22/03/17.
 */

@Component
public class TodoServiceImpl implements TodoService {

    ConcurrentHashMap<Integer, Todo> todoItems = new ConcurrentHashMap<>();

    @Override
    public void add(Todo todo) {
        int id = getId();
        todo.setId(id);
        todoItems.put(id,todo);
    }

    @Override
    public Collection<Todo> list() {
        return todoItems.values();
    }

    @Override
    public Todo get(Integer id) {
        return todoItems.get(id);
    }

    @Override
    public void remove(Integer id) {
        todoItems.remove(id);
    }

    @Override
    public void update(Todo todo) {
        todoItems.put(todo.getId(), todo);
    }

    private int getId(){
        int id = 0;
        if(todoItems.size()==0) {
            id = 1;
        } else {
            List<Integer> keys = Arrays.asList(todoItems.keySet().toArray(new Integer[todoItems.keySet().size()]));
            Collections.sort(keys, Integer::compareTo);
            id = keys.get(keys.size()-1);
        }
        return id;
    }
}
