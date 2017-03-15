/*
 *
 */
package org.jspare.vertx.samples.todolist;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import org.jspare.vertx.experimental.AutoConfiguration;
import org.jspare.vertx.experimental.AutoConfigurationVerticle;
import org.jspare.vertx.experimental.Module;
import org.jspare.vertx.jpa.experimental.AnnotatedClasses;
import org.jspare.vertx.jpa.experimental.PersistenceJpaModule;
import org.jspare.vertx.web.experimental.Cors;
import org.jspare.vertx.web.experimental.HttpServerModule;

@Cors(
  allowedOriginPattern = "*",
  allowedMethods = { HttpMethod.OPTIONS, HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH},
  allowedHeaders = { "Access-Control-Allow-Origin", "Content-Type", "X-PINGARUNER" }
)
@AnnotatedClasses("org.jspare.vertx.samples.todolist.entity")
@AutoConfiguration({
  @Module(HttpServerModule.NAME),
  @Module(PersistenceJpaModule.NAME)
})
public class TodoVerticle extends AutoConfigurationVerticle {

  @Override
  public void start() throws Exception {

    super.start();
  }

  protected HttpServerOptions options() {
    return new HttpServerOptions().setPort(8000);
  }
}
