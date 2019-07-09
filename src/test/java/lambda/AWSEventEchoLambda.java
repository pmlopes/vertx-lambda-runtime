package lambda;

import io.vertx.core.eventbus.Message;
import xyz.jetdrone.vertx.lambda.Lambda;

public class AWSEventEchoLambda implements Lambda<Object> {

  @Override
  public void handle(Message event) {
    System.out.println(event.body());
    event.reply(null);
  }
}
