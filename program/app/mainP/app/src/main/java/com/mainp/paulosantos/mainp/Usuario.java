package com.mainp.paulosantos.mainp;

import android.util.Log;

import java.io.Serializable;

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
    private String face;
    private Boolean facepublic;
    private String wpp;
    private Boolean wpppublic;
    private String insta;
    private Boolean instapublic;
    private String snap;
    private Boolean snappublic;
    private String twitter;
    private Boolean twitterpublic;
    private String email;
    private Boolean emailpublic;
    private String link;
    private Boolean linkpublic;
    private String dtinscricao;

    public void carregarUsuario(JSONObject jsonUsuario) {
        try {
            this.id = jsonUsuario.getInt("id");
            this.login = jsonUsuario.getString("login");
            this.senha = jsonUsuario.getString("senha");
            this.nome = jsonUsuario.getString("nome");
            this.sexo = jsonUsuario.getString("sexo");
            this.face = jsonUsuario.getString("face");
            this.facepublic = jsonUsuario.getBoolean("facepublic");
            this.wpp = jsonUsuario.getString("wpp");
            this.wpppublic = jsonUsuario.getBoolean("wpppublic");
            this.insta = jsonUsuario.getString("insta");
            this.instapublic = jsonUsuario.getBoolean("instapublic");
            this.snap = jsonUsuario.getString("snap");
            this.snappublic = jsonUsuario.getBoolean("snappublic");
            this.twitter = jsonUsuario.getString("twitter");
            this.twitterpublic = jsonUsuario.getBoolean("twitterpublic");
            this.email = jsonUsuario.getString("email");
            this.emailpublic = jsonUsuario.getBoolean("emailpublic");
            this.link = jsonUsuario.getString("link");
            this.linkpublic = jsonUsuario.getBoolean("linkpublic");
            this.dtinscricao = jsonUsuario.getString("dtinscricao");
        } catch (Exception e) {
            Log.e("Classe Usuario", "Falha ao carregar perfil", e);
        }
    }

    public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", getId());
            jsonObject.put("login", getLogin());
            jsonObject.put("senha", getSenha());
            jsonObject.put("nome", getNome());
            jsonObject.put("sexo", getSexo());
            jsonObject.put("face", getFace());
            jsonObject.put("facepublic", getFacepublic());
            jsonObject.put("wpp", getWpp());
            jsonObject.put("wpppublic", getWpppublic());
            jsonObject.put("insta", getInsta());
            jsonObject.put("instapublic", getInstapublic());
            jsonObject.put("snap", getSnap());
            jsonObject.put("snappublic", getSnappublic());
            jsonObject.put("twitter", getTwitter());
            jsonObject.put("twitterpublic", getTwitterpublic());
            jsonObject.put("email", getEmail());
            jsonObject.put("emailpublic", getEmailpublic());
            jsonObject.put("link", getLink());
            jsonObject.put("linkpublic", getLinkpublic());
            jsonObject.put("dtinscricao", getDtinscricao());

            return jsonObject;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
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

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Boolean getFacepublic() {
        return facepublic;
    }

    public void setFacepublic(Boolean facepublic) {
        this.facepublic = facepublic;
    }

    public String getWpp() {
        return wpp;
    }

    public void setWpp(String wpp) {
        this.wpp = wpp;
    }

    public Boolean getWpppublic() {
        return wpppublic;
    }

    public void setWpppublic(Boolean wpppublic) {
        this.wpppublic = wpppublic;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public Boolean getInstapublic() {
        return instapublic;
    }

    public void setInstapublic(Boolean instapublic) {
        this.instapublic = instapublic;
    }

    public String getSnap() {
        return snap;
    }

    public void setSnap(String snap) {
        this.snap = snap;
    }

    public Boolean getSnappublic() {
        return snappublic;
    }

    public void setSnappublic(Boolean snappublic) {
        this.snappublic = snappublic;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public Boolean getTwitterpublic() {
        return twitterpublic;
    }

    public void setTwitterpublic(Boolean twitterpublic) {
        this.twitterpublic = twitterpublic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailpublic() {
        return emailpublic;
    }

    public void setEmailpublic(Boolean emailpublic) {
        this.emailpublic = emailpublic;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getLinkpublic() {
        return linkpublic;
    }

    public void setLinkpublic(Boolean linkpublic) {
        this.linkpublic = linkpublic;
    }

    public String getDtinscricao() {
        return dtinscricao;
    }

    public void setDtinscricao(String dtinscricao) {
        this.dtinscricao = dtinscricao;
    }
}