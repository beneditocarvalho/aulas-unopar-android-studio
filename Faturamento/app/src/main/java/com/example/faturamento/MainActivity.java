package com.example.faturamento;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String ARQUIVO_MEUS_DADOS = "Meu arquivo";
    TextView mTextViewSaldo;
    EditText mEditTextValor;
    NumberPicker mNumberPicker;
    Button mBotaoConfirmar;
    Button mBotaoTitulo;
    RadioGroup mRadioGroup;


    private void adicionarValor(int ano, float valor){
        SharedPreferences sharedPreferences = getSharedPreferences(
                ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);

        float valorAtual = sharedPreferences.getFloat(String.valueOf(ano), 0);
        float novoValor = valorAtual + valor;
        sharedPreferences.edit().putFloat(String.valueOf(ano), novoValor).apply();
    }

    private void excluirValor(int ano,float valor) {
        SharedPreferences sharedPreferences =
                getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);

        float valorAtual = sharedPreferences.getFloat(String.valueOf(ano), 0);
        float novoValor = valorAtual - valor;
        if (novoValor < 0) {
            novoValor = 0;
        }
        sharedPreferences.edit()
                .putFloat(String.valueOf(ano), novoValor)
                .apply();
    }

    private void exibirSaldo(int ano){
        SharedPreferences sharedPreferences =
                getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);
        float saldo = sharedPreferences.getFloat(String.valueOf(ano),0);
        mTextViewSaldo.setText(String.format("R$ %.2f",saldo));

    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mBotaoConfirmar = findViewById(R.id.button);
            mBotaoTitulo = findViewById(R.id.ButtonTitulo);
            mTextViewSaldo = findViewById(R.id.mTextViewSaldo);
            mRadioGroup = findViewById(R.id.radioGroup);
            mNumberPicker = findViewById(R.id.numberPicker);
            mEditTextValor = findViewById(R.id.mEditTextValor);

            mNumberPicker.setMinValue(2000);
            mNumberPicker.setMaxValue(2022);

            mBotaoConfirmar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!mEditTextValor.getText().toString().isEmpty()) {

                        float valor = Float.parseFloat(mEditTextValor.getText().toString());
                        int ano = mNumberPicker.getValue();

                        switch (mRadioGroup.getCheckedRadioButtonId()) {

                            case R.id.radioButton:
                                adicionarValor(ano, valor);
                                break;
                            case R.id.radioButton2:
                                excluirValor(ano, valor);
                                break;
                        }
                        exibirSaldo(ano);

                    }
                }
            });

            mBotaoTitulo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), PersonalizarActivity.class);
                    startActivity(intent);
                }
            });
        }

            @Override
            protected void onResume() {
                super.onResume();

                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);
                String nomeFantasia = sharedPreferences.getString("nomeFantasia", null);
                if (nomeFantasia!=null){
                    setTitle(nomeFantasia);
                }

                int ano = mNumberPicker.getValue();
                exibirSaldo(ano);
            }
}