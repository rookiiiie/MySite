package com.MySiteTest.controller;

import com.MySiteTest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginValidate {
    @Autowired
    private LoginService loginService;
    @GetMapping(value = "/pan")
    public String LoginPage(){
        return "loginIn";
    }
    @RequestMapping(value = "/pan/{dirName}/Login",method = RequestMethod.POST)
    public String LoginValidate(HttpServletRequest request, HttpServletResponse response, String name, String password,String type, Model model, @PathVariable String dirName){
        return loginService.LoginValidate(request,response,name,password,type,model,dirName);
    }
}
