package com.MySiteTest.service.Imp;

import com.MySiteTest.dao.ArticleDao;
import com.MySiteTest.dao.TypeDao;
import com.MySiteTest.pojo.Article;
import com.MySiteTest.pojo.Type;
import com.MySiteTest.service.ArchiveService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created on 2020/6/25
 */
@Service
public class ArchiveServiceImp implements ArchiveService {
    @Autowired
    TypeDao typeDao;
    @Autowired
    ArticleDao articleDao;
    @Override
    public void getArchive(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter=response.getWriter();
        List<Type> list=typeDao.selectAllTypes();
        JSONArray jsonArray= JSONArray.fromObject(list);
//        System.out.println(jsonArray.toString());
        printWriter.println(jsonArray);
    }
    @Override
    public void getArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String type_name=request.getParameter("type_name");
        if(type_name==null||type_name.equals(""))
            return;
//        System.out.println("type_name = "+type_name);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter=response.getWriter();
        List<Article> list=articleDao.SelectByTypeName(new Type(type_name));
        JSONArray jsonArray= JSONArray.fromObject(list);
//        System.out.println(jsonArray.toString());
        printWriter.println(jsonArray.toString());
    }

    @Override
    public void getArticleById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String article_id=request.getParameter("article_id");
        if(article_id==null||article_id.equals(""))
            return;
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter=response.getWriter();
        Article article=articleDao.SelectByArticleId(Integer.parseInt(article_id));
        JSONObject jsonObject=JSONObject.fromObject(article);
        printWriter.println(jsonObject.toString());
        String views=request.getParameter("views");
        articleDao.UpdateArticleView(Integer.parseInt(views)+1,Integer.parseInt(article_id));
    }
}
