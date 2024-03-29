package com.example.tugasakhir_nyuciapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugasakhir_nyuciapps.apihelper.BaseApiService;
import com.example.tugasakhir_nyuciapps.apihelper.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FasilitasActivity extends AppCompatActivity {
    BaseApiService baseApiService;
    EditText antar, parfum, biasa, kilat, setrika, sepatu, karpet;
    String status = "1";
    String serviceid = "1";
    String notes = "notes";
    Button selesai;
    ImageView back;
    SharedPrefManager sharedPrefManager;
    Integer laundryid;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);

        sharedPrefManager = new SharedPrefManager(FasilitasActivity.this.getApplicationContext());
        Log.e("debug", "Shared Preference UserId Fasilitas> " + sharedPrefManager.getSP_UserId());
        laundryid = sharedPrefManager.getSP_LaundryId();

        baseApiService = UtilsApi.getApiService();

        antar = (EditText) findViewById(R.id.antarjemput);
        parfum = (EditText) findViewById(R.id.pilihanparfum);
        biasa = (EditText) findViewById(R.id.cucibiasa);
        kilat = (EditText) findViewById(R.id.cucikilat);
        setrika = (EditText) findViewById(R.id.setrika);
        sepatu = (EditText) findViewById(R.id.sepatu);
        karpet = (EditText) findViewById(R.id.karpet);
        selesai = (Button) findViewById(R.id.btfasilitas);
        back = (ImageView) findViewById(R.id.backbutton4);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FasilitasActivity.this, JadwalActivity.class));
                finish();
            }
        });

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loading = ProgressDialog.show(getApplicationContext(), null, "Harap Tunggu....", true, false);
                fasilitas();

            }
        });


    }

    private void fasilitas() {
        baseApiService.service(
                laundryid,
                antar.getText().toString(),
                parfum.getText().toString(),
                biasa.getText().toString(),
                kilat.getText().toString(),
                setrika.getText().toString(),
                sepatu.getText().toString(),
                karpet.getText().toString(),
                serviceid,
                status,
                notes
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("debug", "OnResponse : Berhasil");
                    //loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Berhasil menambahkan data laundry", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FasilitasActivity.this, MainActivity.class));
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
