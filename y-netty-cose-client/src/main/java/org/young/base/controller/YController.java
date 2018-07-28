package org.young.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Young
 * Date: 2018/7/24 0024
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Controller
@RequestMapping("/y")
public class YController extends AbstractController{

    /**
     * 登录首页
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "yc/login";
    }


    /**
     * 注册首页
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "yc/register";
    }

    /**
     * 注册用户
     *
     * @return
     */
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = ServletRequestUtils.getStringParameter(request, "username");
        String password = ServletRequestUtils.getStringParameter(request, "password");
        String phone_number = ServletRequestUtils.getStringParameter(request, "phone_number");
        String email = ServletRequestUtils.getStringParameter(request, "email");
        userService.createUser(username, password, phone_number, email);
        return "redirect:/y/login?result=suc";
    }

    /**
     * 注册用户名检测
     *
     * @return
     */
    @RequestMapping(value = "/usernameCheck", method = RequestMethod.POST)
    @ResponseBody
    public boolean usernameCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = ServletRequestUtils.getStringParameter(request, "username");
        return !userService.checkUser(username);
    }

    /**
     * 注册手机号检测
     *
     * @return
     */
    @RequestMapping(value = "/phoneCheck", method = RequestMethod.POST)
    @ResponseBody
    public boolean phoneCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = ServletRequestUtils.getStringParameter(request, "phone_number");
        return !userService.checkUser(username);
    }

    /**
     * 注册邮箱检测
     *
     * @return
     */
    @RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
    @ResponseBody
    public boolean emailCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = ServletRequestUtils.getStringParameter(request, "email");
        return !userService.checkUser(username);
    }

    /**
     * 登录用户名检测
     *
     * @return
     */
    @RequestMapping(value = "/usernameCheckL", method = RequestMethod.POST)
    @ResponseBody
    public boolean usernameCheckL(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String username = ServletRequestUtils.getStringParameter(request, "y_username");
        return userService.checkUser(username);
    }

}
