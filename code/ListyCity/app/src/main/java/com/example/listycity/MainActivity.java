package com.example.listycity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;

    ArrayAdapter<String> cityAdapter;

    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        Button addButton = findViewById(R.id.button); // Add City Button
        Button deleteButton = findViewById(R.id.button2); // Delete City Button
        EditText editTextCity = findViewById(R.id.editTextText); // User input for city name

        String[] cities ={"Edmonton", "Paris", "London"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editTextCity.getText().toString().trim();
                if (TextUtils.isEmpty(cityName)) {
                    Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
                } else {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                    editTextCity.setText("");
                    Toast.makeText(MainActivity.this, "City added: " + cityName, Toast.LENGTH_SHORT).show();
                }
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editTextCity.getText().toString().trim();
                if (TextUtils.isEmpty(cityName)) {
                    Toast.makeText(MainActivity.this, "Please enter a city name to delete", Toast.LENGTH_SHORT).show();
                } else if (dataList.contains(cityName)) {
                    dataList.remove(cityName);
                    cityAdapter.notifyDataSetChanged();
                    editTextCity.setText("");
                    Toast.makeText(MainActivity.this, "City deleted: " + cityName, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "City not found: " + cityName, Toast.LENGTH_SHORT).show();
                }
            }
        });






    }
}