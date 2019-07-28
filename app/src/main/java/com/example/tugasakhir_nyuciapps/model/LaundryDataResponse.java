package com.example.tugasakhir_nyuciapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaundryDataResponse {

    @SerializedName("error")
    private String error;

    @SerializedName("values")
    private List<Value> values;

    public String getMessage() {
        return error;
    }

    public void setMessage(String message) {
        this.error = message;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

}