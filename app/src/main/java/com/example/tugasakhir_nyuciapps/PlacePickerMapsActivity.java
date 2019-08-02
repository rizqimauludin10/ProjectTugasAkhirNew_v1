package com.example.tugasakhir_nyuciapps;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class PlacePickerMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private TextView resutText;
    private Button getLokasi;
    private final float DEFAULT_ZOOM = 15;
    private Location location;
    private Double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker_maps);
        resutText = (TextView) findViewById(R.id.dragg_result);
        getLokasi = (Button) findViewById(R.id.btnGetLokasi);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        configureCameraIdle();

        getLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void configureCameraIdle() {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(PlacePickerMapsActivity.this);

                try {
                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        String locality = addressList.get(0).getAddressLine(0);
                        String country = addressList.get(0).getCountryName();
                        lat = addressList.get(0).getLatitude();
                        lng = addressList.get(0).getLongitude();
                        if (!locality.isEmpty() && !country.isEmpty())
                            resutText.setText(locality + "  " + country);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-0.03109, 109.32199);
        mMap = googleMap;
        mMap.setOnCameraIdleListener(onCameraIdleListener);
        /* mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));*/
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, DEFAULT_ZOOM));
        /*mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()),DEFAULT_ZOOM));*/
    }
}
