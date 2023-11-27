package org.alefzero.kcphelper;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

@Provider
@BearerRequired
public class SecurityFilter implements ContainerRequestFilter {
    protected static final Logger logger = LogManager.getLogger();
    private static final String NOT_AUTHORIZED = "Not authorized.";
    private boolean environmentConfigured = false;

    public SecurityFilter() {
        this.environmentConfigured = checkEnvironment();
    }

    private boolean checkEnvironment() {
        boolean result = true;
        for (EnviromentConfig env : EnviromentConfig.values()) {
            if (System.getenv(env.toString()) == null) {
                logger.error("FATAL: Variable {} is not set.", env);
                result = false;
            }
        }
        return result;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (environmentConfigured) {
            String bearerCodeFromOS = EnviromentConfig.getenv(EnviromentConfig.BEARER_CODE);
            String auth = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            if (auth == null || !bearerCodeFromOS.equals(getRequestBearer(auth))) {
                throw new NotAuthorizedException(NOT_AUTHORIZED);
            } 
        } else {
            logger.error("You must configure all environment variables before running.");
            throw new ProcessingException("You must configure all environment variables before running.");
        }

    }

    private String getRequestBearer(String auth) {
        return auth.substring(auth.lastIndexOf(" ") + 1);
    }

}
