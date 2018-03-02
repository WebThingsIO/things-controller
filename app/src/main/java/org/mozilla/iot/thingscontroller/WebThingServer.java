package org.mozilla.iot.thingscontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

/**
 * Web Thing Server.
 *
 * An embedded web server for serving local UI and exposing the controller itself as a web thing.
 */
public class WebThingServer extends RouterNanoHTTPD {

    private final static int PORT = 8080;

    public WebThingServer() throws IOException {
        super(PORT);
        addMappings();
        System.out.println("Web Thing Server running on http://localhost:" + PORT + "/ \n");
    }

    public static class ThingDescriptionHandler extends DefaultHandler {
      @Override
      public String getText() {
          String text = "{"
            + "\"name\": \"Things Controller\","
            + "\"type\": \"thing\","
            + "\"description\": \"A Web of Things controller from Mozilla\""
            + "}";
          return text;
      }

      public String getMimeType() {
          return "application/json";
      }

      @Override
      public NanoHTTPD.Response.IStatus getStatus() {
          return NanoHTTPD.Response.Status.OK;
      }

      public NanoHTTPD.Response get(UriResource uriResource) {
          String text = getText();
          ByteArrayInputStream input = new ByteArrayInputStream(text.getBytes());
          int size = text.getBytes().length;
          return NanoHTTPD.newFixedLengthResponse(getStatus(), getMimeType(), input, size);
      }
    }

    @Override
    public void addMappings() {
        super.addMappings();
        addRoute("/", ThingDescriptionHandler.class);
    }
}
