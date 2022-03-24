package ru.otus.spring.acl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class AccessController {

    @RequestMapping(value = "/error403")
    public ModelAndView accesssDenied(Principal user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("msg", "Пользователь " + user.getName() + ".");
        }

        model.setViewName("error403");
        return model;
    }
}
