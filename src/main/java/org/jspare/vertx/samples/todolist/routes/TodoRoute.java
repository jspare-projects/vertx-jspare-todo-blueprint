package org.jspare.vertx.samples.todolist.routes;

import io.vertx.core.AsyncResult;
import org.jspare.core.annotation.Inject;
import org.jspare.vertx.samples.todolist.entity.Todo;
import org.jspare.vertx.samples.todolist.services.TodoService;
import org.jspare.vertx.web.annotation.handler.Handler;
import org.jspare.vertx.web.annotation.handling.Parameter;
import org.jspare.vertx.web.annotation.method.Delete;
import org.jspare.vertx.web.annotation.method.Get;
import org.jspare.vertx.web.annotation.method.Patch;
import org.jspare.vertx.web.annotation.method.Post;
import org.jspare.vertx.web.annotation.subrouter.SubRouter;
import org.jspare.vertx.web.handler.APIHandler;

import java.util.function.Function;
import java.util.stream.Collectors;

@SubRouter("/todos")
public class TodoRoute extends APIHandler {

  @Inject
  private TodoService service;

  @Get
  @Handler
  public void list() {
    service.list(ar -> handler(ar, list ->
      list.stream().map(this::transform).collect(Collectors.toList())
    ));
  }

  @Delete
  @Handler
  public void deleteAll() {
    service.remove(this::handler);
  }

  @Post
  @Handler
  public void persist(Todo todo) {
    service.persist(todo, ar -> handler(ar, this::transform));
  }

  @Patch("/:entryid")
  @Handler
  public void update(@Parameter("entryid") Long id, Todo todo) {
    service.update(id, todo, ar -> handler(ar, this::transform));
  }

  @Get("/:entryid")
  @Handler
  public void findOne(@Parameter("entryid") Long id) {
    service.findById(id, ar -> handler(ar, this::transform));
  }

  @Delete("/:entryid")
  @Handler
  public void deleteOne(@Parameter("entryid") Long id) {
    service.remove(id, this::handler);
  }

  private <T> void handler(AsyncResult<T> ar) {
    if (ar.succeeded()) {
      success(ar.result());
      return;
    }
    conflict();
  }

  private <T, R> void handler(AsyncResult<T> ar, Function<T, R> transform) {
    if (ar.succeeded()) {
      success(transform.apply(ar.result()));
      return;
    }
    conflict();
  }

  private Todo transform(Todo todo) {
    return todo.url(req.absoluteURI().contains(todo.id().toString()) ? req.absoluteURI() : req.absoluteURI() + "/"  + todo.id());
  }
}
