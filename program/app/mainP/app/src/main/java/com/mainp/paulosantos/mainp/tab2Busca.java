package com.mainp.paulosantos.mainp;

/**
 * Created by Paulo Santos on 24/01/2017.
 */

import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class tab2Busca extends Fragment {

    public EditText etBusca;
    public Spinner redesSociais;
    public TextView tvMensagem;
    public ListView lvPerfis;
    public List<Usuario> listaDeUsuarios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.tab2busca, container, false);
        final Usuario usuario = (Usuario) getActivity().getIntent().getSerializableExtra("usuario");

        redesSociais = (Spinner) view.findViewById(R.id.spRedes);
        ArrayAdapter aa = ArrayAdapter.createFromResource(getActivity(), R.array.redes_sociais, android.R.layout.simple_spinner_item);
        redesSociais.setAdapter(aa);

        etBusca = (EditText) view.findViewById(R.id.etBusca);
        ImageButton btBuscar = (ImageButton) view.findViewById(R.id.btBuscar);
        tvMensagem = (TextView) view.findViewById(R.id.tvMensagem);
        lvPerfis = (ListView) view.findViewById(R.id.lvPerfis);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar(v);
            }
        });

        lvPerfis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int pos, long id) {

                if (listaDeUsuarios != null) {
                    //Cria tela de perfil
                    Intent perfilOutroUsuActivity = new Intent(getActivity(), PerfilOutroUsuActivity.class);

                    //Abre a tela de perfil passando o usuário
                    perfilOutroUsuActivity.putExtra("receptor", (Serializable) listaDeUsuarios.get(pos));
                    perfilOutroUsuActivity.putExtra("emissor", (Serializable) usuario);
                    getActivity().startActivity(perfilOutroUsuActivity);
                }
            }
        });

        return view;
    }

    public void buscar(View v) {
        try {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Requisicao requisicao = new Requisicao();
                JSONArray jsonArrayUsuario = null;

                String conteudoDaBusca = etBusca.getText().toString();

                if ((!conteudoDaBusca.equals("")) && (!conteudoDaBusca.equals(null))) {
                    jsonArrayUsuario = requisicao.buscar(redesSociais.getSelectedItemPosition() + 1, substituirCaracteresEspeciais(conteudoDaBusca));
                } else {
                    lvPerfis.setVisibility(v.INVISIBLE);
                    tvMensagem.setVisibility(v.VISIBLE);
                    tvMensagem.setText("Preencha o campo de busca!");
                }

                if (jsonArrayUsuario != null) {
                    listaDeUsuarios = new ArrayList<Usuario>();

                    //Transforma JsonArray em ArrayList
                    for (int i = 0; i < jsonArrayUsuario.length(); i++) {
                        Usuario usuario = new Usuario();
                        JSONObject jsonObjectUsuario = jsonArrayUsuario.getJSONObject(i);
                        usuario.carregarUsuario(jsonObjectUsuario);
                        listaDeUsuarios.add(usuario);
                    }

                    tvMensagem.setVisibility(v.INVISIBLE);
                    lvPerfis.setVisibility(v.VISIBLE);

                    ListaDeUsuariosAdapter usuariosAdapter = new ListaDeUsuariosAdapter(getActivity(), listaDeUsuarios);
                    lvPerfis.setAdapter(usuariosAdapter);
                } else {
                    lvPerfis.setVisibility(v.INVISIBLE);
                    tvMensagem.setVisibility(v.VISIBLE);
                    tvMensagem.setText("Nenhum resultado encontrado");
                }
            }
        } catch (Exception e) {
            Log.e("tab2Busca", "Falha ao buscar usuários.", e);
        }
    }

    public String substituirCaracteresEspeciais(String conteudoDaBusca) {
        String[] caracteresEspeciais = {"!", "@", "#", "$", "%", "¨", "&", "*", "(", ")", "'", "-", "+"};
        String[] espacos = {"       ", "      ", "     ", "    ", "   ", "  ", " "};

        for (String ce : caracteresEspeciais)
            conteudoDaBusca = conteudoDaBusca.replace(ce, "");

        for (String e : espacos)
            conteudoDaBusca = conteudoDaBusca.replace(e, "_");

        return conteudoDaBusca;
    }
}