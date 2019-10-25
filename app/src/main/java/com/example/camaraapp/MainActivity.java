package com.example.camaraapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;//el menú lateral
    private boolean menu_visible;//para gestionar si está visible o no el menú lateral

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.menu_visible = false;

        //iniciamos el menú
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //muestra el boton de para atrás (por defecto)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_view_headline_black_24dp);//personalizo con el del menu
        //asignamos listener del menú lateral
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener(this); //escucho los eventos de esta clase aquí


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id_item = item.getItemId();
        switch (id_item) {


            case android.R.id.home:
                if (menu_visible) {
                    drawerLayout.closeDrawers();
                    menu_visible = false;
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                    menu_visible = true;
                    Log.d("MIAPP", "'" + String.valueOf(item.getItemId()) +"'");


                }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        String menu = item.getTitle().toString();
        int npi = item.getOrder();//obtengo el número del punto de interés

        Log.d("MIAPP", "Ha tocado la opción " + menu + " " +npi);
        drawerLayout.closeDrawers();
        menu_visible = false;

        if (npi == 1){
            Intent intent = new Intent(this, TomarFotoDiscoActivity.class);
            startActivity(intent);

        }else if (npi == 2){
            Intent intent = new Intent(this, TomarFotoRam.class);
            startActivity(intent);
        }else if (npi == 3){
            Intent intent = new Intent(this, SeleccionaFotoActivity.class);
            startActivity(intent);
        }
        //Intent intent = new Intent(this, TomarFotoRam.class);
        //Intent intent = new Intent(this, SeleccionaFotoActivity.class);
        //startActivity(intent);

        return false;
    }


}

