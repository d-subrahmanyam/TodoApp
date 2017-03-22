package com.subbu.todoapp.model;

import javax.jws.WebService;
import java.util.Collection;

/**
 * This is a service contract for the TodoService
 *
 * Created by subbu on 21/03/17.
 */

@WebService
public interface TodoService {

    /**
     * Add a new Todo item to the repo
     *
     * @param todo
     */
    public void add(Todo todo);

    /**
     * return a Collection of all the Todo items available in the repo
     *
     * @return
     */
    public Collection<Todo> list();

    /**
     * return a Todo item given the id
     *
     * @param id
     * @return
     */
    public Todo get(Integer id);

    /**
     * Remove the Todo item corresponding to the given id
     *
     * @param id
     */
    public void remove(Integer id);

    /**
     * Update the given Todo item
     *
     * @param todo
     */
    public void update(Todo todo);
}
