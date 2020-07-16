package com.MySiteTest.pojo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;

/**
 * Created on 2020/6/25
 */
@Repository
@Mapper
public class Comment {
    private String content;
    private Date time;
    private String QQ;
    private String article_id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", time=" + time +
                ", QQ='" + QQ + '\'' +
                ", article_id='" + article_id + '\'' +
                '}';
    }
}
