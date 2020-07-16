package com.MySiteTest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created on 2020/6/25
 */
public interface ArchiveService {
    public void getArchive(HttpServletResponse response) throws IOException;
    public void getArticle(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public void getArticleById(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
