package com.example.tugasakhir_nyuciapps;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class InputJadwalActivity extends AppCompatActivity {

    TimePickerDialog picker;
    EditText buka, tutup;
    String bukastatus, tutupstatus, strDate;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_jadwal);

        Toolbar toolbar1 = (Toolbar) findViewById(R.id.inputjadwaltoolbar);

        buka = (EditText) findViewById(R.id.buka);
        tutup = (EditText) findViewById(R.id.tutup);
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

            }
        });


    }

    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        strDate = ("Current Time: " + simpleDateFormat.format(calendar.getTime()));
        //tvStatus.setText(strDate);

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
