package com.whu.sres.lhw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 模板引擎类配置
 * Created by lvhw on 2019/3/10 0:01.
 */
@Controller
@RequestMapping(value = "/template")
public class TemplateController {

    @GetMapping(value = "freemarker1")
    public String sayHello(ModelMap mm) {
        mm.addAttribute("msg", "hello，freemarker1");
        return "hello";
    }

    @GetMapping(value = "freemarker2")
    public ModelAndView sayHello(ModelAndView mv) {
        mv.addObject("msg", "hello，freemarker2");
        mv.setViewName("hello");
        return mv;
    }

    @GetMapping(value = "thymeleaf")
    public ModelAndView sayHelloThymeleaf(ModelAndView mv) {
        mv.addObject("msg", "hello，thymeleaf");
        mv.setViewName("helloleaf");
        return mv;
    }
}
