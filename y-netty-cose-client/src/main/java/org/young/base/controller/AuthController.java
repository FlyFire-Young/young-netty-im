package org.young.base.controller;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.young.base.entity.User;
import org.young.base.security.SecurityContext;
import org.young.base.utils.StringUtils;
import org.young.base.utils.YoungUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * User: Young
 * Date: 2018/7/24 0024
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * 对话聊天
     *
     * @return
     */
    @RequestMapping("/chat")
    public String chat(Model model) {
        User user = SecurityContext.getCurrentUser();
        model.addAttribute("username", user.getUsername());
        return "/yauth/chat";
    }

    /**
     * 头部模版
     *
     * @return
     */
    @RequestMapping("/realMain")
    public String realMain(Model model) {
        User user = SecurityContext.getCurrentUser();
        model.addAttribute("username", user.getUsername());
        return "/yauth/main";
    }

    /**
     * 发送信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String name = ServletRequestUtils.getStringParameter(request, "name", "young");
        String content = ServletRequestUtils.getStringParameter(request, "content", "这是bug");
        content = StringUtils.replaceBlank(content);
        Channel client = YoungUtils.map.get(name);
        if (null != client) {
            client.writeAndFlush(content + "\r\n");
        }
        return "successSend";
    }

    /**
     * 获取信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getChatMsg")
    public void getChatMsg(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String name = ServletRequestUtils.getStringParameter(request, "name", "young");
        Integer size = ServletRequestUtils.getIntParameter(request, "size", 0);
        //让浏览器用utf8来解析返回的数据
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        // 死循环 查询有无数据变化
        while (true) {
            List<String> msg = YoungUtils.msgMap.get(name);
            Thread.sleep(300); // 休眠300毫秒，模拟处理业务等
            if (null == msg) {
                writer.write(JSON.toJSONString("offLine"));
                break;
            }
            if (msg.size() <= 0) {
                Thread.sleep(1300);
                continue;
            }
            if (null == YoungUtils.map.get(name)) {
                writer.write(JSON.toJSONString(msg));
                break;
            }
            if (msg.size() > size) {
                writer.write(JSON.toJSONString(msg));
                break;
            }
            Thread.sleep(300);
        }
        logger.info("real end ----------------");
    }
}
