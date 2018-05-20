package com.soucriador.edeploy.jhonattas.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.soucriador.edeploy.jhonattas.R;

public class SearchActivity extends AppCompatActivity {

    EditText edNome;
    EditText edEstado;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findComponents();
    }

    void findComponents() {
        // encontra os componentes dentro do layout
        edNome      = findViewById(R.id.edNome);
        edEstado    = findViewById(R.id.edEstado);
        btSubmit    = findViewById(R.id.btPesquisa);

        // adiciona a acao do botao de pesquisa
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("nome",     edNome.getText().toString());
                b.putString("estado",   edEstado.getText().toString());

                Intent i = new Intent(SearchActivity.this, ListAllCitiesActivity.class);
                i.putExtras(b);
                startActivity(i);
            }
        });

        edNome.requestFocus();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
