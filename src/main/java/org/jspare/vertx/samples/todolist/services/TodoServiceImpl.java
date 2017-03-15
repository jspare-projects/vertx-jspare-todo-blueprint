package org.jspare.vertx.samples.todolist.services;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.jspare.vertx.jpa.annotation.RepositoryInject;
import org.jspare.vertx.samples.todolist.entity.Todo;
import org.jspare.vertx.samples.todolist.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulo.ferreira on 15/03/2017.
 */
public class TodoServiceImpl implements  TodoService {

  @RepositoryInject
  private TodoRepository repository;

  @Override
  public void list(Handler<AsyncResult<List<Todo>>> resultHandler) {

    resultHandler.handle(Future.succeededFuture(collect(repository.findAll())));
  }

  @Override
  public void persist(Todo todo, Handler<AsyncResult<Todo>> resultHandler) {

    resultHandler.handle(Future.succeededFuture(repository.save(todo)));
  }

  @Override
  public void update(Long id, Todo todo, Handler<AsyncResult<Todo>> resultHandler) {

    Todo original = repository.findOne(id);
    if(original == null) resultHandler.handle(Future.failedFuture("Not found"));
    resultHandler.handle(Future.succeededFuture(repository.save(original.merge(todo))));
  }

  @Override
  public void persist(List<Todo> todos, Handler<AsyncResult<List<Todo>>> resultHandler) {

    resultHandler.handle(Future.succeededFuture(collect(repository.save(todos))));
  }

  @Override
  public void findById(Long id, Handler<AsyncResult<Todo>> resultHandler) {

    resultHandler.handle(Future.succeededFuture(repository.findOne(id)));
  }

  @Override
  public void remove(Handler<AsyncResult<Void>> resultHandler) {

    repository.deleteAll();
    resultHandler.handle(Future.succeededFuture());
  }

  @Override
  public void remove(Long id, Handler<AsyncResult<Void>> resultHandler) {

    repository.delete(id);
    resultHandler.handle(Future.succeededFuture());
  }

  private <T> List<T> collect(Iterable<T> iterable) {
    List<T> list = new ArrayList<>();
    iterable.forEach(list::add);
    return list;
  }
}
