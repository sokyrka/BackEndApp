package org.sokirka.backendapp.filters;

import org.sokirka.backendapp.entities.User;
import org.sokirka.backendapp.jwt.JwtService;
import org.sokirka.backendapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Eugine Sokirka
 */
public class AuthFilter extends GenericFilterBean {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = httpServletRequest.getHeader("token");

        if (token == null || token.isEmpty()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        User parsedUser = jwtService.parseToken(token);

        if (parsedUser != null) {
            if (!userService.authenticate(parsedUser)) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
