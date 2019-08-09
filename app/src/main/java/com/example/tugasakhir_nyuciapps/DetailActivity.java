package com.example.tugasakhir_nyuciapps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity {

    String namaEx, alamatEx, lokasiEx, createdEX, bukaEX, tutupEx, liburHarianEx, namaPemilikEx, nomorPemilikEx, photoEx, statusBukaEx;
    Integer tglmerahiconEx;
    TextView nama, alamat, lokasi, created, buka, tutup, liburHarian, tvNamaPemilik, tvNomorPemilik, statusBuka;
    TextView biasa, kilat, setrika, sepatu, karpet, antar, parfum;
    String biasaEx, kilatEx, setrikaEx, sepatuEx, karpetEx, antarEx, parfumEx;
    ImageView tglmerah, ivHarian, photo;
    Button hubungiPemilik;
    String path = "http://192.168.43.93:8000/images/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nama = (TextView) findViewById(R.id.tvNamaLaundry);
        alamat = (TextView) findViewById(R.id.alamatLaundry);
        lokasi = (TextView) findViewById(R.id.tvLokasi);
        created = (TextView) findViewById(R.id.tvCreatedAt);
        buka = (TextView) findViewById(R.id.jamBuka);
        tutup = (TextView) findViewById(R.id.jamTutup);
        tglmerah = (ImageView) findViewById(R.id.ivLiburTanggalMerah);
        liburHarian = (TextView) findViewById(R.id.tvLiburHarian);
        ivHarian = (ImageView) findViewById(R.id.ivHarian);
        tvNamaPemilik = (TextView) findViewById(R.id.tvNamaPemilik);
        tvNomorPemilik = (TextView) findViewById(R.id.tvNomorPemilik);
        hubungiPemilik = (Button) findViewById(R.id.hubungiPemilik);
        biasa = (TextView) findViewById(R.id.hargabiasa);
        kilat = (TextView) findViewById(R.id.hargakilat);
        setrika = (TextView) findViewById(R.id.hargasetrika);
        sepatu = (TextView) findViewById(R.id.hargasepatu);
        karpet = (TextView) findViewById(R.id.hargakarpet);
        antar = (TextView) findViewById(R.id.hargaantar);
        parfum = (TextView) findViewById(R.id.hargaparfum);
        photo = (ImageView) findViewById(R.id.ivPhoto);
        statusBuka = (TextView) findViewById(R.id.statusbuka);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            statusBukaEx = extras.getString("stbuka");


            if (statusBukaEx == "Buka") {
                statusBuka.setText("Buka");
                statusBuka.setTextColor(Color.parseColor("#13476A"));
            } else {
                statusBuka.setText("Tutup");
                statusBuka.setTextColor(Color.parseColor("#EE2727"));

            }

            namaEx = extras.getString("name");
            nama.setText(namaEx);

            alamatEx = extras.getString("alamat");
            alamat.setText(alamatEx);

            lokasiEx = extras.getString("lokasi");
            lokasi.setText(lokasiEx);

            createdEX = extras.getString("created_at");
            created.setText(createdEX);

            bukaEX = extras.getString("buka");
            buka.setText(bukaEX);

            tutupEx = extras.getString("tutup");
            tutup.setText(tutupEx);

            tglmerahiconEx = extras.getInt("tglmerah");
            if (tglmerahiconEx == 1) {
                tglmerah.setImageResource(R.drawable.ic_check_circle_black_24dp);
            } else {
                tglmerah.setImageResource(R.drawable.ic_cancel_black_24dp);
            }

            liburHarianEx = extras.getString("liburharian");
            if (liburHarianEx == "Tidak Ada") {
                ivHarian.setImageResource(R.drawable.ic_cancel_black_24dp);
                liburHarian.setText(liburHarianEx);
            } else {
                ivHarian.setImageResource(R.drawable.ic_check_circle_black_24dp);
                liburHarian.setText(liburHarianEx);
            }

            namaPemilikEx = extras.getString("namapemilik");
            tvNamaPemilik.setText(namaPemilikEx);

            nomorPemilikEx = extras.getString("nohppemilik");
            tvNomorPemilik.setText(nomorPemilikEx);

            biasaEx = extras.getString("biasa");
            biasa.setText(biasaEx);

            kilatEx = extras.getString("kilat");
            kilat.setText(kilatEx);

            setrikaEx = extras.getString("setrika");
            setrika.setText(setrikaEx);

            sepatuEx = extras.getString("sepatu");
            sepatu.setText(sepatuEx);

            karpetEx = extras.getString("karpet");
            karpet.setText(karpetEx);

            antarEx = extras.getString("antar");
            antar.setText(antarEx);

            parfumEx = extras.getString("parfum");
            parfum.setText(parfumEx);

            photoEx = extras.getString("photo");
            Picasso.get().load(path + photoEx).into(photo);



            hubungiPemilik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://api.whatsapp.com/send?phone=" + nomorPemilikEx + "&text=Saya%20perlu%20bantuan%20Pemilik%20" + namaEx));
                    startActivity(intent);
                }
            });


        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
