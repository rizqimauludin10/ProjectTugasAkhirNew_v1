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

public class LoginActivity extends AppCompatActivity {

    ImageView showPassword;
    EditText etpassword, etusername;
    TextView toRegister;
    Button btnLogin;

    ProgressDialog loading;

    boolean password_status = true;

    Context mContext;
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        mContext = this;
        mApiService = UtilsApi.getApiService();

        showPassword = (ImageView) findViewById(R.id.show_password);
        etpassword = (EditText) findViewById(R.id.et_password);
        etusername = (EditText) findViewById(R.id.et_username);
        toRegister = (TextView) findViewById(R.id.toRegister);
        btnLogin = (Button) findViewById(R.id.btn_login);

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


        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu....", true, false);
                requestLogin();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void requestLogin() {
        mApiService.loginRequest(
                etusername.getText().toString(),
                etpassword.getText().toString()
        )
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("error").equals("false")) {
                                    Log.e("debug", "OnResponse: Berhasil");

                                    Toast.makeText(mContext, "Berhasil Login", Toast.LENGTH_SHORT).show();
                                    Integer id = jsonObject.getJSONObject("data").getInt("id");
                                    String email = jsonObject.getJSONObject("data").getString("email");
                                    String name = jsonObject.getJSONObject("data").getString("name");
                                    String phone = jsonObject.getJSONObject("data").getString("phone");

                                    //sharedPreferences
                                    sharedPrefManager.saveSPInt(SharedPrefManager.SP_UserId, id);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_Email, email);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_Email, name);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_Phone, phone);
                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SudahLogin, true);

                                    Intent intent = new Intent(mContext, MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    finish();
                                    intent.putExtra("email", email);
                                    intent.putExtra("name", name);
                                    intent.putExtra("phone", phone);
                                    startActivity(intent);
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
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "OnFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }
}
