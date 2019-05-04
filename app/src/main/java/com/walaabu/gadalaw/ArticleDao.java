package com.walaabu.gadalaw;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ArticleDao {

    @Query("SELECT *FROM Article ORDER BY number")
    List<Article>LoasdallArticle();

    @Insert
    void InsertArticle(Article article);


}
