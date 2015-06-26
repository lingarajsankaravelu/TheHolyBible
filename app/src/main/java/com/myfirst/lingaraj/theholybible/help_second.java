package com.myfirst.lingaraj.theholybible;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lingaraj on 6/23/2015.
 */
public class help_second extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_second);
        LinearLayout mylinear = (LinearLayout) findViewById(R.id.help_second_layout);
        mylinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(), share_main.class);
                startActivity(myintent);
                finish();

            }
        });

    }

}
