package org.jspare.vertx.samples.todolist.services;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import org.jspare.core.annotation.Component;
import org.jspare.vertx.samples.todolist.entity.Todo;

import java.util.List;

/**
 * Created by paulo.ferreira on 15/03/2017.
 */
@VertxGen
@Component
public interface TodoService {

  void list(Handler<AsyncResult<List<Todo>>> resultHandler);

  void persist(Todo todo, Handler<AsyncResult<Todo>> resultHandler);

  void update(Long id, Todo todo, Handler<AsyncResult<Todo>> resultHandler);

  void persist(List<Todo> todo, Handler<AsyncResult<List<Todo>>> resultHandler);

  void findById(Long id, Handler<AsyncResult<Todo>> resultHandler);

  void remove(Handler<AsyncResult<Void>> resultHandler);

  void remove(Long id, Handler<AsyncResult<Void>> resultHandler);
}
