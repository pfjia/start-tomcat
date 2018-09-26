package top.pfjia.core;

import java.io.IOException;

public class StaticResourceProcessor {

  public void process(HttpRequest httpRequest, HttpResponse httpResponse) {
    try {
      httpResponse.sendStaticResource();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}