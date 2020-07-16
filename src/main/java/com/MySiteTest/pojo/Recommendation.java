package com.MySiteTest.pojo;

/**
 * Created on 2020/6/29
 */
public class Recommendation {
    private String content;
    private String href;

    public Recommendation(String content, String href) {
        this.content = content;
        this.href = href;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "content='" + content + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
