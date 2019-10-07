package com.example.tugasakhir_nyuciapps;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
    TextView nama, alamat, lokasi, created, buka, tutup, liburHarian, tvNamaPemilik, tvNomorPemilik, statusBuka, desc_login;
    TextView biasa, kilat, setrika, sepatu, karpet, antar, parfum;
    String biasaEx, kilatEx, setrikaEx, sepatuEx, karpetEx, antarEx, parfumEx;
    ImageView tglmerah, ivHarian, photo, back;
    Button hubungiPemilik;
    Button cs_btnTidak, cs_btnYa, btncsLogin;
    ImageView closeBtn, closeBtnLogin;
    String path = "https://laundry.rumahkite.co.id/images/";
    SharedPrefManager sharedPrefManager;
    Dialog epicDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        sharedPrefManager = new SharedPrefManager(DetailActivity.this.getApplicationContext());

        String email = sharedPrefManager.getSp_Email();
        String name = sharedPrefManager.getSp_Name();
        String phone = sharedPrefManager.getSP_Phone();
        epicDialog = new Dialog(this);

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
        cs_btnTidak = (Button) findViewById(R.id.cs_btnTidak);
        cs_btnYa = (Button) findViewById(R.id.cs_btnYa);
        closeBtn = (ImageView) findViewById(R.id.btn_close);
        closeBtnLogin = (ImageView) findViewById(R.id.btn_closeLogin);
        desc_login = (TextView) findViewById(R.id.desc_login);
        back = (ImageView) findViewById(R.id.backbutton3);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            statusBukaEx = extras.getString("stbuka");

            if (statusBukaEx == ("Tutup")) {
                statusBuka.setText("Verifikasi");
                statusBuka.setTextColor(Color.parseColor("#13476A"));
            } else {
                statusBuka.setText("Verifikasi");
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
                    if (sharedPrefManager.getSPSudahLoginPencari().equals(true)) {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://api.whatsapp.com/send?phone=" + nomorPemilikEx + "&text=Hallo%20Saya%20perlu%20bantuan%20Pemilik%20" + namaEx + "%0D" + " " + email + " " + "%0D"));
                        startActivity(intent);
                    } else if (sharedPrefManager.getSPSudahLoginPemilik().equals(true)) {
                        dialogPencari();
                    } else {
                        dialogLogin();
                    }

                }
            });


        }

        back.setOnClickListener(new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View view) {
                intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void dialogPencari() {
        epicDialog.setContentView(R.layout.cs_pencari_login);
        closeBtnLogin = (ImageView) epicDialog.findViewById(R.id.btn_closeLogin);
        desc_login = (TextView) epicDialog.findViewById(R.id.desc_login);
        btncsLogin = (Button) epicDialog.findViewById(R.id.btn_cslogin);

        closeBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });

        btncsLogin.setOnClickListener(new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View view) {
                intent = new Intent(DetailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

    }

    public void dialogLogin() {
        epicDialog.setContentView(R.layout.cs_login);
        closeBtnLogin = (ImageView) epicDialog.findViewById(R.id.btn_closeLogin);
        desc_login = (TextView) epicDialog.findViewById(R.id.desc_login);
        btncsLogin = (Button) epicDialog.findViewById(R.id.btn_cslogin);

        closeBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });

        btncsLogin.setOnClickListener(new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View view) {
                intent = new Intent(DetailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

    }
}
