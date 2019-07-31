package com.example.tugasakhir_nyuciapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("laundry_id")
    @Expose
    private Integer laundryId;

    @SerializedName("laundry_pict")
    @Expose
    private String laundryPict;

    @SerializedName("laundry_name")
    @Expose
    private String laundryName;

    @SerializedName("laundry_desc")
    @Expose
    private String laundryDesc;

    @SerializedName("laundry_phone")
    @Expose
    private String laundryPhone;

    @SerializedName("created_at")
    @Expose

    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("location_name")
    @Expose
    private String locationName;

    @SerializedName("laundry_address")
    @Expose
    private String laundryAddress;

    @SerializedName("location_desc")
    @Expose
    private String locationDesc;

    @SerializedName("location_postcode")
    @Expose
    private Integer locationPostcode;

    @SerializedName("laundry_address_lat")
    @Expose
    private String laundryAddressLat;

    @SerializedName("laundry_address_lng")
    @Expose
    private String laundryAddressLng;

    @SerializedName("laundry_is_holiday")
    @Expose
    private Integer laundryIsHoliday;

    @SerializedName("nyucischedule_day")
    @Expose
    private String nyucischeduleDay;

    @SerializedName("nyucischedule_open_hours")
    @Expose
    private String nyucischeduleOpenHours;

    @SerializedName("nyucischedule_close_hours")
    @Expose
    private String nyucischeduleCloseHours;

    @SerializedName("nyucischedule_notes")
    @Expose
    private String nyucischeduleNotes;

    @SerializedName("service_name")
    @Expose
    private String serviceName;

    @SerializedName("service_shortname")
    @Expose
    private String serviceShortname;

    @SerializedName("nyuciservice_price")
    @Expose
    private String nyuciservicePrice;

    @SerializedName("nyuciservice_notes")
    @Expose
    private String nyuciserviceNotes;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    public Integer getLaundryId() {
        return laundryId;
    }

    public void setLaundryId(Integer laundryId) {
        this.laundryId = laundryId;
    }

    public String getLaundryPict() {
        return laundryPict;
    }

    public void setLaundryPict(String laundryPict) {
        this.laundryPict = laundryPict;
    }

    public String getLaundryName() {
        return laundryName;
    }

    public void setLaundryName(String laundryName) {
        this.laundryName = laundryName;
    }

    public String getLaundryDesc() {
        return laundryDesc;
    }

    public void setLaundryDesc(String laundryDesc) {
        this.laundryDesc = laundryDesc;
    }

    public String getLaundryPhone() {
        return laundryPhone;
    }

    public void setLaundryPhone(String laundryPhone) {
        this.laundryPhone = laundryPhone;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLaundryAddress() {
        return laundryAddress;
    }

    public void setLaundryAddress(String laundryAddress) {
        this.laundryAddress = laundryAddress;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public Integer getLocationPostcode() {
        return locationPostcode;
    }

    public void setLocationPostcode(Integer locationPostcode) {
        this.locationPostcode = locationPostcode;
    }

    public String getLaundryAddressLat() {
        return laundryAddressLat;
    }

    public void setLaundryAddressLat(String laundryAddressLat) {
        this.laundryAddressLat = laundryAddressLat;
    }

    public String getLaundryAddressLng() {
        return laundryAddressLng;
    }

    public void setLaundryAddressLng(String laundryAddressLng) {
        this.laundryAddressLng = laundryAddressLng;
    }

    public Integer getLaundryIsHoliday() {
        return laundryIsHoliday;
    }

    public void setLaundryIsHoliday(Integer laundryIsHoliday) {
        this.laundryIsHoliday = laundryIsHoliday;
    }

    public String getNyucischeduleDay() {
        return nyucischeduleDay;
    }

    public void setNyucischeduleDay(String nyucischeduleDay) {
        this.nyucischeduleDay = nyucischeduleDay;
    }

    public String getNyucischeduleOpenHours() {
        return nyucischeduleOpenHours;
    }

    public void setNyucischeduleOpenHours(String nyucischeduleOpenHours) {
        this.nyucischeduleOpenHours = nyucischeduleOpenHours;
    }

    public String getNyucischeduleCloseHours() {
        return nyucischeduleCloseHours;
    }

    public void setNyucischeduleCloseHours(String nyucischeduleCloseHours) {
        this.nyucischeduleCloseHours = nyucischeduleCloseHours;
    }

    public String getNyucischeduleNotes() {
        return nyucischeduleNotes;
    }

    public void setNyucischeduleNotes(String nyucischeduleNotes) {
        this.nyucischeduleNotes = nyucischeduleNotes;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceShortname() {
        return serviceShortname;
    }

    public void setServiceShortname(String serviceShortname) {
        this.serviceShortname = serviceShortname;
    }

    public String getNyuciservicePrice() {
        return nyuciservicePrice;
    }

    public void setNyuciservicePrice(String nyuciservicePrice) {
        this.nyuciservicePrice = nyuciservicePrice;
    }

    public String getNyuciserviceNotes() {
        return nyuciserviceNotes;
    }

    public void setNyuciserviceNotes(String nyuciserviceNotes) {
        this.nyuciserviceNotes = nyuciserviceNotes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}