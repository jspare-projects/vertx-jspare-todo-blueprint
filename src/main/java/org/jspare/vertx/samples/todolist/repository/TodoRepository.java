package org.jspare.vertx.samples.todolist.repository;

import org.jspare.vertx.samples.todolist.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by paulo.ferreira on 15/03/2017.
 */
@Transactional
public interface TodoRepository extends CrudRepository<Todo, Long> {
}
