package com.example.tugasakhir_nyuciapps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String phone = "62";


    Context mContext;
    BaseApiService mApiService;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

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

                if (etUserRegis.getText().toString().isEmpty() && etPassRegis.getText().toString().isEmpty() && etPhoneRegis.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Masukkan alamat email, password dan nomor handphone", Toast.LENGTH_SHORT).show();
                } else if(etUserRegis.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Masukkan alamat email", Toast.LENGTH_SHORT).show();
                } else if(etPassRegis.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Masukkan password", Toast.LENGTH_SHORT).show();
                } else if(etPhoneRegis.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Masukkan nomor handphone", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Patterns.EMAIL_ADDRESS.matcher(etUserRegis.getText().toString().trim()).matches()) {
                        loading = ProgressDialog.show(mContext, null, "Harap Tunggu....", true, false);
                        requestRegister();
                    } else if (etPassRegis.getText().toString().length()<6 && !isValidPassword(etPassRegis.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Password tidak sesuai format", Toast.LENGTH_SHORT).show();
                    } else if (!etUserRegis.getText().toString().trim().matches(emailPattern)){
                        Toast.makeText(getApplicationContext(), "Email tidak sesuai format", Toast.LENGTH_SHORT).show();
                    } else if (etPhoneRegis.getText().toString().length()>=13) {
                        Toast.makeText(getApplicationContext(), "Nomor Handphone tidak sesuai format", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (pencarijasa.isChecked()) {
                    level = "1";
                } else {
                    level = "0";
                }
            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private void requestRegister() {
        mApiService.registerRequest(
                etUserRegis.getText().toString(),
                etPassRegis.getText().toString(),
                phone + etPhoneRegis.getText().toString(),
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
                        loading.dismiss();

                    }
                });

    }
}
