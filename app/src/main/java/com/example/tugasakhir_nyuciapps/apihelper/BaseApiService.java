package com.example.tugasakhir_nyuciapps.apihelper;

import com.example.tugasakhir_nyuciapps.model.LaundryDataResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
