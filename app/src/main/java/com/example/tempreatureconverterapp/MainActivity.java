package com.example.tempreatureconverterapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editInput;
    private TextView textResult;
    private TextView textResultType;
    private Button selectType;
    private String selectedUnit;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DecimalFormat df = new DecimalFormat("#.##");
        selectedUnit = "Fahrenheit";

        editInput = findViewById(R.id.editInput);
        textResult = findViewById(R.id.textResult);
        textResultType = findViewById(R.id.textResultType);
        selectType = findViewById(R.id.selectType);

        selectType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        editInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String resultText;
                String inputVal = editInput.getText().toString();
                if (!inputVal.isEmpty()) {
                    double doubleInput = Double.parseDouble(inputVal);
                    if (selectedUnit.equals("Fahrenheit")) {
                        resultText = df.format((doubleInput - 32) * 5 / 9);
                        textResultType.setText("Celsius");
                    } else {
                        resultText = df.format((doubleInput * 9 / 5) + 32);
                        textResultType.setText("Fahrenheit");
                    }
                    textResult.setText(resultText);
                }
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Select Unit");

        final String[] items = {"Fahrenheit", "Celsius"};
        final int[] checkedItem = {-1};

        alertDialog.setSingleChoiceItems(items, checkedItem[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedUnit = items[which];
                textResultType.setText(items[which]);
                checkedItem[0] = which;
            }
        });

        alertDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
}
