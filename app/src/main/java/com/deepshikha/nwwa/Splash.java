package com.deepshikha.nwwa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(Splash.this, GoogleSignInActivity.class);
                startActivity(i);
                finish();
            }
        }, 300);
    }
}

///        obj initialization
//        sp = new SharedPref(this);
//        USER AUTHORIZATION /AUTHENTICATION CHECKING
//        if(!sp.getUserAuthorization()){           // if user is not authorized, then open register
////            open register fragment
//            Intent i = new Intent(this,Register.class);
//            startActivity(i);
//
//
//        }
