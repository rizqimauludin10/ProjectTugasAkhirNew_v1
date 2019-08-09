package com.example.tugasakhir_nyuciapps;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SP_Nyuciin = "spNyuciin";

    public static final String SP_Phone = "spPhone";
    public static final String SP_Email = "spEmail";
    public static final String SP_Name = "spName";
    public static final String SP_UserId = "user";
    public static final String SP_LaundryId = "laundry";
    public static final Integer SP_LocationId = 0;

    public static final String SP_SudahLoginPemilik = "spSudahLoginPemilik";
    public static final String SP_SudahLoginPencari = "spSudahLoginPencari";



    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_Nyuciin, Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }

    public void saveSPString(String keySp, String value) {
        spEditor.putString(keySp, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySp, int value) {
        spEditor.putInt(keySp, value);
        spEditor.commit();
    }

    public void saveSPIntUser(String keySp, int value) {
        spEditor.putInt(keySp, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySp, boolean value) {
        spEditor.putBoolean(keySp, value);
        spEditor.commit();
    }

    public String getSP_Phone() {
        return sharedPreferences.getString(SP_Phone, "");
    }

    public String getSp_Email() {
        return sharedPreferences.getString(SP_Email, "");
    }

    public String getSp_Name() {
        return sharedPreferences.getString(SP_Name, "");
    }

    public Integer getSP_UserId() {
        return (Integer) sharedPreferences.getInt(String.valueOf(SP_UserId), 0);
    }

    public Integer getSP_LaundryId() {
        return (Integer) sharedPreferences.getInt(String.valueOf(SP_LaundryId), 0);
    }

    public Integer getSP_LocationId() {
        return (Integer) sharedPreferences.getInt(String.valueOf(SP_LocationId), 0);
    }

    public Boolean getSPSudahLoginPemilik() {
        return sharedPreferences.getBoolean(SP_SudahLoginPemilik, false);
    }

    public Boolean getSPSudahLoginPencari() {
        return sharedPreferences.getBoolean(SP_SudahLoginPencari, false);
    }
}
