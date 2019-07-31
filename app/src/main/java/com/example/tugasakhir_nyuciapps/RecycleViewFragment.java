package com.example.tugasakhir_nyuciapps;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhir_nyuciapps.adapter.LaundryAdapter;
import com.example.tugasakhir_nyuciapps.apihelper.BaseApiService;
import com.example.tugasakhir_nyuciapps.apihelper.UtilsApi;
import com.example.tugasakhir_nyuciapps.model.LaundryDataResponse;
import com.example.tugasakhir_nyuciapps.model.Value;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RecycleViewFragment extends Fragment {

    View v;

    RecyclerView rvLaundry;
    ProgressDialog progressDialog;
    Context context;
    private List<Value> semuaLaundryItemList = new ArrayList<>();
    private RecyclerView.Adapter laundryAdapter;
    private BaseApiService baseApiService;

    public RecycleViewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.recycleview_fragment, container, false);


        rvLaundry = (RecyclerView) v.findViewById(R.id.rvLaundry);

        context = getActivity();
        baseApiService = UtilsApi.getApiService();


        laundryAdapter = new LaundryAdapter(context, semuaLaundryItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvLaundry.setLayoutManager(layoutManager);
        rvLaundry.setItemAnimator(new DefaultItemAnimator());

        getResultListLaundry();

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void getResultListLaundry() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);

        baseApiService.getLaundry().enqueue(new Callback<LaundryDataResponse>() {
            @Override
            public void onResponse(Call<LaundryDataResponse> call, Response<LaundryDataResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    semuaLaundryItemList = response.body().getValues();
                    rvLaundry.setAdapter(new LaundryAdapter(context, semuaLaundryItemList));
                    laundryAdapter.notifyDataSetChanged();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Gagal mengambil data laundry", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LaundryDataResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
