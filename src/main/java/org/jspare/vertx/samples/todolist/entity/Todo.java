package org.jspare.vertx.samples.todolist.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jspare.vertx.utils.DataObjectConverter;

import javax.persistence.*;

/**
 * Created by paulo.ferreira on 15/03/2017.
 */
@Accessors(fluent = true)
@Entity
@Table(name = "todolist")
@Data
@DataObject
public class Todo {

  public Todo() {
  }

  public Todo(JsonObject json) {
    DataObjectConverter.fromJson(json, this);
  }

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "TITLE", length = 255)
  private String title;
  private boolean completed = false;
  //Order is a H2's reserved word
  @Column(name = "TASK_ORDER")
  private int order;
  @Transient
  private String url =  "";


  public Todo(Long id, String title, Boolean completed, Integer order, String url) {
    this.id = id;
    this.title = title;
    this.completed = completed;
    this.order = order;
    this.url = url;
  }

  public Todo merge(Todo todo) {
    return new Todo(id,
      getOrElse(todo.title, title),
      getOrElse(todo.completed, completed),
      getOrElse(todo.order, order),
      url);
  }

  private <T> T getOrElse(T value, T defaultValue) {
    return value == null ? defaultValue : value;
  }
}
