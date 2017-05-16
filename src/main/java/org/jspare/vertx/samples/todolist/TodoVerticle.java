/*
 *
 */
package org.jspare.vertx.samples.todolist;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import org.jspare.vertx.autoconfiguration.AutoConfiguration;
import org.jspare.vertx.autoconfiguration.AutoConfigurationVerticle;
import org.jspare.vertx.autoconfiguration.Resource;
import org.jspare.vertx.jpa.AnnotatedClasses;
import org.jspare.vertx.jpa.PersistenceJpaModule;
import org.jspare.vertx.web.module.Cors;
import org.jspare.vertx.web.module.HttpServerModule;

@Cors(
  allowedOriginPattern = "*",
  allowedMethods = {HttpMethod.OPTIONS, HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH},
  allowedHeaders = {"Access-Control-Allow-Origin", "Content-Type", "X-PINGARUNER"}
)
@AnnotatedClasses("org.jspare.vertx.samples.todolist.entity")
@AutoConfiguration({
  @Resource(HttpServerModule.class),
  @Resource(PersistenceJpaModule.class)
})
public class TodoVerticle extends AutoConfigurationVerticle {

  protected HttpServerOptions options() {
    return new HttpServerOptions().setPort(8000);
  }
}
