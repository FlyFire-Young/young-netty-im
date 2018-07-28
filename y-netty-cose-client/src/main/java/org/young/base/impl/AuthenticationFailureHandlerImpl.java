package org.young.base.impl;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Young on 2017/3/17.
 */
public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        super.setDefaultFailureUrl("/y/login?error=1");
        super.onAuthenticationFailure(request,response,exception);
    }

    private static String createFailSalt(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        String base = "abcdefghijklmnopqrstuvwxyz1023456789";
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
