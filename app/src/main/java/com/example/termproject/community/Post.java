package com.example.termproject.community;

import android.net.Uri;

public class Post {


    String writer = "";
    String title = "";
    String content = "";
    String uri = "";
    String univ = "";
    String badge = ""; // contribution


    public Post() {}

    public Post(String writer, String title, String content, String uri, String univ, String badge) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.uri = uri;
        this.univ = univ;
        this.badge = badge;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

}
