package com.example.test_epic.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.test_epic.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class bottom_bar extends AppCompatActivity {

    public static String userName = null;
    public static String accessToken = null;
    public static String refreshToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        BottomNavigationView navView = (BottomNavigationView) findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_feed, R.id.navigation_upload_image, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        initValues();
    }

    private void initValues() {
        Bundle b = getIntent().getExtras();
        if(b != null) {
            userName = b.getString("username");
            accessToken = b.getString("accessToken");
            refreshToken = b.getString("refreshToken");
        }
    }
}
