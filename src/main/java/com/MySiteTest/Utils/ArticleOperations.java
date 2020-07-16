package com.MySiteTest.Utils;

import com.MySiteTest.dao.ArticleDao;
import com.MySiteTest.dao.TypeDao;
import com.MySiteTest.pojo.Article;
import com.MySiteTest.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * Created on 2020/6/29
 */
@Component
public class ArticleOperations {
    @Autowired
    private FileOperations fileOperations;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    ArticleDao articleDao;
    public void addArticle(String content,String fileName) throws FileNotFoundException {
        String type_name=fileName.split("-")[0];
        String article_name=fileName.split("-")[1];
        if(articleDao.SelectByArticleName(article_name).size()>0){
            return;
        }
        if(typeDao.selectByTypeName(new Type(type_name))==null){
            typeDao.saveType(new Type(type_name));
        }
        Article article=new Article(article_name,content,new Date(),0,type_name);
        articleDao.saveArticle(article);
    }
    public void deleteArticle(String fileName){
        String type_name=fileName.split("-")[0];
        String article_name=fileName.split("-")[1];
        if(articleDao.SelectByArticleName(article_name).size()==0){
            return;
        }
        articleDao.deleteByArticleNameAndTypeName(article_name,type_name);
        if(articleDao.SelectByTypeName(new Type(type_name)).size()==0){
            typeDao.deleteType(new Type(type_name));
        }
    }
}
