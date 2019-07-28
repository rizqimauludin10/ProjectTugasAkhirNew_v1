package com.example.tugasakhir_nyuciapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhir_nyuciapps.MainActivity;
import com.example.tugasakhir_nyuciapps.R;
import com.example.tugasakhir_nyuciapps.model.Value;

import java.security.PublicKey;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaundryAdapter extends RecyclerView.Adapter<LaundryAdapter.LaundryHolder> {
    List<Value> semuaLaundryList;
    Context context;
    String apa;


    public LaundryAdapter(Context context, List<Value> laundryList) {
        this.context = context;
        this.semuaLaundryList = laundryList;

    }

    @NonNull
    @Override
    public LaundryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlaundry, parent, false);
        LaundryHolder laundryHolder = new LaundryHolder(view);

        laundryHolder.itemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"Welcome", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LaundryAdapter.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("", apa);

            }
        });

        return laundryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LaundryHolder holder, int position) {
        final Value semualaundryItem = semuaLaundryList.get(position);
        holder.tvNamaLaundry.setText(semualaundryItem.getLaundryName());
        holder.tvDescLaundry.setText(semualaundryItem.getLaundryAddress());
        holder.tvLokasi.setText(semualaundryItem.getLocationName());

        apa = semualaundryItem.getLaundryPhone();


    }

    @Override
    public int getItemCount() {
        return semuaLaundryList.size();
    }

    public class LaundryHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaLaundry;
        public TextView tvDescLaundry, tvLokasi;
        public LinearLayout itemClick;



        public LaundryHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaLaundry = itemView.findViewById(R.id.tvNamaLaundry);
            tvDescLaundry = itemView.findViewById(R.id.tvDescLaundry);
            tvLokasi = itemView.findViewById(R.id.tvLokasi);
            itemClick = itemView.findViewById(R.id.itemclick);

            ButterKnife.bind(this, itemView);
        }
    }


}