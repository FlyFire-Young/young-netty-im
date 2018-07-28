package org.young.base.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.young.base.entity.User;

/**
 * User: Young
 * Date: 2018/7/24 0024
 * Time: 18:35
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class SecurityContext {

    /**
     * 获得当前登录用户
     * @return
     */
    public static User getCurrentUser() {
        org.springframework.security.core.context.SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx == null || ctx.getAuthentication() == null) {
            return null;
        }
        Object obj = ctx.getAuthentication().getPrincipal();
        return (obj instanceof User) ? (User) obj : null;
    }

}
