package com.mainp.paulosantos.mainp;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Paulo Santos on 06/05/2017.
 */
public class Notificacao {

    private String tipo;
    private Usuario emissor = new Usuario();
    private Usuario receptor;
    private String dataEhora;
    private String mensagem;

    public Notificacao(JSONObject jsonNotificacao, Usuario receptor) {
        try {
            this.tipo = jsonNotificacao.getString("tipo");
            this.emissor.carregarUsuario(jsonNotificacao.getJSONObject("emissor"));
            this.dataEhora = jsonNotificacao.getString("dataEhora");
            setMensagem(emissor.getNome() + " compartilhou o perfil com você.");
            this.receptor = receptor;
        } catch (Exception e) {
            Log.e("Classe Usuario", "Falha ao criar notificação", e);
        }
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getEmissor() {
        return emissor;
    }

    public void setEmissor(Usuario usuario) {
        this.emissor = usuario;
    }

    public String getDataEhora() {
        return dataEhora;
    }

    public void setDataEhora(String dataEhora) {
        this.dataEhora = dataEhora;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }
}