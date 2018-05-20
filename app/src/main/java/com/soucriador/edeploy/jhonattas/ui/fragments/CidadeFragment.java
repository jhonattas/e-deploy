package com.soucriador.edeploy.jhonattas.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.soucriador.edeploy.jhonattas.R;
import com.soucriador.edeploy.jhonattas.model.Cidade;
import com.soucriador.edeploy.jhonattas.ui.adapters.CidadeDataAdapter;
import com.soucriador.edeploy.jhonattas.ui.interfaces.OnFragmentInteractionListener;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class CidadeFragment extends Fragment {

    private static final String TAG = CidadeFragment.class.getSimpleName();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnFragmentInteractionListener mListener;
    private RealmResults<Cidade> CIDADES;
    private View view;
    private String nome = "";
    private String estado = "";
    private RecyclerView recyclerView;
    private TextView empty;

    public CidadeFragment() {
    }

    public static CidadeFragment newInstance(String nome, String estado) {
        CidadeFragment fragment = new CidadeFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_COLUMN_COUNT, 1);
        args.putString("nome", nome);
        args.putString("estado", estado);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle b = getArguments();
        if (b != null) {
            nome = b.getString("nome");
            estado = b.getString("estado");
        }

        view = inflater.inflate(R.layout.fragment_cidade_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            populateCidades();
            if(CIDADES.size() == 0) {
                Toast.makeText(getContext(), getString(R.string.empty), Toast.LENGTH_LONG).show();
            }
            recyclerView.setAdapter(new CidadeDataAdapter(CIDADES, mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void populateCidades(){
        try{
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    // se cidade e estado estivem em branco, retorna todos resultados
                    if(nome.equals("") && estado.equals("")){
                        CIDADES = realm.where(Cidade.class)
                                .findAll()
                                .sort("nome");
                    } else if(!nome.equals("") && !estado.equals("")){
                        // caso nem cidade e nem estado estiverem em branco utiliza os dois como parametros
                        CIDADES = realm.where(Cidade.class)
                                .contains("nome", nome, Case.INSENSITIVE)
                                .and()
                                .contains("estado", estado, Case.INSENSITIVE)
                                .findAll()
                                .sort("nome");
                    } else if(!nome.equals("") && estado.equals("")) {
                        // se apenas estado estiver em branco, entao busca apenas por cidade
                        CIDADES = realm.where(Cidade.class)
                                .contains("nome", nome, Case.INSENSITIVE)
                                .findAll()
                                .sort("nome");

                    } else if(nome.equals("") && !estado.equals("")) {
                        // do contrario busca apenas por estado
                        CIDADES = realm.where(Cidade.class)
                                .contains("estado", estado, Case.INSENSITIVE)
                                .findAll()
                                .sort("nome");
                    }

                }
            });
        } catch (Error e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
}
