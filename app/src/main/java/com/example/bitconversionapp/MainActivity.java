package com.example.bitconversionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber;
    Spinner spinnerUnits;
    Button buttonConvert;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.editTextNumber);
        spinnerUnits = findViewById(R.id.spinnerUnits);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        String[] units = {"Bits", "Bytes", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes", "Petabytes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnits.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
                addAnimation();
            }
        });
    }

    private void convertUnits() {
        String inputValue = editTextNumber.getText().toString();
        if (inputValue.isEmpty()) {
            textViewResult.setText("Please enter a value.");
            return;
        }

        double value = Double.parseDouble(inputValue);
        String selectedUnit = spinnerUnits.getSelectedItem().toString();

        double bits = convertToBits(value, selectedUnit);
        String result = "Conversions:\n";

        result += "Bits: " + bits + "\n";
        result += "Bytes: " + bits / 8 + "\n";
        result += "Kilobytes: " + bits / (8 * 1024) + "\n";
        result += "Megabytes: " + bits / (8 * 1024 * 1024) + "\n";
        result += "Gigabytes: " + bits / (8 * 1024 * 1024 * 1024) + "\n";
        result += "Terabytes: " + bits / (8L * 1024 * 1024 * 1024 * 1024) + "\n";
        result += "Petabytes: " + bits / (8L * 1024 * 1024 * 1024 * 1024 * 1024) + "\n";

        textViewResult.setText(result);
    }

    private double convertToBits(double value, String unit) {
        switch (unit) {
            case "Bits":
                return value;
            case "Bytes":
                return value * 8;
            case "Kilobytes":
                return value * 8 * 1024;
            case "Megabytes":
                return value * 8 * 1024 * 1024;
            case "Gigabytes":
                return value * 8 * 1024 * 1024 * 1024;
            case "Terabytes":
                return value * 8L * 1024 * 1024 * 1024 * 1024;
            case "Petabytes":
                return value * 8L * 1024 * 1024 * 1024 * 1024 * 1024;
            default:
                return value;
        }
    }

    private void addAnimation() {
        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        textViewResult.startAnimation(fadeIn);
    }
}
