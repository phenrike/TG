package com.mainp.paulosantos.mainp;

/**
 * Created by Paulo Santos on 24/01/2017.
 */

import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class tab2Busca extends Fragment {

    Spinner redes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab2busca, container, false);

        redes = (Spinner) rootView.findViewById(R.id.spRedes);
        final EditText etBusca = (EditText) rootView.findViewById(R.id.etBusca);
        final ListView lvPerfis = (ListView) rootView.findViewById(R.id.lvPerfis);
        ImageButton btbuscar = (ImageButton) rootView.findViewById(R.id.btBuscar);

        btbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operacoes operacoes = new Operacoes();
                JSONArray jsonArray;
                JSONObject jsonObject = null;

                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    jsonArray = operacoes.buscar(redes.getSelectedItemPosition(), etBusca.getText().toString());
                    //jsonArray = operacoes.buscar(0, etBusca.getText().toString());

                    if (jsonArray != null)

                    {

                        List<Usuario> perfis = new ArrayList<Usuario>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Usuario usuario = new Usuario();

                            try {
                                jsonObject = jsonArray.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            usuario.carregarUsuario(jsonObject);

                            perfis.add(usuario);
                        }

                        ArrayAdapter<Usuario> arrayAdapter = new ArrayAdapter<Usuario>(
                                getActivity().getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                perfis);

                        lvPerfis.setAdapter(arrayAdapter);
                    }
                }
            }
        });

        ArrayAdapter aa = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.redes_sociais, android.R.layout.simple_spinner_item);
        redes.setAdapter(aa);
        return rootView;
    }
}
