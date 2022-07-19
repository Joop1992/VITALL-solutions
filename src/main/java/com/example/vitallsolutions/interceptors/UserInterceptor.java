package com.example.vitallsolutions.interceptors;

import com.example.vitallsolutions.SpringContext;
import com.example.vitallsolutions.model.user.User;
import com.example.vitallsolutions.model.user.service.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        UserRepository userRepository = SpringContext.getBean(UserRepository.class);

        ClaimsWrapper claims = new ClaimsWrapper(SecurityContextHolder.getContext().getAuthentication());

        if(!userRepository.findById(claims.getSub()).isPresent()) {
            System.out.println("Encountered unknown user: Creating new inactive user account");
            userRepository.saveAndFlush(new User(claims.getSub(), claims.getName(), claims.getEmail()));
        }

        return true;
    }
}
