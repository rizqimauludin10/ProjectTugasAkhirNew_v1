package com.example.tugasakhir_nyuciapps.apihelper;

public class UtilsApi {

    public static final String BASE_URL_API = "http://192.168.43.93:8000/api/";

    public static BaseApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
