package com.example.faturamento;

import android.content.Context;
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
    EditText mEditText;
    NumberPicker mNumberPicker;
    Button mBotaoConfirmar;
    RadioGroup mRadioGroup;

    private NumberPicker.OnValueChangeListener valorAlteradoListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker numberPicker, int valorAntigo, int valorAtual) {
            exibirSaldo(valorAtual);
        }
    };


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

        mTextViewSaldo = findViewById(R.id.mTextViewSaldo);
        mEditText = findViewById(R.id.editTextTextPersonName2);
        mRadioGroup = findViewById(R.id.radioGroup);
        mBotaoConfirmar = findViewById(R.id.button);
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
                if(!mEditText.getText().toString().isEmpty()){

                float valor = Float.parseFloat(mEditText.getText().toString());
                int ano = mNumberPicker.getValue();

                switch (mRadioGroup.getCheckedRadioButtonId()){

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
    }
}