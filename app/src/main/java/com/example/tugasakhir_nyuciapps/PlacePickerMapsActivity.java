package com.example.tugasakhir_nyuciapps;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PlacePickerMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = PlacePickerMapsActivity.class.getSimpleName();
    private GoogleMap mMap;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private TextView resutText;
    private Button getLokasi;
    private final float DEFAULT_ZOOM = 15;
    private Location location;
    private double lat, lng;
    String nama, alamat, phone, desc, kecamatan;


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

        Bundle extras = getIntent().getExtras();
        nama = extras.getString("nama");
        desc = extras.getString("desc");
        phone = extras.getString("phone");
        kecamatan = extras.getString("kec");
        alamat = extras.getString("alamat");


        getLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLokasi.setEnabled(false);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getLokasi.setEnabled(true);
                                Intent intent = new Intent(PlacePickerMapsActivity.this, InputActivity.class);

                                intent.putExtra("lat", lat);
                                intent.putExtra("lng", lng);
                                intent.putExtra("nama", nama);
                                intent.putExtra("desc", desc);
                                intent.putExtra("phone", phone);
                                intent.putExtra("kec", kecamatan);
                                intent.putExtra("alamat", alamat);

                                startActivity(intent);
                            }
                        });
                    }
                }, 1000);




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

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        //titik biru lokasi user pada maps
        //mMap.getUiSettings().setMyLocationButtonEnabled(true);

        boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        if (!success) {
            Log.e(TAG, "Style parsing failed.");
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-0.03109, 109.32199);
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, DEFAULT_ZOOM));
        mMap.setOnCameraIdleListener(onCameraIdleListener);
        /* mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));*/
        /*mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()),DEFAULT_ZOOM));*/
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
