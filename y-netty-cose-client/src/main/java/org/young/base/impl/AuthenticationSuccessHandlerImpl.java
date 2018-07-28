package org.young.base.impl;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.bind.ServletRequestUtils;
import org.young.base.dao.UserDao;
import org.young.base.entity.User;
import org.young.base.service.CoseService;
import org.young.base.utils.YoungUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Young on 2017/3/17.
 */
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    @Resource
    private CoseService coseService;

    @Autowired
    UserDao userDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = ServletRequestUtils.getStringParameter(request, "y_username", "");
        User user = userDao.getByUsername(username);
        dealChannel(username);
        super.setDefaultTargetUrl("/auth/realMain");
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private void dealChannel (String name) {
        Channel clientCheck = YoungUtils.map.get(name);
        if (null != clientCheck) {
            clientCheck.close();
            YoungUtils.map.remove(name);
        }
        Channel client = coseService.start(name);
        YoungUtils.map.put(name, client);
        logger.info("-------map login end------:{}", YoungUtils.map.size());
    }

    private static String createSalt(int length) {
        Random random = new Random();
        String base = "abcdefghijklmnopqrstuvwxyz1023456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


}
