package com.soucriador.edeploy.jhonattas.ui.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.soucriador.edeploy.jhonattas.R;
import com.soucriador.edeploy.jhonattas.model.Cidade;
import com.soucriador.edeploy.jhonattas.ui.fragments.CidadeFragment;
import com.soucriador.edeploy.jhonattas.ui.interfaces.OnFragmentInteractionListener;

public class ListAllCitiesActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    String nome = "";
    String estado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_all_cities_activity);

        if (savedInstanceState == null) {
            Bundle b = getIntent().getExtras();
            if(b != null) {
                nome = b.getString("nome");
                estado = b.getString("estado");
            }
            Fragment fragment = CidadeFragment.newInstance(nome, estado);
            fragment.setArguments(b);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow();
        }
    }

    // define o que acontece quando clicar em uma das cidades da lista
    @Override
    public void onFragmentInteraction(Cidade cidade) {
        Intent i = new Intent(ListAllCitiesActivity.this, CityPointsActivity.class);
        Bundle b = new Bundle();
        b.putString("city", cidade.getNome());
        b.putString("state", cidade.getEstado());
        i.putExtras(b);
        startActivity(i);
    }
}
