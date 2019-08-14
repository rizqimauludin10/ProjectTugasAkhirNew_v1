package com.example.tugasakhir_nyuciapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tugasakhir_nyuciapps.apihelper.BaseApiService;
import com.example.tugasakhir_nyuciapps.apihelper.UtilsApi;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class JadwalActivity extends AppCompatActivity {

    EditText liburHr;
    EditText buka;
    EditText tutup;
    Button jadwalBt;
    ImageView back;
    Integer laundryid, userid;
    String notes = "bla";
    SharedPrefManager sharedPrefManager;
    SharedPrefManager sharedPrefManager1;
    ProgressDialog loading;
    TimePickerDialog timePicker;
    BaseApiService baseApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        baseApiService = UtilsApi.getApiService();

        sharedPrefManager = new SharedPrefManager(JadwalActivity.this.getApplicationContext());
        laundryid = sharedPrefManager.getSP_LaundryId();

        userid = sharedPrefManager.getSP_UserId();
        Log.e("debug", "Shared Preference LaundryId Jadwal> " + laundryid);
        Log.e("debug", "Shared Preference UserId Jadwal> " + userid);

        liburHr = (EditText) findViewById(R.id.liburharian);
        buka = (EditText) findViewById(R.id.jambuka);
        tutup = (EditText) findViewById(R.id.jamtutup);
        jadwalBt = (Button) findViewById(R.id.btjadwal);
        back = (ImageView) findViewById(R.id.backbutton6);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JadwalActivity.this, InputActivity.class));
                finish();
            }
        });


        jadwalBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loading = ProgressDialog.show(getApplicationContext(), null, "Harap Tunggu....", true, false);
                jadwal();
            }
        });

        buka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(JadwalActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int jam, int menit) {
                        buka.setText(String.valueOf(jam) + ":" + String.valueOf(menit) + ":00");
                    }
                }, hour, minutes, true);
                timePicker.show();
            }
        });

        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(JadwalActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int jam, int menit) {
                        tutup.setText(String.valueOf(jam) + ":" + String.valueOf(menit) + ":00");
                    }
                }, hour, minutes, true);
                timePicker.show();
            }
        });
    }

    private void jadwal() {
        baseApiService.jadwal(
                laundryid,
                liburHr.getText().toString(),
                buka.getText().toString(),
                tutup.getText().toString(),
                notes
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("debug", "OnResponse : Berhasil");
                    //loading.dismiss();
                    startActivity(new Intent(JadwalActivity.this, FasilitasActivity.class));
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Gagal Input Data Laundry, Mohon Ulangi", Toast.LENGTH_SHORT).show();
                    //loading.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                //loading.dismiss();

            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
