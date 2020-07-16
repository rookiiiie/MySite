package com.MySiteTest.service.Imp;

import com.MySiteTest.Utils.ArticleOperations;
import com.MySiteTest.Utils.FileOperations;
import com.MySiteTest.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class FileServiceImp implements FileService {
    @Autowired
    private FileOperations fileOperations;
    @Autowired
    ArticleOperations articleOperations;
    @Override
    public void FileUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile file, Model model,String dirName) throws IOException {
        if(file.isEmpty()){
            model.addAttribute("msg","未选中任何文件！");
            return;
        }
        String fileName = file.getOriginalFilename();
        byte[] bytes=file.getBytes();
//        System.out.println("上传路径 = "+FileOperations.getFilePath()+fileName);
        FileOutputStream fileOutputStream=fileOperations.getFileOutputStream(dirName,fileName);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        model.addAttribute("msg","上传成功");
        if(dirName.equals("articles")){
            articleOperations.addArticle(new String(bytes,"utf-8"),fileName.substring(0,fileName.lastIndexOf(".")));
        }
    }

    @Override
    public void FileDownload(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String filename, Model model,String dirName) throws IOException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        File file=new File(fileOperations.getRootDirByName(dirName)+filename);
        if(file.exists()&&filename!=""){
            httpServletResponse.reset();    //Clears any data that exists in the buffer as well as the status code, headers.
            httpServletResponse.setHeader("content-type","application/octet-stream");
            httpServletResponse.setHeader("content-Disposition","attachment;filename="+filename);
            OutputStream outputStream=httpServletResponse.getOutputStream();
            FileInputStream fileInputStream=new FileInputStream(file);
            byte[] buff=new byte[1024];
            int len=-1;
            while((len=fileInputStream.read(buff))>0){ //每次读并写入1kb
                outputStream.write(buff,0,len);
            }
            outputStream.close();
            fileInputStream.close();
            httpServletResponse.getOutputStream().close();
        }else{
            model.addAttribute("msg","未找到该文件");
        }
    }
    @Override
    public void FileDelete(HttpServletRequest request, HttpServletResponse response, String filename, Model model,String dirName) throws FileNotFoundException {
        File file=new File(fileOperations.getRootDirByName(dirName)+filename);
        System.out.println(file.getAbsoluteFile().getPath());
        if(!file.exists()){
            model.addAttribute("msg","未找到该文件！");
        }else if(file.delete()){
            if(dirName.equals("articles")){
                articleOperations.deleteArticle(filename.substring(0,filename.lastIndexOf(".")));
            }
            model.addAttribute("msg","删除成功!");
        }else{
            model.addAttribute("msg","删除失败！");
        }
    }

}