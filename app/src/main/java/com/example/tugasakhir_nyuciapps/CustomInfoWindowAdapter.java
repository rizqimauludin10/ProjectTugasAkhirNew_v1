package com.example.tugasakhir_nyuciapps;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugasakhir_nyuciapps.R;
import com.example.tugasakhir_nyuciapps.model.Value;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context context;
    String path = "http://192.168.43.93:8000/images/";

    public CustomInfoWindowAdapter(Context context) {
        this.context = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void setmWindow(Marker marker, View view) {
        String tittle = marker.getTitle();

        TextView tvTittle = (TextView) view.findViewById(R.id.tittleLaundry);
        TextView tvHarga = (TextView) view.findViewById(R.id.hargaA);
        TextView tvBukaJam = (TextView) view.findViewById(R.id.BukaJam);
        TextView tvTutupJam = (TextView) view.findViewById(R.id.TutupJam);
        TextView tvOperasional = (TextView) view.findViewById(R.id.tvOperasional);
        TextView tvBiasa = (TextView) view.findViewById(R.id.IwBiasa);
        TextView tvAlamat = (TextView) view.findViewById(R.id.IwAlamat);
        TextView tvlokasi = (TextView) view.findViewById(R.id.lokasilaundry);
        ImageView ivWindow = (ImageView) view.findViewById(R.id.iv_info);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/TitilliumWeb-Bold.ttf");
        Typeface typeface_a = Typeface.createFromAsset(context.getAssets(), "font/TitilliumWeb-SemiBold.ttf");
        Typeface typeface_b = Typeface.createFromAsset(context.getAssets(), "font/TitilliumWeb-Regular.ttf");

        //String foto = marker.getSnippet();
        String foto = marker.getTitle();
        final String[] str2 = foto.split("_");
        if (!tittle.equals("")) {
            Picasso.get().load(path + str2[1]).into(ivWindow);
            tvTittle.setTypeface(typeface);
            tvTittle.setText(str2[0]);
            tvlokasi.setTypeface(typeface);
            tvlokasi.setText(str2[2]);
        }

        String snippet = marker.getSnippet();
        final String[] snp2 = snippet.split("_");

        TextView tvSnippet = (TextView) view.findViewById(R.id.snippet);

        if (!snippet.equals("")) {
            tvAlamat.setTypeface(typeface_a);
            tvSnippet.setTypeface(typeface_a);
            tvOperasional.setTypeface(typeface_a);
            tvBukaJam.setTypeface(typeface_a);
            tvTutupJam.setTypeface(typeface_a);
            tvBiasa.setTypeface(typeface_a);
            tvHarga.setTypeface(typeface_a);
            tvSnippet.setText(snp2[0]);
            tvBukaJam.setText(snp2[1]);
            tvTutupJam.setText(snp2[2]);
            tvHarga.setText(snp2[3]);
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        setmWindow(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        setmWindow(marker, mWindow);
        return mWindow;
    }
}
