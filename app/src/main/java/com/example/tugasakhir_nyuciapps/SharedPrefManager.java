package com.example.tugasakhir_nyuciapps;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SP_Nyuciin = "spNyuciin";

    public static final String SP_Phone = "spPhone";
    public static final String SP_Email = "spEmail";

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

    public void saveSPInt(String keySp, int value) {
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

    public Boolean getSPSudahLogin() {
        return sharedPreferences.getBoolean(SP_SudahLogin, false);
    }
}
