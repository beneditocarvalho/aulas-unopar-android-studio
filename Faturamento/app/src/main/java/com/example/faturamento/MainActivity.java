package com.example.faturamento;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String ARQUIVO_MEUS_DADOS = "Meu arquivo";
    Button mBotaoConfirmar;
    TextView mtexto;
    NumberPicker mNumberPicker;
    EditText mEditText;

    private void adicionarValor(int ano, float valor){
        SharedPreferences sharedPreferences = getSharedPreferences(
                ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE
        );

        sharedPreferences.edit().putFloat(String.valueOf(ano), valor).apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBotaoConfirmar = findViewById(R.id.button);
        mtexto = findViewById(R.id.textView);
        mNumberPicker = findViewById(R.id.numberPicker);
        mEditText = findViewById(R.id.editTextTextPersonName2);

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

                int ano = mNumberPicker.getValue();
                float valor = Float.parseFloat(mEditText.getText().toString());
                adicionarValor(ano, valor);
            }
        });
    }
}