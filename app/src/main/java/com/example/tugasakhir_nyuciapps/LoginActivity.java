package com.example.tugasakhir_nyuciapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    ImageView showPassword;
    EditText etpassword;

    boolean password_status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showPassword = (ImageView) findViewById(R.id.show_password);
        etpassword = (EditText) findViewById(R.id.et_password);

        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (password_status) {
                    password_status = false;
                    etpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password_status = true;
                    etpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }


            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
