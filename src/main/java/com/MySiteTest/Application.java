package com.MySiteTest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@SpringBootApplication(exclude = {})
@RestController
public class Application {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "hello,world";
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .run(args);
        ApplicationHome applicationHome=new ApplicationHome(new Application().getClass());
        File file=applicationHome.getSource().getParentFile();
        System.out.println("file path = "+file.getPath());
    }
}