package com.example.tugasakhir_nyuciapps.apihelper;

public class UtilsApi {

    public static final String BASE_URL_API = "https://laundry.rumahkite.co.id/api/";
    //public static final String BASE_URL_API = "http://172.16.1.40:8000/api/";

    public static BaseApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
