package com.example.tugasakhir_nyuciapps;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tugasakhir_nyuciapps.apihelper.BaseApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class InputJadwalActivity extends AppCompatActivity {

    TimePickerDialog picker;
    SharedPrefManager sharedPrefManager;
    Integer laundry_id;
    EditText buka, tutup, libur;
    String bukastatus, tutupstatus, strDate;
    String notes = "notes";
    Button next;
    BaseApiService baseApiService;
    ProgressDialog loading;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_jadwal);

        sharedPrefManager = new SharedPrefManager(InputJadwalActivity.this.getApplicationContext());
        laundry_id = sharedPrefManager.getSP_LaundryId();

        Toolbar toolbar1 = (Toolbar) findViewById(R.id.inputjadwaltoolbar);

        buka = (EditText) findViewById(R.id.buka);
        tutup = (EditText) findViewById(R.id.tutup);
        libur = (EditText) findViewById(R.id.liburHarian);
        next = (Button) findViewById(R.id.next);

        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");

        buka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InputJadwalActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int jam, int menit) {
                                buka.setText(jam + " : " + menit);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InputJadwalActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int jam, int menit) {
                                tutup.setText(jam + " : " + menit);

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanjadwal();

            }
        });


    }

    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        strDate = ("Current Time: " + simpleDateFormat.format(calendar.getTime()));
        //tvStatus.setText(strDate);

    }

    private void simpanjadwal() {
        baseApiService.schedule(
                laundry_id,
                libur.getText().toString(),
                buka.getText().toString(),
                tutup.getText().toString(),
                notes
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "OnResponse : Berhasil");
                    loading.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("error").equals("false")) {

                            startActivity(new Intent(mContext, InputFasilitasActivity.class));
                            Toast.makeText(mContext, "Berhasil", Toast.LENGTH_SHORT).show();


                        } else {
                            String error = jsonObject.getString("error");
                            Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
