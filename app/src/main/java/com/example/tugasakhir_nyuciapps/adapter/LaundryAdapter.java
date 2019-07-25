package com.example.tugasakhir_nyuciapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhir_nyuciapps.R;
import com.example.tugasakhir_nyuciapps.model.Value;

import java.security.PublicKey;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaundryAdapter extends RecyclerView.Adapter<LaundryAdapter.LaundryHolder> {
    List<Value> semuaLaundryList;
    Context context;


    public LaundryAdapter(Context context, List<Value> laundryList) {
        this.context = context;
        semuaLaundryList = laundryList;

    }

    @NonNull
    @Override
    public LaundryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlaundry, parent, false);
        LaundryHolder laundryHolder = new LaundryHolder(view);
        return laundryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LaundryHolder holder, int position) {
        final Value semualaundryItem = semuaLaundryList.get(position);
        holder.tvNamaLaundry.setText(semualaundryItem.getLaundryName());
        holder.tvDescLaundry.setText(semualaundryItem.getLaundryDesc());

    }

    @Override
    public int getItemCount() {
        return semuaLaundryList.size();
    }

    public class LaundryHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaLaundry;
        public TextView tvDescLaundry;


        public LaundryHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaLaundry = itemView.findViewById(R.id.tvNamaLaundry);
            tvDescLaundry = itemView.findViewById(R.id.tvDescLaundry);

            ButterKnife.bind(this, itemView);
        }
    }


}
