package org.young.base.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.young.base.entity.User;

/**
 * Created by Young on 2016/9/17.
 */
@Service
public class UserDetailsServiceImpl extends AbstractService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("start login:{}", "开始");
        User user = userDao.getByUsername(username);
        logger.info("start login 2:{}", "开始2");
        if (user == null) {
            throw new UsernameNotFoundException("user.login.error");
        }
        return user;
    }
}
