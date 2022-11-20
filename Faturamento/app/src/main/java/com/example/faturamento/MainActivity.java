package com.example.faturamento;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button mBotaoConfirmar;
    TextView mtexto;
    NumberPicker mNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBotaoConfirmar = findViewById(R.id.button);
        mtexto = findViewById(R.id.textView);
        mNumberPicker = findViewById(R.id.numberPicker);

        mNumberPicker.setMinValue(2000);
        mNumberPicker.setMaxValue(2022);

        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Toast.makeText(MainActivity.this,"Teste do listener!", Toast.LENGTH_SHORT).show();
            }
        });

        mBotaoConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mtexto.setText("Teste de botao!");
            }
        });
    }
}