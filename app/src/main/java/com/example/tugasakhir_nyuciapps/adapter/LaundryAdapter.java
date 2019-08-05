package com.example.tugasakhir_nyuciapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhir_nyuciapps.DetailActivity;
import com.example.tugasakhir_nyuciapps.MainActivity;
import com.example.tugasakhir_nyuciapps.R;
import com.example.tugasakhir_nyuciapps.model.Value;
import com.squareup.picasso.Picasso;

import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaundryAdapter extends RecyclerView.Adapter<LaundryAdapter.LaundryHolder> {
    List<Value> semuaLaundryList;
    Context context;
    String phone, alamat, name, lokasi, created_at, buka, tutup, liburHarian, namapemilik,
            noHpPemilik, photo;
    String antar, parfum, biasa, kilat, setrika, sepatu, karpet;
    Integer tglmerah;
    String date1, date2;
    String path = "http://192.168.43.93:8000/images/";


    public LaundryAdapter(Context context, List<Value> laundryList) {
        this.context = context;
        this.semuaLaundryList = laundryList;

    }

    @NonNull
    @Override
    public LaundryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlaundry, parent, false);
        final LaundryHolder laundryHolder = new LaundryHolder(view);
        return laundryHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull LaundryHolder holder, int position) {
        final Value semualaundryItem = semuaLaundryList.get(position);
        //holder.IvLaundry.setImageResource(semualaundryItem.getLaundryPict());
        Picasso.get().load(path + semualaundryItem.getLaundryPict()).into(holder.IvLaundry);
        holder.tvNamaLaundry.setText(semualaundryItem.getLaundryName());
        holder.tvDescLaundry.setText(semualaundryItem.getLaundryAddress());
        holder.tvLokasi.setText(semualaundryItem.getLocationName());
        holder.harga.setText(semualaundryItem.getNyuciservicePricethree());


        //holder.jamBuka.setText(semualaundryItem.getNyucischeduleOpenHours());

        holder.itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"Welcome" + name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);

                name = semualaundryItem.getLaundryName();
                alamat = semualaundryItem.getLaundryAddress();
                phone = semualaundryItem.getLaundryPhone();
                lokasi = semualaundryItem.getLocationName();
                created_at = semualaundryItem.getCreatedAt();
                buka = semualaundryItem.getNyucischeduleOpenHours();
                tutup = semualaundryItem.getNyucischeduleCloseHours();
                tglmerah = semualaundryItem.getLaundryIsHoliday();
                liburHarian = semualaundryItem.getNyucischeduleDay();
                namapemilik = semualaundryItem.getName();
                noHpPemilik = semualaundryItem.getLaundryPhone();
                antar = semualaundryItem.getNyuciservicePriceOne();
                parfum = semualaundryItem.getNyuciservicePricetwo();
                biasa = semualaundryItem.getNyuciservicePricethree();
                kilat = semualaundryItem.getNyuciservicePricefour();
                setrika = semualaundryItem.getNyuciservicePricefive();
                sepatu = semualaundryItem.getNyuciservicePricesix();
                karpet = semualaundryItem.getNyuciservicePriceseven();
                photo = semualaundryItem.getLaundryPict();



                intent.putExtra("name", name);
                intent.putExtra("alamat", alamat);
                intent.putExtra("phone", phone);
                intent.putExtra("lokasi", lokasi);
                intent.putExtra("created_at", created_at);
                intent.putExtra("buka", buka);
                intent.putExtra("tutup", tutup);
                intent.putExtra("tglmerah", tglmerah);
                intent.putExtra("liburharian", liburHarian);
                intent.putExtra("namapemilik", namapemilik);
                intent.putExtra("nohppemilik", noHpPemilik);
                intent.putExtra("antar", antar);
                intent.putExtra("parfum", parfum);
                intent.putExtra("biasa", biasa);
                intent.putExtra("kilat", kilat);
                intent.putExtra("setrika", setrika);
                intent.putExtra("sepatu", sepatu);
                intent.putExtra("karpet", karpet);
                intent.putExtra("photo", photo);


                context.startActivity(intent);
            }
        });




        date1 = semualaundryItem.getNyucischeduleOpenHours();
        date2 = semualaundryItem.getNyucischeduleCloseHours();


        //Kondisi Buka dan Tutup
        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR_OF_DAY); // Get hour in 24 hour format
        int minute = now.get(Calendar.MINUTE);

        Date date = parseDate(hour + ":" + minute);
        Date dateCompareOne = parseDate(date1);
        Date dateCompareTwo = parseDate(date2);

        if (dateCompareOne.before(date) && dateCompareTwo.after(date)) {
            holder.jamBuka.setText("Buka");
            holder.jamBuka.setTextColor(Color.parseColor("#13476A"));
            Log.d("Operasional", "Open");
        } else {
            holder.jamBuka.setText("Tutup");
            holder.jamBuka.setTextColor(Color.parseColor("#EE2727"));
            Log.d("Operasional", "Close");
        }
    }

    private Date parseDate(String date) {

        final String inputFormat = "HH:mm";
        SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }


    @Override
    public int getItemCount() {
        return semuaLaundryList.size();
    }

    public class LaundryHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaLaundry, fasilitas;
        public TextView tvDescLaundry, tvLokasi, rp, harga, kg, jamBuka;
        public ImageView IvLaundry;
        public LinearLayout itemClick;



        public LaundryHolder(@NonNull View itemView) {
            super(itemView);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/TitilliumWeb-Regular.ttf");
            Typeface typeface1 = Typeface.createFromAsset(context.getAssets(), "font/TitilliumWeb-SemiBold.ttf");
            Typeface typeface2 = Typeface.createFromAsset(context.getAssets(), "font/TitilliumWeb-Bold.ttf");
            IvLaundry = itemView.findViewById(R.id.IvLaundry);
            tvNamaLaundry = itemView.findViewById(R.id.tvNamaLaundry);
            tvNamaLaundry.setTypeface(typeface);
            tvDescLaundry = itemView.findViewById(R.id.tvDescLaundry);
            tvDescLaundry.setTypeface(typeface2);
            tvLokasi = itemView.findViewById(R.id.tvLokasi);
            tvLokasi.setTypeface(typeface2);
            itemClick = itemView.findViewById(R.id.itemclick);
            rp = itemView.findViewById(R.id.rp);
            rp.setTypeface(typeface2);
            harga = itemView.findViewById(R.id.harga);
            harga.setTypeface(typeface2);
            kg = itemView.findViewById(R.id.kg);
            kg.setTypeface(typeface2);
            fasilitas = itemView.findViewById(R.id.fasilitas);
            fasilitas.setTypeface(typeface2);
            jamBuka = itemView.findViewById(R.id.buka);
            jamBuka.setTypeface(typeface2);

            ButterKnife.bind(this, itemView);
        }
    }

}
