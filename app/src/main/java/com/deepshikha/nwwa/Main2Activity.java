package com.deepshikha.nwwa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.deepshikha.nwwa.ui.KalaKendra;
import com.deepshikha.nwwa.ui.SamudriItems;
import com.deepshikha.nwwa.ui.Saundrya;

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
        }
    }
}
