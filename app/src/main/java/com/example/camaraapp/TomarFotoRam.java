package com.example.camaraapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class TomarFotoRam extends AppCompatActivity {

    private static final String [] PERMISOS = {Manifest.permission.CAMERA};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_foto_ram);

        ActivityCompat.requestPermissions(this, PERMISOS, 100);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //suponemos que nos dice que sí
        Intent tomar_foto_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (tomar_foto_intent.resolveActivity(getPackageManager())!=null)
        {
            Log.i("MIAPP", "Al menos hay una app que toma fotos");
            startActivityForResult(tomar_foto_intent, 150);
        }

        //Actividades candidatas para el Intent
        List<ResolveInfo> lista_paquetes = getPackageManager().queryIntentActivities(tomar_foto_intent, PackageManager.MATCH_DEFAULT_ONLY);

        for(ResolveInfo resolveInfo: lista_paquetes){
            Log.i("MIAPP", "APPS candidatas " + resolveInfo.activityInfo.packageName);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        Log.i("MIAPP", "Ha venido de tomar la foto");
        switch (resultCode){
            case RESULT_OK: Log.i("MIAPP", "La foto fue bien");
                Bundle saco = data.getExtras();
                Bitmap bitmap = (Bitmap)saco.get("data");
                if (bitmap!=null)
                {
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageBitmap(bitmap);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }else{
                    Log.i("MIAPP", "No hay una app de cámara compatible");

                    Toast mensaje = Toast.makeText(this, "No hay una app de cámara", Toast.LENGTH_LONG);
                    mensaje.show();
                }

                break;
            case RESULT_CANCELED: Log.i("MIAPP", "Foto abortada");
                break;
        }

    }
}

