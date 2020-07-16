package com.MySiteTest.pojo;

/**
 * Created on 2020/6/29
 */
public class Quote {
    private String content;
    private String author;
    private String href;

    public Quote(String content, String author, String href) {
        this.content = content;
        this.author = author;
        this.href = href;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
