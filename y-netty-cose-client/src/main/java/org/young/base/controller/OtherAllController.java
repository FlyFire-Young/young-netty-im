package org.young.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Young
 * Date: 2018/7/10 0010
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Controller
public class OtherAllController {

    private static final Logger logger = LoggerFactory.getLogger(OtherAllController.class);

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("")
    public String sss() {
        return "redirect:/y/login";
    }


}
