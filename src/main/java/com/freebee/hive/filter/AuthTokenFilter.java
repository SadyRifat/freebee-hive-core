package com.freebee.hive.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freebee.hive.dto.response.BaseResponse;
import com.freebee.hive.dto.response.ErrorResponse;
import com.freebee.hive.enums.ERole;
import com.freebee.hive.model.Consumer;
import com.freebee.hive.service.JWTService;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@Slf4j
public class AuthTokenFilter extends AbstractAuthenticationProcessingFilter {
    public AuthTokenFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
        Authentication authentication = null;
        JWTService jwtService = new JWTService();
        String token = jwtService.parseJwt(httpServletRequest);
        if (token == null || token.isEmpty()) {
            authentication = new UsernamePasswordAuthenticationToken(new Consumer("",
                    new HashSet<>(List.of(ERole.ROLE_ANONYMOUS))), "");
            return getAuthenticationManager().authenticate(authentication);
        } else if (jwtService.validateJwtToken(token)) {
            Gson gson = new Gson();
            String subjectDataFromJwtToken = jwtService.getSubjectDataFromJwtToken(token);
            authentication = new UsernamePasswordAuthenticationToken(gson.fromJson(subjectDataFromJwtToken, Consumer.class), "");
            return getAuthenticationManager().authenticate(authentication);
        } else {
            unsuccessfulAuthentication(httpServletRequest, httpServletResponse, new AuthenticationServiceException("Token validation failed"));
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseResponse baseResponse = new BaseResponse(null, HttpServletResponse.SC_UNAUTHORIZED, new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage()));

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getOutputStream().write(objectMapper.writeValueAsString(baseResponse).getBytes());
    }

}
