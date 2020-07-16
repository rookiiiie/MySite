package com.MySiteTest.service.Imp;

import com.MySiteTest.Utils.FileOperations;
import com.MySiteTest.pojo.Quote;
import com.MySiteTest.pojo.Recommendation;
import com.MySiteTest.pojo.Type;
import com.MySiteTest.service.HomeAndAboutMeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2020/6/29
 */
@Service
public class HomeAndAboutMeServiceImp implements HomeAndAboutMeService {
    @Autowired
    FileOperations fileOperations;
    @Override
    public void returnQuotes(HttpServletResponse response,String type) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        FileInputStream fileInputStream = fileOperations.getFileInputStream("home", type+".txt");
        byte[] bytes = new byte[1024];
        int length = 0;
        String content = "";
        while ((length = fileInputStream.read(bytes)) != -1) {
            content += new String(bytes, 0, length);
        }
        String[] ones = content.split("\\|");
        if(type.equals("quotes")){
            List<Quote> list = new ArrayList<>();
            for (int i = 0; i < ones.length; i++) {
                String[] entry = ones[i].split("---");
                String href = entry.length <= 2 ? "/" : entry[2].trim();
                String Content = entry[0].trim();
                String author = entry.length<=1?"佚名":entry[1].trim();
                Quote quote = new Quote(Content, author, href);
                list.add(quote);
            }
            JSONArray jsonArray=JSONArray.fromObject(list);
            printWriter.println(jsonArray.toString());
        }else if(type.equals("recommendation")){
            List<Recommendation> list = new ArrayList<>();
            for (int i = 0; i < ones.length; i++) {
                String[] entry = ones[i].split("---");
                String href = entry[0].trim();
                String Content = entry[1].trim();
                Recommendation recommendation=new Recommendation(Content,href);
                list.add(recommendation);
            }
            JSONArray jsonArray=JSONArray.fromObject(list);
            printWriter.println(jsonArray.toString());
        }else if(type.equals("introduction")){
            printWriter.println(content);
        }
    }
}
