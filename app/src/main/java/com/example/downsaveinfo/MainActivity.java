package com.example.downsaveinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText mensagemInserida;
    private Button downloadButton;
    private String mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensagemInserida = findViewById(R.id.editTextXML);
        downloadButton = findViewById(R.id.downloadXML);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnalizadorXml analizadorXml = new AnalizadorXml(mensagem);
                analizadorXml.process();
                mensagem = analizadorXml.getConteudo();
                mensagemInserida.setText(mensagem);
            }
        });
        MinhaAsync minhaAsync = new MinhaAsync();
        minhaAsync.execute("https://w3schools.com/xml/note.xml");
    }

    public void disparoNovaTela(View v) {

        mensagem = mensagemInserida.getText().toString();

        Intent myIntent = new Intent(this, Tela2.class);

        myIntent.putExtra("mensagemEnviada", mensagem);

        startActivity(myIntent);

    }

    private String downloadXMLFile(String theUrl) {
        try {
            URL myUrl = new URL(theUrl);
            HttpURLConnection myconnection = (HttpURLConnection) myUrl.openConnection();
            int response = myconnection.getResponseCode();
            Log.d("Download", "The response code is " + response);
            InputStream data = myconnection.getInputStream();
            InputStreamReader caracteres = new InputStreamReader(data);
            char[] inputBuffer = new char[500];
            StringBuilder tempBuffer = new StringBuilder();
            int charRead;
            while (true) {
                charRead =caracteres.read(inputBuffer);
            if (charRead <= 0) {
                break;
            }
            tempBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));

        }
        return tempBuffer.toString();
    }
    catch(IOException e)

    {
        Log.d("Download", "IO Expection durante a conexão: " + e.getMessage());
    }
    return null;
}

    private class MinhaAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            //vamos efocar o método downloadXMLFile passando a url como parâmetro
            mensagem = downloadXMLFile(params[0]);

            //Aviso para ser exibido no Logcat caso tenha havido problema no downlod
            if (mensagem == null) {
                Log.d("Download", "Erro downloading");
            }
            return mensagem;
        }
    }
}