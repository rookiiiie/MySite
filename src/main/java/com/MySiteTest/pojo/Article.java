package com.MySiteTest.pojo;

import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * Created on 2020/6/25
 */
public class Article {
    private int article_id;
    private String article_name;
    private String content;
    private Date add_time;
    private int views;
//    private List<String> comment;
    private String type_name;

    public Article(String article_name, String content, Date add_time, int views, String type_name) {
        this.article_name = article_name;
        this.content = content;
        this.add_time = add_time;
        this.views = views;
        this.type_name = type_name;
    }

    public Article(int article_id, String article_name, String content, Date add_time, int views, String type_name) {
        this.article_id = article_id;
        this.article_name = article_name;
        this.content = content;
        this.add_time = add_time;
        this.views = views;
        this.type_name = type_name;
    }

    public Article() {

    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", article_name='" + article_name + '\'' +
                ", content=" + content +
                ", add_time=" + add_time +
                ", views=" + views +
                ", type_name='" + type_name + '\'' +
                '}';
    }
}
