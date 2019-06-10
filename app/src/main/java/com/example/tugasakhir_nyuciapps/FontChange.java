package com.example.tugasakhir_nyuciapps;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FontChange extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/TitilliumWeb-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
