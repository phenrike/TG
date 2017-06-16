package com.mainp.paulosantos.mainp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Paulo Santos on 03/06/2017.
 */
public class Username implements Serializable {

    public int Id;

    public Redesocial Idredesocial;

    public String Nomeusuario;

    public Boolean Privado;

    public void carregarUsername(JSONObject joUsername) {
        try {
            Redesocial redesocial = new Redesocial();
            redesocial.carregarRedeSocial(joUsername.getJSONObject("idredesocial"));
            this.Idredesocial = redesocial;
            this.Id = joUsername.getInt("id");
            this.Nomeusuario = joUsername.getString("nomeusuario");
            this.Privado = joUsername.getBoolean("privado");
        } catch (Exception e) {
            Log.e("Classe Username", "Falha ao carregar username", e);
        }
    }

    public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", getId());
            jsonObject.put("idredesocial", getIdredesocial().toJSON());
            jsonObject.put("nomeusuario", getNomeusuario());
            jsonObject.put("privado", getPrivado());
        } catch (JSONException e) {
            Log.e("Classe Username", "Falha ao tranformar username em JSON", e);
        }

        return jsonObject;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Redesocial getIdredesocial() {
        return Idredesocial;
    }

    public void setIdredesocial(Redesocial idredesocial) {
        Idredesocial = idredesocial;
    }

    public String getNomeusuario() {
        return Nomeusuario;
    }

    public void setNomeusuario(String nomeusuario) {
        Nomeusuario = nomeusuario;
    }

    public Boolean getPrivado() {
        return Privado;
    }

    public void setPrivado(Boolean privado) {
        Privado = privado;
    }
}