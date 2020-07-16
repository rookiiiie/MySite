package com.MySiteTest.service;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public interface LoginService {
    public String LoginValidate(HttpServletRequest request, HttpServletResponse response, String name, String password,String type, Model model,String dirName);
}
