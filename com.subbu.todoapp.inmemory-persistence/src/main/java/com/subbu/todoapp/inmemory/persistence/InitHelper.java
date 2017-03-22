package com.subbu.todoapp.inmemory.persistence;

import com.subbu.todoapp.model.Todo;
import com.subbu.todoapp.model.TodoService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by subbu on 22/03/17.
 */

@Component
public class InitHelper {

    Logger LOG = LoggerFactory.getLogger(InitHelper.class);

    @Reference
    TodoService todoService;

    @Activate
    public void addDemoTasks() {
        try {
            Todo todo = new Todo("Just a sample task", "Some more info", new SimpleDateFormat("dd-MMM-YYYY").format(new Date()));
            todoService.add(todo);
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
        }
    }
}
