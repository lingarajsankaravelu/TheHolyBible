package com.example.lingaraj.theholybible;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by lingaraj on 6/23/2015.
 */
public class help_main extends ActionBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        LinearLayout mylinear=(LinearLayout) findViewById(R.id.custom_linear_layout);
        mylinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(getApplicationContext(),help_second.class);
                startActivity(myintent);
                finish();
            }
        });


    }
}
