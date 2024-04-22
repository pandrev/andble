package com.example.myqeue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnTask1;
    Button btnTask2;
    Button btnTask3;

    ConnectionManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cm = new ConnectionManager();
        btnTask1 = findViewById(R.id.btnTask1);
        btnTask2 = findViewById(R.id.btnTask2);
        btnTask3 = findViewById(R.id.btnTask3);

        btnTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm.connect(device, cona);
            }
        });

        btnTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm.enqueueOperation(new TaskTwo(cm));
            }
        });

        btnTask3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm.enqueueOperation(new TaskThree(cm));
            }
        });
    }

}