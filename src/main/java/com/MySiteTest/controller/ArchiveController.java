package com.MySiteTest.controller;

import com.MySiteTest.service.Imp.ArchiveServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created on 2020/6/25
 */
@Controller
public class ArchiveController {
    @Value("#{archiveServiceImp}")
    ArchiveServiceImp archiveService;
    @PostMapping(value = "/getArchive")
    public void getArchive(HttpServletResponse response) throws IOException {
        archiveService.getArchive(response);
    }
    @PostMapping(value = "/getArticle")
    public void getArticle(HttpServletRequest request,HttpServletResponse response) throws IOException {
        archiveService.getArticle(request,response);
    }
    @PostMapping(value = "/getArticleById")
    public void getArticleById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        archiveService.getArticleById(request,response);
    }
}
