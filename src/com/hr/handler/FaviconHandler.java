package com.hr.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * �ղ�ͼ��
 */
@Controller("favicon")
public class FaviconHandler {

    @RequestMapping("/favicon.ico")
    public String logo(){
        return null;
    }
}
