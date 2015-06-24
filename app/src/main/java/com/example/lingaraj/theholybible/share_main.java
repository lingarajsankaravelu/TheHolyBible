package com.example.lingaraj.theholybible;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lingaraj on 6/25/2015.
 */
public class share_main extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);
        LinearLayout mylinear = (LinearLayout) findViewById(R.id.share_layout);
        mylinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), Bible_home.class);
                startActivity(myintent);
                finish();
            }
        });
    }
}