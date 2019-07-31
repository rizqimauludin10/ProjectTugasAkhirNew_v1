package com.example.tugasakhir_nyuciapps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity {

    String namaEx, alamatEx, lokasiEx, createdEX, bukaEX, tutupEx, liburHarianEx, namaPemilikEx, nomorPemilikEx;
    Integer tglmerahiconEx;
    TextView nama, alamat, lokasi, created, buka, tutup, liburHarian, tvNamaPemilik, tvNomorPemilik;
    ImageView tglmerah, ivHarian;
    Button hubungiPemilik;

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
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

            hubungiPemilik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://api.whatsapp.com/send?phone=" + nomorPemilikEx + "&text=Saya%20perlu%20bantuan%Pemilik%20" + namaEx));
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
