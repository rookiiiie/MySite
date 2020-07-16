package com.MySiteTest.Utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserCookies {
    public boolean checkCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("name")){

            }
        }
        return false;
    }
    public void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String password){
        Cookie cookie1=new Cookie("name",name);
        Cookie cookie2=new Cookie("password",password);

    }
}
