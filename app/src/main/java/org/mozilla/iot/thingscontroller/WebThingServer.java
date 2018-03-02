package org.mozilla.iot.thingscontroller;

import java.io.IOException;

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

    @Override
    public void addMappings() {
        super.addMappings();
    }
}
