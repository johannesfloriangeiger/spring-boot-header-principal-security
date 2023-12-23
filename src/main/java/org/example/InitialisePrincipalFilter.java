package org.example;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.example.model.Principal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InitialisePrincipalFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final var httpServletRequest = (HttpServletRequest) servletRequest;
        final var authentication = Principal.from(httpServletRequest);
        final var securityContext = new SecurityContextImpl(authentication);
        SecurityContextHolder.setContext(securityContext);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}