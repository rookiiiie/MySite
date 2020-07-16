package com.MySiteTest.service;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component("FileService")
public interface FileService {
    public void FileUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile file, Model model,String dirName) throws IOException;
    public void FileDownload(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String filename, Model model,String dirName) throws IOException;
    public void FileDelete(HttpServletRequest request, HttpServletResponse response, String filename, Model model,String dirName) throws FileNotFoundException;
}