package com.example.tugasakhir_nyuciapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

public class InputActivity extends AppCompatActivity {

    Toolbar toolbar;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    BaseApiService mApiService;
    EditText nama, alamat, phone, desc;

    RadioGroup rglibur;
    RadioButton ya, tidak;
    Button simpan;
    Integer userid;
    String libur = "0";
    String location = "1";
    String pict = "null";
    String lat = "01010101";
    String lng = "10101010";
    String status = "1";

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        mContext = this;
        mApiService = UtilsApi.getApiService();

        toolbar = (Toolbar) findViewById(R.id.inputtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        nama = (EditText) findViewById(R.id.namaLaundry);
        alamat = (EditText) findViewById(R.id.alamatLaundry);
        phone = (EditText) findViewById(R.id.nohpLaundry);
        desc = (EditText) findViewById(R.id.descLaundry);
        rglibur = (RadioGroup) findViewById(R.id.rg_Libur);
        ya = (RadioButton) findViewById(R.id.rgLiburYa);
        tidak = (RadioButton) findViewById(R.id.rgLiburTidak);
        simpan = (Button) findViewById(R.id.simpan);


        sharedPrefManager = new SharedPrefManager(InputActivity.this.getApplicationContext());

        userid = sharedPrefManager.getSP_UserId();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu....", true, false);
                requestSimpan();
            }
        });
    }


    private void requestSimpan() {
        mApiService.inputLaundryProfile(
                userid,
                location,
                nama.getText().toString(),
                pict,
                desc.getText().toString(),
                phone.getText().toString(),
                libur,
                alamat.getText().toString(),
                lat,
                lng,
                status
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "OnResponse : Berhasil");
                    loading.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("error").equals("false")) {
                            Toast.makeText(mContext, "Berhasil Input Data Laundry", Toast.LENGTH_SHORT).show();
                            Integer laundryid = jsonObject.getJSONObject("data").getInt("laundry_id");

                            sharedPrefManager.saveSPInt(SharedPrefManager.SP_LaundryId, laundryid);
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
