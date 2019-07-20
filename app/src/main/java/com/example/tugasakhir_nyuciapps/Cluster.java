package com.example.tugasakhir_nyuciapps;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Cluster implements ClusterItem {

    private final String username;
    private final LatLng latLng;

    public Cluster(String username, LatLng latLng) {
        this.username = username;
        this.latLng = latLng;
    }

    @Override
    public LatLng getPosition() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
