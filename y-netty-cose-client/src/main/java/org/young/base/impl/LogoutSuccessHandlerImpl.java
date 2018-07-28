package org.young.base.impl;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.bind.ServletRequestUtils;
import org.young.base.dao.UserDao;
import org.young.base.entity.User;
import org.young.base.utils.YoungUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Young on 2017/3/17.
 */
public class LogoutSuccessHandlerImpl extends SimpleUrlLogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = ServletRequestUtils.getStringParameter(request, "username", "");
        logout(username);
        super.setDefaultTargetUrl("/y/login");
        super.onLogoutSuccess(request, response, authentication);
    }

    private void logout(String username) {
        User user = userDao.getByUsername(username);
        logger.info("LogoutSuccessHandlerImpl start-----:{}", username);
        if (null != user) {
            String name = user.getUsername();
            logger.info("LogoutSuccessHandlerImpl name-----:{}", name);
            Channel client = YoungUtils.map.get(name);
            if (null != client) {
                client.close();
                YoungUtils.map.remove(name);
                YoungUtils.msgMap.remove(name);
            }
        }
    }
    private static String createLogoutSalt(int length) {
        StringBuffer sb = new StringBuffer();
        String base = "abcdefghijklmnopqrstuvwxyz1023456789";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
