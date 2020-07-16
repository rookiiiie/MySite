package com.MySiteTest.controller;

import com.MySiteTest.service.Imp.HomeAndAboutMeServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2020/6/14
 */
@Controller
public class HomeAndAboutMe {
    @Value("#{homeAndAboutMeServiceImp}")
    HomeAndAboutMeServiceImp homeAndAboutMeService;
    @GetMapping(value = "/games/greedySnakeDouble1")
    public String game1(){
        return "greedySnakeDouble1";
    }
    @PostMapping(value = "/home/{type}")
    public void returnQuotes(HttpServletResponse httpServletResponse, @PathVariable String type) throws IOException {
        homeAndAboutMeService.returnQuotes(httpServletResponse,type);
    }
}