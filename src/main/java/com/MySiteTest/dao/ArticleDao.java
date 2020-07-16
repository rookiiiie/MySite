package com.MySiteTest.dao;

import com.MySiteTest.pojo.Article;
import com.MySiteTest.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2020/6/26
 */
@Repository
@Mapper
public interface ArticleDao {
//    @Select("select * from article where type_name = #{type_name}")
    public List<Article> SelectByTypeName(Type type);
    public Article SelectByArticleId(Integer article_id);
    public void UpdateArticleView(@Param("views") Integer views,@Param("article_id") Integer article_id);
    public void saveArticle(Article article);
    public List<Article> SelectByArticleName(@Param("article_name")String article_name);
    public void deleteByArticleNameAndTypeName(@Param("article_name")String article_name,@Param("type_name")String type_name);
}
