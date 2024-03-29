package com.example.tugasakhir_nyuciapps;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.autofill.Validator;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.tugasakhir_nyuciapps.apihelper.BaseApiService;
import com.example.tugasakhir_nyuciapps.apihelper.UtilsApi;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

///import com.schibstedspain.leku.LocationPickerActivity;

public class InputActivity extends AppCompatActivity {


    Toolbar toolbar;
    ImageView backButton2;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    BaseApiService mApiService;
    EditText nama, alamat, phone, desc, lat, lng;
    TextInputLayout input, descinput, alamatinput, latinput, lnginput, hpinput;
    Double latEx, lngEX;
    String namaEx, alamatEx, phoneEx, kecEx, descEx;
    TextView tvLatLaundry;
    Spinner kecamatan;
    RadioGroup rglibur;
    RadioButton ya, tidak;
    Button simpan, openPhoto, bttampilMaps;
    ImageView Ivphoto, Ivphotosrc;
    Integer userid;
    String libur;
    String location;
    String status = "0";
    Bitmap bitmap;
    String vnama;


    public static final int IMG_REQUEST = 777;

    private int CAMERA = 2;

    ProgressDialog loading;

    private String[] kecamatanlist = {
            "Pontianak Barat",
            "Pontianak Kota",
            "Pontianak Selatan",
            "Pontianak Tenggara",
            "Pontianak Timur",
            "Pontianak Utara"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        sharedPrefManager = new SharedPrefManager(InputActivity.this.getApplicationContext());
        userid = sharedPrefManager.getSP_UserId();
        Log.e("debug", "Shared Preference LaundryId Input> " + userid);


        mContext = this;
        mApiService = UtilsApi.getApiService();

        toolbar = (Toolbar) findViewById(R.id.inputtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        nama = (EditText) findViewById(R.id.namaLaundry);
        alamat = (EditText) findViewById(R.id.alamatLaundry);
        phone = (EditText) findViewById(R.id.nohpLaundry);
        desc = (EditText) findViewById(R.id.descLaundry);
        rglibur = (RadioGroup) findViewById(R.id.rg_Libur);
        ya = (RadioButton) findViewById(R.id.rgLiburYa);
        tidak = (RadioButton) findViewById(R.id.rgLiburTidak);
        simpan = (Button) findViewById(R.id.simpan);
        kecamatan = (Spinner) findViewById(R.id.spinnerKecamatan);
        backButton2 = (ImageView) findViewById(R.id.backbutton2);
        openPhoto = (Button) findViewById(R.id.openCamera);
        Ivphoto = (ImageView) findViewById(R.id.photo);
        Ivphotosrc = (ImageView) findViewById(R.id.photosrc);
        lat = (EditText) findViewById(R.id.LatLaundry);
        lng = (EditText) findViewById(R.id.LngLaundry);
        tvLatLaundry = (TextView) findViewById(R.id.ketLat);
        bttampilMaps = (Button) findViewById(R.id.tampilMaps);
        input = (TextInputLayout) findViewById(R.id.layoutinput);
        descinput = (TextInputLayout) findViewById(R.id.descinput);
        alamatinput = (TextInputLayout) findViewById(R.id.inputalamat);
        hpinput = (TextInputLayout) findViewById(R.id.inputHp);
        latinput = (TextInputLayout) findViewById(R.id.inputlat);
        lnginput = (TextInputLayout) findViewById(R.id.inputlng);

        Ivphoto.setVisibility(View.INVISIBLE);

        //awesomeValidation= new AwesomeValidation(ValidationStyle.BASIC);
        //awesomeValidation.addValidation(this, R.id.namaLaundry, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            latEx = extras.getDouble("lat", 0);
            lngEX = extras.getDouble("lng", 0);
            lat.setText("" + latEx);
            lng.setText("" + lngEX);

            namaEx = extras.getString("nama");
            nama.setText(namaEx);

            alamatEx = extras.getString("alamat");
            alamat.setText(alamatEx);

            phoneEx = extras.getString("phone");
            phone.setText(phoneEx);

            /*kecEx = extras.getString("kec");
            kecamatan.getSelectedItem().toString();*/

            descEx = extras.getString("desc");
            desc.setText(descEx);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinnerlist, kecamatanlist);

        kecamatan.setAdapter(adapter);

        kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        location = "1";
                        break;
                    case 1:
                        location = "2";
                        break;
                    case 2:
                        location = "3";
                        break;
                    case 3:
                        location = "4";
                        break;
                    case 4:
                        location = "5";
                        break;
                    case 5:
                        location = "6";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(InputActivity.this, "Anda belum memilih lokasi ", Toast.LENGTH_SHORT).show();

            }
        });





        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputActivity.this, MainActivity.class));
                finish();
            }
        });

        openPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();

            }
        });

        rglibur.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (ya.isChecked()) {
                    libur = "1";
                } else {
                    libur = "0";
                }
            }
        });

        bttampilMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPlacePicker();

            }
        });


    }


    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Captire photo from camera"};
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        selectImage();
                        break;
                    case 1:
                        takePhotofromCamera();
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    public void selectImage() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, IMG_REQUEST);

    }


    private void takePhotofromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);

    }


    private void showPlacePicker() {
        Intent intent = new Intent(InputActivity.this, PlacePickerMapsActivity.class);
        intent.putExtra("nama", nama.getText().toString());
        intent.putExtra("desc", desc.getText().toString());
        intent.putExtra("phone", phone.getText().toString());
        intent.putExtra("alamat", alamat.getText().toString());
        intent.putExtra("kec", kecamatan.getSelectedItem().toString());
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                Ivphoto.setImageBitmap(bitmap);
                Ivphoto.setVisibility(View.VISIBLE);
                Ivphotosrc.setVisibility(View.INVISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA && resultCode == RESULT_OK && data != null) {
            bitmap = (Bitmap) data.getExtras().get("data");
            Ivphoto.setImageBitmap(bitmap);
            Ivphoto.setVisibility(View.VISIBLE);
            Ivphotosrc.setVisibility(View.INVISIBLE);

        }
    }

    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        String imgString = Base64.encodeToString(imgByte, Base64.NO_WRAP);

        return imgString;
    }


    private void requestSimpan() {
        String laundry_pict = imageToString(bitmap);
        mApiService.inputLaundryProfile(
                userid,
                location,
                nama.getText().toString(),
                laundry_pict,
                desc.getText().toString(),
                phone.getText().toString(),
                libur,
                alamat.getText().toString(),
                lat.getText().toString(),
                lng.getText().toString(),
                status
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "OnResponse : Berhasil");
                    loading.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("error").equals("false")) {

                            Integer laundryid = jsonObject.getJSONObject("laundry").getInt("id");

                            sharedPrefManager.saveSPInt(SharedPrefManager.SP_LaundryId, laundryid);

                            Log.i("debug", "Id Laundry" + laundryid);
                            startActivity(new Intent(mContext, JadwalActivity.class));
                            Toast.makeText(mContext, "Berhasil", Toast.LENGTH_SHORT).show();


                        } else {
                            String error = jsonObject.getString("error");
                            Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.i("debug", "OnResponse : Gagal");
                    Toast.makeText(mContext, "Gagal Input Data Laundry, Mohon Ulangi", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "OnFailure : Error > " + t.getMessage());
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void submitForm() {

        if (nama.length() == 0) {
            input.setError("Masukkan nama");
        } else if (desc.length() == 0) {
            descinput.setError("Masukkan deskripsi");
        } else if (phone.length() == 0) {
            hpinput.setError("Masukkan nomor handphone");
        } else if (alamat.length() == 0) {
            alamatinput.setError("Masukkan alamat");
        } else if (lat.length() == 0 && lng.length() == 0) {
            latinput.setError("Tekan Tombol Ubah Lokasi");
            lnginput.setError("Tekan Tombol Ubah Lokasi");
        } else if(Ivphoto.getDrawable() == null) {
            Toast.makeText(getApplicationContext(), "Tambahkan Foto Laundry", Toast.LENGTH_SHORT).show();
        }
        else {
            loading = ProgressDialog.show(mContext, null, "Harap Tunggu....", true, false);
            requestSimpan();
        }
    }

}


/*    public String saveImage(Bitmap bitmap) {
 *//* ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
      File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
                // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }*//*
        try {
            *//*namaFileGambar = Calendar.getInstance().getTimeInMillis() + ".JPG";
            f = new File(wallpaperDirectory, namaFileGambar);

            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            filepath = f.getAbsolutePath();

            return f.getAbsolutePath();*//*
            //return "";
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }*/
