package org.example.projectspring.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projectspring.entity.MyUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

//        boolean isAdmin = authentication.getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Integer userId = ((MyUser) userDetails).getId();;
//
//
//        // Перенаправьте пользователя на страницу профиля, включив идентификатор пользователя в URL
//        if (userId != null)
//        {
//            if (isAdmin) {
//            setDefaultTargetUrl("/admin?id=" + userId);
//        } else {
//            setDefaultTargetUrl("/profile?id=" + userId);
//            }
//
//        } else {
//            // Обработка случая, если идентификатор пользователя не доступен
//            // Например, перенаправление на другую страницу или вывод сообщения об ошибке
//        }
//        super.onAuthenticationSuccess(request, response, authentication);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            setDefaultTargetUrl("/admin");
        } else {
            setDefaultTargetUrl("/profile");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}