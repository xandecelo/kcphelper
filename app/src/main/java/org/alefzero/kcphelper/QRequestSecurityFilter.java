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
public class QRequestSecurityFilter implements ContainerRequestFilter {
    protected static final Logger logger = LogManager.getLogger();
    private static final String BEARED_CODE_OS_ENV = "BEARER_CODE";
    private static final String NOT_AUTHORIZED = "Not authorized.";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.info("Qilter.");
        String bearerCodeFromOS = System.getenv(BEARED_CODE_OS_ENV);
        if (bearerCodeFromOS == null) {
            throw new ProcessingException("Authorization not configured at server.");
        }
        String auth = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (auth == null) {
            throw new NotAuthorizedException(NOT_AUTHORIZED);
        } else {
            String bearer = auth.substring(auth.lastIndexOf(" ") + 1);
            if (!bearerCodeFromOS.equals(bearer)) {
                throw new NotAuthorizedException(NOT_AUTHORIZED);
            }

        }
    }

}
