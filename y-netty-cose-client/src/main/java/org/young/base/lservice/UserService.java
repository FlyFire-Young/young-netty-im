package org.young.base.lservice;

import org.young.base.entity.User;

/**
 * User: Young
 * Date: 2018/7/24 0024
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public interface UserService {

    /**
     * 新建用户
     *
     * @param username
     * @param password
     * @param phone
     * @param email
     */
    void createUser(String username, String password, String phone, String email);

    /**
     * 用户名检测
     *
     * @param username
     */
    boolean checkUser(String username);

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    User getById(Long id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean deleteById(Long id);
}
