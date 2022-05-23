package com.example.yo7a.healthwatcher;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

//public class Drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
//    Toolbar toolbar;
//
//    @Override
//    public void setContentView(View view){
//        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.primary, null);
//        FrameLayout containers = drawerLayout.findViewById(R.id.containers);
//        containers.addView(view);
//        super.setContentView(drawerLayout);
//
//        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//
//        NavigationView navigationView = drawerLayout.findViewById(R.id.navigationview);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
//
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//    }
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }
//
//    protected void allocateActivityTitle(String titleString){
//        if(getSupportActionBar()!=null){
//            getSupportActionBar().setTitle(titleString);
//        }
//    }
//}
