package com.deepshikha.nwwa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.deepshikha.nwwa.ui.AdminLogin;
import com.deepshikha.nwwa.ui.home.kala.KalaKendra;
import com.deepshikha.nwwa.ui.home.saundrya.Saundrya;
import com.deepshikha.nwwa.ui.samudri.ProviderLogin;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setExitTransition(new Explode());
//        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_main2);
        String value = getIntent().getExtras().getString("FragName");
//opening respective fragment
        switch (value) {
            case "kala":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new KalaKendra())
                        .commit();
                break;
            case "saundrya":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new Saundrya())
                        .commit();
                break;
           case "adminLogin":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new AdminLogin())
                        .commit();
                break;
           case "providerLogin":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ProviderLogin())
                        .commit();
                break;

           case "saudryaConsol":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ProviderLogin())
                        .commit();
                break;
        }
    }
}
