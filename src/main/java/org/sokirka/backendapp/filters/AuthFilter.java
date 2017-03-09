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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Eugine Sokirka
 */

@WebFilter(urlPatterns = {"/user/*", "/role/*"})
public class AuthFilter extends GenericFilterBean {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = getToken(httpServletRequest);

        if (token == null || token.isEmpty()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        User parsedUser = jwtService.parseToken(token);

        if (parsedUser == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (!userService.authenticate(parsedUser)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getTokenFromHeader(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("token");
    }

    private String getTokenFromQuery(HttpServletRequest httpServletRequest) {
        String token = null;
        String query = httpServletRequest.getQueryString();
        if (query != null) {
            if (query.contains("token=")) {
                token = query.split("=")[1];//TODO Make wider
            }
        }
        return token;
    }

    private String getTokenFromCookie(HttpServletRequest httpServletRequest) {
        String token = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //TODO Implement this
            }
        }
        return token;
    }

    private String getToken(HttpServletRequest httpServletRequest) {
        String token = getTokenFromHeader(httpServletRequest);
        if (isNotEmptyToken(token))
            return token;
        token = getTokenFromQuery(httpServletRequest);
        if (isNotEmptyToken(token))
            return token;
        token = getTokenFromCookie(httpServletRequest);
        return token;
    }

    private boolean isNotEmptyToken(String token) {
        return !(token == null || token.isEmpty());
    }
}
