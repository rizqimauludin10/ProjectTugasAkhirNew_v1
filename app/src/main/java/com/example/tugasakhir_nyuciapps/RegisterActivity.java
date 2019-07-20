package com.example.tugasakhir_nyuciapps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugasakhir_nyuciapps.apihelper.BaseApiService;
import com.example.tugasakhir_nyuciapps.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterActivity extends AppCompatActivity {

    ImageView showPassRegis;
    EditText etPassRegis, etUserRegis, etPhoneRegis;
    TextView toLogin;
    Button btnRegister;
    RadioGroup radioGroup;
    RadioButton pencarijasa, pemilikJasa;
    boolean password_status = true;
    ProgressDialog loading;

    String level = "0";


    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = this;
        mApiService = UtilsApi.getApiService();

        showPassRegis = (ImageView) findViewById(R.id.show_password_register);

        etPassRegis = (EditText) findViewById(R.id.et_password_register);
        etUserRegis = (EditText) findViewById(R.id.et_username_register);
        etPhoneRegis = (EditText) findViewById(R.id.et_noHp_regis);
        btnRegister = (Button) findViewById(R.id.btn_register);
        radioGroup = (RadioGroup) findViewById(R.id.rg_user);
        pemilikJasa = (RadioButton) findViewById(R.id.pemilikJasa);
        pencarijasa = (RadioButton) findViewById(R.id.pencariJasa);

        toLogin = (TextView) findViewById(R.id.toLogin);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        showPassRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password_status) {
                    password_status = false;
                    etPassRegis.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password_status = true;
                    etPassRegis.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu....", true, false);

                requsetRegister();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (pencarijasa.isChecked()) {
                    level = "1";
                    Toast.makeText(RegisterActivity.this, level, Toast.LENGTH_SHORT).show();
                } else {
                    level = "0";
                    Toast.makeText(RegisterActivity.this, level, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void requsetRegister() {
        mApiService.registerRequest(
                etUserRegis.getText().toString(),
                etPassRegis.getText().toString(),
                etPhoneRegis.getText().toString(),
                level
        )
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.i("debug", "OnResponse : Berhasil");
                            loading.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("error").equals("false")) {
                                    Toast.makeText(mContext, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                } else {
                                    String error = jsonObject.getString("error");
                                    Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "OnResponse : Gagal");
                            loading.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "OnFailure : Error > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
