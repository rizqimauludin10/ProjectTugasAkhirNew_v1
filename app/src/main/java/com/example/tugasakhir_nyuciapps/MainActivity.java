package com.example.tugasakhir_nyuciapps;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText search_content;
    Dialog epicDialog;
    Button cs_btnTidak, cs_btnYa;
    ImageView closeBtn;
    TextView title_cs, desc_cs;
    ProgressDialog progressBar;

    Context mContext;

    private InternetCheck internetCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        epicDialog = new Dialog(this);
        cs_btnTidak = (Button) findViewById(R.id.cs_btnTidak);
        cs_btnYa = (Button) findViewById(R.id.cs_btnYa);
        closeBtn = (ImageView) findViewById(R.id.btn_close);
        title_cs = (TextView) findViewById(R.id.title_cs);
        desc_cs = (TextView) findViewById(R.id.desc_cs);

        search_content = findViewById(R.id.search_activity_content);

        search_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Search.class));
                finish();

            }
        });

        //Internet Connection Check
        internetCheck = new InternetCheck(this);
        if (!internetCheck.isConnected(MainActivity.this)) {

            internetCheck.buildDialog(MainActivity.this).show();
        } else {
            //Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.customerservice) {
            /*progressBar = ProgressDialog.show(MainActivity.this, null, "Sedang Menghubungkan...", true, false);*/
            dialog();
        }

        return super.onOptionsItemSelected(item);
    }

    public void dialog() {

        epicDialog.setContentView(R.layout.cs_dialog);
        closeBtn = (ImageView) epicDialog.findViewById(R.id.btn_close);
        cs_btnYa = (Button) epicDialog.findViewById(R.id.cs_btnYa);
        cs_btnTidak = (Button) epicDialog.findViewById(R.id.cs_btnTidak);
        title_cs = (TextView) epicDialog.findViewById(R.id.title_cs);
        desc_cs = (TextView) epicDialog.findViewById(R.id.desc_cs);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });

        cs_btnYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "6289685191803";
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://api.whatsapp.com/send?phone=" + phone + "&text=Saya%20perlu%20bantuan%20Admin%20Nyuci.in"));
                startActivity(intent);
            }
        });

        cs_btnTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent intent;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_add) {
            intent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_profile) {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_signout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
