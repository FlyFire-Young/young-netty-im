package org.young.base.dao;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.young.base.entity.User;

/**
 * User: Young
 * Date: 2018/7/24 0024
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Repository
public class UserDao extends AbstractDao<User> {

    public User getByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return get("where name = ? or phone = ? or email = ?", username, username, username);
    }

    public boolean existUserName(String userName) {
        return exists("where name = ? or phone = ? or email = ?", userName, userName, userName);
    }
}
