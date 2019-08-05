package com.example.tugasakhir_nyuciapps.apihelper;

import com.example.tugasakhir_nyuciapps.model.LaundryDataResponse;
import com.example.tugasakhir_nyuciapps.model.Value;

import java.sql.Time;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("auth/login/")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/register/")
    Call<ResponseBody> registerRequest(@Field("email") String email,
                                       @Field("password") String password,
                                       @Field("phone") String phone,
                                       @Field("level") String level);

    @FormUrlEncoded
    @POST("laundry/")
    Call<ResponseBody> inputLaundryProfile(
            @Field("laundry_userid") Integer laundry_userid,
            @Field("laundry_locationid") String laundry_locationid,
            @Field("laundry_name") String laundry_name,
            @Field("laundry_pict") String laundry_pict,
            @Field("laundry_desc") String laundry_desc,
            @Field("laundry_phone") String laundry_phone,
            @Field("laundry_is_holiday") String laundry_is_holiday,
            @Field("laundry_address") String laundry_address,
            @Field("laundry_address_lat") String laundry_address_lat,
            @Field("laundry_address_lng") String laundry_address_lng,
            @Field("laundry_status") String laundry_status
    );

    @GET("laundry")
    Call<LaundryDataResponse> getLaundry();

    @FormUrlEncoded
    @POST("sch/")
    Call<ResponseBody> jadwal(
            @Field("nyucischedule_laundryid") Integer nyucischedule_laundryid,
            @Field("nyucischedule_day") String nyucischedule_day,
            @Field("nyucischedule_open_hours") String nyucischedule_open_hours,
            @Field("nyucischedule_close_hours") String nyucischedule_close_hours,
            @Field("nyucischedule_notes") String nyucischedule_notes
    );

    @FormUrlEncoded
    @POST("service/")
    Call<ResponseBody> service(
            @Field("nyuciservice_laundryid") Integer nyuciservice_laundryid,
            @Field("nyuciservice_serviceid") String nyuciservice_serviceid,
            @Field("nyuciservice_price_one") String nyuciservice_price_one,
            @Field("nyuciservice_pricetwo") String nyuciservice_pricetwo,
            @Field("nyuciservice_pricethree") String nyuciservice_pricethree,
            @Field("nyuciservice_pricefour") String nyuciservice_pricefour,
            @Field("nyuciservice_pricefive") String nyuciservice_pricefive,
            @Field("nyuciservice_pricesix") String nyuciservice_pricesix,
            @Field("nyuciservice_priceseven") String nyuciservice_priceseven,
            @Field("nyuciservice_notes") String nyuciservice_notes,
            @Field("nyuciservice_status") String nyuciservice_status
    );
}
