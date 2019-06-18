package com.example.tugasakhir_nyuciapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Search extends AppCompatActivity {

    ImageView searchicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchicon = (ImageView) findViewById(R.id.search1);
        searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
