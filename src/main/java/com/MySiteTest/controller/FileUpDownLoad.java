package com.MySiteTest.controller;

import com.MySiteTest.service.FileService;
import com.MySiteTest.service.ReturnFileModelService;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 1)如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
 * 例如：本来应该到success.jsp页面的，则其显示success.
 * 2)如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
 * 3)如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 */
@Controller
public class FileUpDownLoad {
    @Value("#{fileServiceImp}")
    private FileService fileService;
    @Value("#{returnFileModelImp}")
    private ReturnFileModelService returnFileModelService;
    @RequestMapping(value = "/{dirName}/repository",method = {RequestMethod.POST})
    public String index(Model model, @PathVariable String dirName) throws FileNotFoundException {
        returnFileModelService.getFiles(dirName,model);
        return "repository";
    }
//    @ResponseBody
    @RequestMapping(value = "/pan/{dirName}/downloadFile",method = {RequestMethod.POST})
    public void Download(HttpServletRequest request, HttpServletResponse response, String filename, Model model, @PathVariable String dirName) throws IOException {
        fileService.FileDownload(request,response,filename,model,dirName);
    }
    @RequestMapping(value = "/pan/{dirName}/uploadFile",method = {RequestMethod.POST})
    public String Upload(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file, Model model, @PathVariable String dirName) throws IOException {
        fileService.FileUpload(request,response,file,model,dirName);
        return "forward:/"+dirName+"/repository";
    }
    @RequestMapping(value = "/pan/{dirName}/deleteFile",method = {RequestMethod.POST})
    public String Delete(HttpServletRequest request, HttpServletResponse response, String filename, Model model, @PathVariable String dirName) throws FileNotFoundException {
        fileService.FileDelete(request,response,filename,model,dirName);
        return "forward:/"+dirName+"/repository";
    }
}