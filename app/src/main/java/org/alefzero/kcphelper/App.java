package org.alefzero.kcphelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("")
public class App {
    protected static final Logger logger = LogManager.getLogger();

    @GET
    @Path("data")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @BearerRequired
    @Path("private")
    public String data() {
        logger.info("Call");
        return "Hello, private!";
    }
}
