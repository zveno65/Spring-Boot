package ru.plotnikov.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.plotnikov.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class MySimpleUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {

        boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();

        User user = (User) authentication.getPrincipal();


        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
            }
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
            }
        }

        if (isAdmin) {
            response.sendRedirect("/admin");
        }
        else
        if (isUser) {
            response.sendRedirect("/user?id=" + user.getId());
        } else {
            throw new IllegalStateException();
        }
    }
}