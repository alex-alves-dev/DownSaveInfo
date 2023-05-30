package com.example.downsaveinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Tela2 extends AppCompatActivity {

    String mensagemRecebida;
    TextView exibeMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        exibeMensagem= findViewById(R.id.mensagemRecebidaXML);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        String mensagemRecebida = (String) bd.get("mensagemEnviada");

        exibeMensagem.setText(mensagemRecebida);
    }
}