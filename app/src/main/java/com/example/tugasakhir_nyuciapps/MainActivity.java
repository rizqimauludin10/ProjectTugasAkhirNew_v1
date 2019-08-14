package com.example.tugasakhir_nyuciapps;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    Button cs_btnTidak, cs_btnYa, btnLoginDrawer, btncsLogin;
    ImageView closeBtn, headerpict, closeBtnLogin;
    TextView title_cs, desc_cs, desc_login;
    TextView prusername, prphone;
    TextView hai;
    String username, phone;
    ProgressDialog progressBar;
    NavigationView navigationView;
    Integer iduser = 0;

    Intent intent;

    Context mContext;

    SharedPrefManager sharedPrefManager;

    private InternetCheck internetCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = new SharedPrefManager(MainActivity.this.getApplicationContext());

        Log.e("debug", "Shared Preference UserId > " + sharedPrefManager.getSP_UserId());



        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        epicDialog = new Dialog(this);
        cs_btnTidak = (Button) findViewById(R.id.cs_btnTidak);
        cs_btnYa = (Button) findViewById(R.id.cs_btnYa);
        closeBtn = (ImageView) findViewById(R.id.btn_close);
        closeBtnLogin = (ImageView) findViewById(R.id.btn_closeLogin);

        title_cs = (TextView) findViewById(R.id.title_cs);
        desc_cs = (TextView) findViewById(R.id.desc_cs);

        desc_login = (TextView) findViewById(R.id.desc_login);

        prusername = (TextView) headerView.findViewById(R.id.prusername);
        prphone = (TextView) headerView.findViewById(R.id.prphone);
        headerpict = (ImageView) headerView.findViewById(R.id.imageView);
        btnLoginDrawer = (Button) headerView.findViewById(R.id.btnlogindrawer);


        hai = (TextView) findViewById(R.id.hai);

        search_content = findViewById(R.id.search_activity_content);

        search_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                finish();

            }
        });

        btnLoginDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        //Internet Connection Check
        internetCheck = new InternetCheck(this);
        if (!internetCheck.isConnected(MainActivity.this)) {

            internetCheck.buildDialog(MainActivity.this).show();
        } else {

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


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("name");
            phone = extras.getString("phone");
        }

        String email = sharedPrefManager.getSp_Email();
        String name = sharedPrefManager.getSp_Name();
        String phone = sharedPrefManager.getSP_Phone();

        prusername.setText(email);
        prphone.setText(phone);

        hideMenu();

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
            if (sharedPrefManager.getSPSudahLoginPemilik().equals(true) || sharedPrefManager.getSPSudahLoginPencari().equals(true)) {
                dialog();
            } else {
                dialogLogin();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    public void dialogLogin() {
        epicDialog.setContentView(R.layout.cs_login);
        closeBtnLogin = (ImageView) epicDialog.findViewById(R.id.btn_closeLogin);
        desc_login = (TextView) epicDialog.findViewById(R.id.desc_login);
        btncsLogin = (Button) epicDialog.findViewById(R.id.btn_cslogin);

        closeBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });

        btncsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

    }

    public void dialogLoginPem() {
        epicDialog.setContentView(R.layout.cs_pemilik_login);
        closeBtnLogin = (ImageView) epicDialog.findViewById(R.id.btn_closeLogin);
        desc_login = (TextView) epicDialog.findViewById(R.id.desc_login);
        btncsLogin = (Button) epicDialog.findViewById(R.id.btn_cslogin);

        closeBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
            }
        });

        btncsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialog.dismiss();
               /* intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);*/
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

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
            if (sharedPrefManager.getSPSudahLoginPemilik().equals(true)) {
                intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
            } else if (sharedPrefManager.getSPSudahLoginPencari().equals(true)) {
                dialogLoginPem();
            }



        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_signout) {
            sharedPrefManager.saveSPInt(SharedPrefManager.SP_UserId, iduser);
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SudahLoginPemilik, false);
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SudahLoginPencari, false);
            startActivity(new Intent(MainActivity.this, LauncherActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /*MenuItem profile = menu.findItem(R.id.customerservice);*/

        return true;
    }


    private void hideMenu() {
        Menu nav_menu = navigationView.getMenu();

        if (sharedPrefManager.getSPSudahLoginPemilik().equals(true) || sharedPrefManager.getSPSudahLoginPencari().equals(true)) {
            nav_menu.findItem(R.id.nav_signout).setVisible(true);
            btnLoginDrawer.setVisibility(View.GONE);
            headerpict.setVisibility(View.VISIBLE);

        } else {
            nav_menu.findItem(R.id.nav_signout).setVisible(false);
            //nav_menu.findItem(R.id.nav_profile).setVisible(false);
            prusername.setText("Kamu belum login, login dulu yuk");
            prphone.setText("");
            btnLoginDrawer.setVisibility(View.VISIBLE);
            headerpict.setVisibility(View.GONE);
        }


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
