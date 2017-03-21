package com.subbu.todoapp.service;

import com.subbu.todoapp.model.Todo;
import com.subbu.todoapp.model.TodoService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

/**
 * Created by subbu on 22/03/17.
 */

@Component(service=TodoRestService.class, property={"service.exported.interfaces=*",
        "service.exported.configs=org.apache.cxf.rs",
        "org.apache.cxf.rs.address=/rest/todo"})
@Path("")
public class TodoRestService {

    @Reference
    TodoService todoService;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("{id}")
    public Response getTodo(@PathParam("id") Integer id) {
        Todo todo = todoService.get(id);
        return todo == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(todo).build();
    }

    @POST
    public Response addTodo(Todo todo) {
        todoService.add(todo);
        URI taskURI = uriInfo.getRequestUriBuilder().path(TodoService.class, "getTodo").build(todo.getId());
        return Response.created(taskURI).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Todo> getTodos() {
        return todoService.list();
    }

    @PUT
    @Path("{id}")
    public void updateTodo(@PathParam("id") Integer id, Todo todo) {
        todo.setId(id);
        todoService.update(todo);
    }

    @DELETE
    @Path("{id}")
    public void deleteTodo(@PathParam("id") Integer id) {
        todoService.remove(id);
    }
}
