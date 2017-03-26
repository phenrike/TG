package com.mainp.paulosantos.mainp;

/**
 * Created by Paulo Santos on 24/01/2017.
 */

import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class tab2Busca extends Fragment {

    public EditText etBusca;
    public Spinner redesSociais;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab2busca, container, false);

        redesSociais = (Spinner) view.findViewById(R.id.spRedes);
        ArrayAdapter aa = ArrayAdapter.createFromResource(getActivity(), R.array.redes_sociais, android.R.layout.simple_spinner_item);
        redesSociais.setAdapter(aa);

        etBusca = (EditText) view.findViewById(R.id.etBusca);
        ImageButton btBuscar = (ImageButton) view.findViewById(R.id.btBuscar);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int SDK_INT = android.os.Build.VERSION.SDK_INT;
                    if (SDK_INT > 8) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                .permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        TextView tvMensagem = (TextView) v.findViewById(R.id.tvMensagem);
                        ListView lvPerfis = (ListView) v.findViewById(R.id.lvPerfis);

                        Requisicao requisicao = new Requisicao();
                        JSONArray jsonArrayUsuario = null;
                        JSONObject jsonObjectUsuario = null;

                        if (!etBusca.getText().toString().equals("") || !etBusca.getText().toString().equals(null)) {
                            jsonArrayUsuario = requisicao.buscar(redesSociais.getSelectedItemPosition(), etBusca.getText().toString());
                        } else {
                            lvPerfis.setVisibility(View.INVISIBLE);
                            tvMensagem.setVisibility(View.VISIBLE);
                            tvMensagem.setText("Preencha o campo de busca!");
                        }

                        if (jsonArrayUsuario != null) {
                            List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

                            //Transforma JsonArray em ArrayList
                            for (int i = 0; i < jsonArrayUsuario.length(); i++) {
                                Usuario usuario = new Usuario();
                                jsonObjectUsuario = jsonArrayUsuario.getJSONObject(i);
                                usuario.carregarUsuario(jsonObjectUsuario);
                                listaDeUsuarios.add(usuario);
                            }

                            tvMensagem.setVisibility(v.INVISIBLE);

                            ListaDeUsuariosAdapter usuariosAdapter = new ListaDeUsuariosAdapter(getActivity(), listaDeUsuarios);
                            lvPerfis.setAdapter(usuariosAdapter);
                        } else {
                            lvPerfis.setVisibility(v.INVISIBLE);
                            tvMensagem.setVisibility(v.VISIBLE);
                            tvMensagem.setText("Nenhum usuário encontrado! Tente novamente.");
                        }
                    }
                } catch (Exception e) {
                    Log.e("LoginActivity", "Falha ao buscar usuários.", e);
                }
            }
        });

        return view;
    }
}
