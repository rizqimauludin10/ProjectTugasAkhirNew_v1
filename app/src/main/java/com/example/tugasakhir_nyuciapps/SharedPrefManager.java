package com.example.tugasakhir_nyuciapps;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SP_Nyuciin = "spNyuciin";

    public static final String SP_Phone = "spPhone";
    public static final String SP_Email = "spEmail";
    public static final String SP_Name = "spName";
    public static final Integer SP_UserId = 0;
    public static final Integer SP_LaundryId = 0;
    public static final Integer SP_LocationId = 0;

    public static final String SP_SudahLogin = "spSudahLogin";

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

    public void saveSPInt(Integer keySp, int value) {
        spEditor.putInt(String.valueOf(keySp), value);
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

    public int getSP_UserId() {
        return (int) sharedPreferences.getInt(String.valueOf(SP_UserId), 0);
    }

    public int getSP_LaundryId() {
        return (int) sharedPreferences.getInt(String.valueOf(SP_LaundryId), 0);
    }

    public int getSP_LocationId() {
        return (int) sharedPreferences.getInt(String.valueOf(SP_LocationId), 0);
    }

    public Boolean getSPSudahLogin() {
        return sharedPreferences.getBoolean(SP_SudahLogin, false);
    }
}
