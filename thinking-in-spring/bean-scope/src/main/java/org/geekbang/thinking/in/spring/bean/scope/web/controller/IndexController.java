package org.geekbang.thinking.in.spring.bean.scope.web.controller;


import org.geekbang.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页：Spring web mvc Controller
 *
 * **/

@Controller
public class IndexController {

    @Autowired
    private User user;

    @GetMapping("/")
    public String index(Model model){
        //作用域范围： page->request->session->application
        //ApplicationScope: User 对象存在， ServletContext上下文名称： scopedTarget.user==新生成的Bean名称
        model.addAttribute("userObject",user);
//        model.addAttribute("user",user);
        return "index";
    }
}
