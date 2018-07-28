package org.young.base.impl;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginUrlAuthenticationEntryPointImpl extends LoginUrlAuthenticationEntryPoint {


    public LoginUrlAuthenticationEntryPointImpl(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
    	if (request.getRequestURI().contains("/admin")) {
    		return "/adminLogin";
    	}
        return getLoginFormUrl();
    }



}
