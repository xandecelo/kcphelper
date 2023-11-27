package org.alefzero.kcphelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
public class App {
    protected static final Logger logger = LogManager.getLogger();
    private DBProvider dbprovider = new DBProvider(
            EnviromentConfig.getenv(EnviromentConfig.JDBC_URL),
            EnviromentConfig.getenv(EnviromentConfig.JDBC_USERNAME),
            EnviromentConfig.getenv(EnviromentConfig.JDBC_PASSWORD),
            EnviromentConfig.getenv(EnviromentConfig.SQL_QUERY));

    @GET
    @Path("data")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @BearerRequired
    @Produces(MediaType.APPLICATION_JSON)
    @Path("phone/{username}")
    public PhoneResponse getPhone(@PathParam("username") String username) {
        return new PhoneResponse(username, dbprovider.getPhoneFor(username));
    }
}
