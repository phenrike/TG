package com.mainp.paulosantos.mainp;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Paulo Santos on 19/03/2016.
 */
public class Usuario implements Serializable {

    private int id;
    private String login;
    private String senha;
    private String nome;
    private String sexo;
    private String dtinscricao;
    private List<Username> usernames;

    public Usuario() {
        usernames = new ArrayList<Username>();
    }

    public void carregarUsuario(JSONObject jsonUsuario) {
        try {
            this.id = jsonUsuario.getInt("id");
            this.login = jsonUsuario.getString("login");
            this.senha = jsonUsuario.getString("senha");
            this.nome = jsonUsuario.getString("nome");
            this.sexo = jsonUsuario.getString("sexo");
            this.dtinscricao = jsonUsuario.getString("dtinscricao");

            JSONArray jaUsernames = new JSONArray(jsonUsuario.getString("usernames"));
            for (int i = 0; i < jaUsernames.length(); i++) {
                Username username = new Username();
                JSONObject joUsername = jaUsernames.getJSONObject(i);
                username.carregarUsername(joUsername);
                this.usernames.add(username);
            }
        } catch (Exception e) {
            Log.e("Classe Usuario", "Falha ao carregar perfil", e);
        }
    }

    public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonObject.put("id", getId());
            jsonObject.put("login", getLogin());
            jsonObject.put("senha", getSenha());
            jsonObject.put("nome", getNome());
            jsonObject.put("sexo", getSexo());
            jsonObject.put("dtinscricao", getDtinscricao());

            for (Username username : usernames) {
                jsonArray.put(username.toJSON());
            }

            jsonObject.put("usernames", jsonArray);
        } catch (JSONException e) {
            Log.e("Classe Usuario", "Falha ao tranformar usuário em JSON", e);
        }

        return jsonObject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDtinscricao() {
        return dtinscricao;
    }

    public void setDtinscricao(String dtinscricao) {
        this.dtinscricao = dtinscricao;
    }

    public List<Username> getUsernames() {
        return usernames;
    }

    public void setUsernames(ArrayList<Username> usernames) {
        this.usernames = usernames;
    }
}