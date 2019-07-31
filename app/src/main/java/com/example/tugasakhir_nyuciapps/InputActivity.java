package com.example.tugasakhir_nyuciapps;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import com.example.tugasakhir_nyuciapps.apihelper.BaseApiService;
import com.example.tugasakhir_nyuciapps.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class InputActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView backButton2;
    SharedPrefManager sharedPrefManager;

    Context mContext;
    BaseApiService mApiService;
    EditText nama, alamat, phone, desc, lat, lng;
    TextView tvPath;

    Spinner kecamatan;

    RadioGroup rglibur;
    RadioButton ya, tidak;
    Button simpan, openPhoto;
    Integer userid;
    String libur;
    String location;
    String status = "1";
    String path;
    String namaFileGambar;
    String gambar;

    private ImageView Ivphoto;
    private static final String IMAGE_DIRECTORY = "/nyuciin";
    private int GALLERY = 1, CAMERA = 2;

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
        lat = (EditText) findViewById(R.id.LatLaundry);
        lng = (EditText) findViewById(R.id.LngLaundry);

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


        sharedPrefManager = new SharedPrefManager(InputActivity.this.getApplicationContext());
        userid = sharedPrefManager.getSP_UserId();


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu....", true, false);
                requestSimpan();
            }
        });

        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputActivity.this, InputJadwalActivity.class));
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
                        choosePhotofromGallery();
                        break;
                    case 1:
                        takePhotofromCamera();
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    public void choosePhotofromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotofromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    path = saveImage(bitmap);
                    Toast.makeText(InputActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    Ivphoto.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(InputActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            Ivphoto.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            //Toast.makeText(InputActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        try {
            String file = Calendar.getInstance().getTimeInMillis() + ".jpg";
            namaFileGambar = file;
            File f = new File(wallpaperDirectory, file);

            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


    private void requestSimpan() {
        mApiService.inputLaundryProfile(
                userid,
                location,
                nama.getText().toString(),
                namaFileGambar,
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
                            Toast.makeText(mContext, "Berhasil Input Data Laundry", Toast.LENGTH_SHORT).show();
                            Integer laundryid = jsonObject.getJSONObject("data").getInt("id");
                            Integer locationid = jsonObject.getJSONObject("data").getInt("laundry_locationid");

                            sharedPrefManager.saveSPInt(SharedPrefManager.SP_LaundryId, laundryid);
                            sharedPrefManager.saveSPInt(SharedPrefManager.SP_LocationId, locationid);
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
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
