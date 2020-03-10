package com.downloadpdf;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnDownload;
    String URL = "http://192.168.100.10/downloadpdf/contoh.pdf"; // ubah link ini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new DownloadTask(MainActivity.this, URL);
            }
        });

        getPermissionWriteStorage();
    }

    //Pengenal bagi permission request
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1;
    public void getPermissionWriteStorage() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    // Callback yang membawa hasil dari pemanggilan requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Pengecekan ini akan memastikan bahwa hasil yang diberikan berasal dari request
        // yang kita lakukan berdasarkan kode yang ditulis di atas
        if (requestCode == MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Izin Storage Diberikan", Toast.LENGTH_SHORT).show();
            } else {
                // showRationale = false jika pengguna memilih Never Ask Again, jika tidak bernilai true
                // boolean showRationale = shouldShowRequestPermissionRationale( this, Manifest.permission.ACCESS_FINE_LOCATION);

                //  if (showRationale) {
                // lakukan sesuatu disini
//                } else {
                Toast.makeText(this, "Izin Storage Ditolak", Toast.LENGTH_SHORT).show();
//                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
