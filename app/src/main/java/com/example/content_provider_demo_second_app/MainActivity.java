package com.example.content_provider_demo_second_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String AUTHORITY = "com.demo.device.provider";
    public static final String CONTENT_PATH = "device";
    Uri CONTENT_URI = Uri.parse("content://" +AUTHORITY+"/"+CONTENT_PATH);
    Button btnLoadDevices;
    TextView txtListOfDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoadDevices = findViewById(R.id.btnLoadDevices);
        txtListOfDevices = findViewById(R.id.txtListOfDevices);
    }

    public void getListOfDevices(View view) {
        try {
            Cursor cr = getContentResolver().query(CONTENT_URI, null, null, null, "_id");
            StringBuilder stringBuilder = new StringBuilder();

            while (cr.moveToNext()) {
                int id = cr.getInt(0);
                String name = cr.getString(1);
                String quantity = cr.getString(2);
                String typeName = cr.getString(3);
                stringBuilder.append(id + "  " + name + " " + quantity + " " + typeName + "\n");
            }
            txtListOfDevices.setText(stringBuilder);
        } catch (Exception ex) {
            ex.printStackTrace();
//            txtListOfDevices.setText(CONTENT_URI.toString());
        }
    }
}