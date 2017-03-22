package com.subbu.todoapp.command;

import com.subbu.todoapp.model.Todo;
import com.subbu.todoapp.model.TodoService;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.osgi.service.component.annotations.Reference;

/**
 * Created by subbu on 22/03/17.
 */

@Command(scope="todo", name="add", description = "Add a todo item")
public class TodoAddCommand implements Action {

    @Argument(index = 0, name = "id", required = false, description = "Id of the Todo object", multiValued = false)
    String id;

    @Argument(index = 0, name = "title", required = false, description = "Title of the Todo object", multiValued = false)
    String title;

    @Argument(index = 0, name = "dueDate", required = false, description = "Due Date of the Todo object", multiValued = false)
    String dueDate;

    @Reference
    TodoService todoService;

    @Override
    public Object execute() throws Exception {
        Todo todo = new Todo(id, title, dueDate);
        todoService.add(todo);
        return null;
    }
}
