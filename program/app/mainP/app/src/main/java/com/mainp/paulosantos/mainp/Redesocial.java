package com.mainp.paulosantos.mainp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Paulo Santos on 03/06/2017.
 */
public class Redesocial  implements Serializable {

    public int id;
    public String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void carregarRedeSocial(JSONObject joRedeSocial) {
        try {
            this.id = joRedeSocial.getInt("id");
            this.nome = joRedeSocial.getString("nome");
        } catch (Exception e) {
            Log.e("Classe Rede Social", "Falha ao carregar rede social", e);
        }
    }

    public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("id", getId());
            jsonObject.put("nome", getNome());
        } catch (JSONException e) {
            Log.e("Classe Redesocial", "Falha ao tranformar Rede Social em JSON", e);
        }

        return jsonObject;
    }
}
