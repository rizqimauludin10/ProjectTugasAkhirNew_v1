package com.example.tugasakhir_nyuciapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaundryDataResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("values")
    @Expose
    private List<Value> values;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

}