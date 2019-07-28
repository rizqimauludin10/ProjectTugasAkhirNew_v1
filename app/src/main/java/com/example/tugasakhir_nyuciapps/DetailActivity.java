package com.example.tugasakhir_nyuciapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    String phone;
    TextView phone1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        phone1 = (TextView) findViewById(R.id.phone);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            phone = extras.getString("name");
            phone1.setText(phone);

        }
    }
}
