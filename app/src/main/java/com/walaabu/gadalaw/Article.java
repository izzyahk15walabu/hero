package com.walaabu.gadalaw;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Article {

@PrimaryKey
    int number;
    String title;
    String content;

    public Article(int number, String title, String content) {
        this.number = number;
        this.title = title;
        this.content = content;
    }

    public void setNumber(int  number) {
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int  getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
