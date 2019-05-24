package com.walaabu.gadalaw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddArticleactivity extends AppCompatActivity {
Button msaveArticle;
TextView number;
TextView title;
TextView content;
AppDatabase mdb;
    int mnumber;
    String mtitile;
    String  mcontent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_articleactivity);

   mdb=AppDatabase.getInstance(getApplicationContext());

        number=findViewById(R.id.ed_articlenumbre);
        title=findViewById(R.id.ed_addtitle);
        content=findViewById(R.id.ed_content);

        msaveArticle=findViewById(R.id.btn_savetoroom);
        msaveArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mnumber= Integer.parseInt(number.getText().toString());
                mtitile=title.getText().toString();
                mcontent=content.getText().toString();
                //Article article=new Article(mnumber,mtitile,mcontent);
                //mdb.ArticleDao().InsertArticle(article);
                finish();

            }
        });

    }
}
