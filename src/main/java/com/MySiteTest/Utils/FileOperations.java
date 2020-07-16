package com.MySiteTest.Utils;

import com.MySiteTest.Application;
import com.MySiteTest.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class FileOperations {

    public static String join(String[] strs){
        return String.join(",",strs);
    }
//gain all files
    public static File[] gainFile(String dirName) throws FileNotFoundException {
        File file=new File(new FileOperations().getRootDirByName(dirName));
        return file.isDirectory()?file.listFiles():new File[]{file};
    }
//gain filenames:
    public static String gainFilename(String dirName) throws FileNotFoundException {
        File[] files=new FileOperations().gainFile(dirName);
        String[] filename=new String[files.length];
        int i=0;
        for(File file:files)
            filename[i++]=new String(file.getName());
        return String.join(",",filename);
    }
    //gain size:
    public static String gainSize(String dirName) throws FileNotFoundException {
        File[] files=new FileOperations().gainFile(dirName);
        String[] size=new String[files.length];
        int i=0;
        for(File file:files){
            if(file.length()/1024==0){
                size[i++]="1KB";
            }else if(file.length()/1024/1024==0){
                size[i++]=String.valueOf(file.length()/1024)+"KB";
            }else{
                size[i++]=String.valueOf(file.length()/1024/1024)+"MB";
            }
        }

        return String.join(",",size);
    }


    //return classpath absolute path
    public String getRootPath() throws FileNotFoundException {
        return new ApplicationHome(new Application().getClass()).getSource().getParentFile().getPath();
    }
    //return files directory's path
    public String getRootDirByName(String dirName) throws FileNotFoundException {
        return new ApplicationHome(new Application().getClass()).getSource().getParentFile().getPath()+"/"+ dirName+"/";
    }
    //get FileInputStream according to file path
    public FileInputStream getFileInputStream(String dirName,String filename) throws FileNotFoundException {
        return new FileInputStream(new FileOperations().getRootDirByName(dirName)+filename);
    }
    //get FileOutputStream according to file path
    public FileOutputStream getFileOutputStream(String dirName,String filename) throws FileNotFoundException {
        return new FileOutputStream(new FileOperations().getRootDirByName(dirName)+filename);
    }
}
