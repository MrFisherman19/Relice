package com.mrfisherman.relice.Configuration.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authException) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password.");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Exception exception = (Exception) httpServletRequest.getAttribute("exception");
        String message = getExceptionMessage(authException, exception);

        writeToOutputStream(httpServletResponse, message);
    }

    private void writeToOutputStream(HttpServletResponse httpServletResponse, String message) throws IOException {
        byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
        httpServletResponse.getOutputStream().write(body);
    }

    private String getExceptionMessage(AuthenticationException authException, Exception exception) {
        String message;
        if (exception != null) {
            if (exception.getCause() != null) {
                message = exception.getCause().toString() + " " + exception.getMessage();
            } else {
                message = exception.getMessage();
            }
        } else {
            if (authException.getCause() != null) {
                message = authException.getCause().toString() + " " + authException.getMessage();
            } else {
                message = authException.getMessage();
            }
        }
        return message;
    }
}
