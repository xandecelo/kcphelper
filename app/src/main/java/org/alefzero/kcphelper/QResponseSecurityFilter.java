package org.alefzero.kcphelper;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

public class QResponseSecurityFilter implements ContainerResponseFilter {
    protected static final Logger logger = LogManager.getLogger();

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        logger.info("After Qilter.");
        responseContext.setStatus(HttpServletResponse.SC_FORBIDDEN);

    }

}
