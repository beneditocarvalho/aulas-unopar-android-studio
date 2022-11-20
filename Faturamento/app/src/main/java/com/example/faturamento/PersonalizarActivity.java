package com.example.faturamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonalizarActivity extends AppCompatActivity {

    public EditText mEditTextNomeFantasia;
    private Button mBotao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar);

        mEditTextNomeFantasia.findViewById(R.id.editTextPersonName2);
        mBotao = findViewById(R.id.button2);

        mBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeFantasia = mEditTextNomeFantasia.getText().toString();

                if (!nomeFantasia.isEmpty()){
                    getSharedPreferences(MainActivity.ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE)
                            .edit().putString("nomeFantasia", nomeFantasia).apply();
                }

            }
        });

    }
}