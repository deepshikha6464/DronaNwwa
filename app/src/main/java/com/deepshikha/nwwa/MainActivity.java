package com.deepshikha.nwwa;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, Main2Activity.class);

        switch (item.getItemId()) {
            case R.id.admin:
                intent.putExtra("FragName", "adminLogin");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                return true;
            case R.id.provider:
                intent.putExtra("FragName", "providerLogin");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                return  true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        if(navView.getSelectedItemId() == R.id.navigation_home){

//            Fragment f = this.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//            if(f instanceof Day1Fragment) {
//                // do something with f
//                Log.d(TAG, "onBackPressed: ");
//                getSupportFragmentManager().beginTransaction().remove(f).commit();
//
//            }
            finishAffinity();

        }else{
            navView.setSelectedItemId(R.id.navigation_home);
        }
         }
}
