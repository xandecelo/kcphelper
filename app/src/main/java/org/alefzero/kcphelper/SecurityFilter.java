package org.alefzero.kcphelper;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;

@WebFilter(urlPatterns = "/private")
public class SecurityFilter implements Filter {

    protected static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        logger.info("Filter.");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String auth = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (auth == null || !(auth.equals("Bearer 12345678"))) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            logger.info("Blocked.");
        }

        chain.doFilter(httpRequest, response);

    }
}
