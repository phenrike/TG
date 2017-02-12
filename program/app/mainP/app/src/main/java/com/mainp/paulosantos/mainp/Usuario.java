package com.mainp.paulosantos.mainp;

import android.util.Log;
import java.io.Serializable;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Paulo Santos on 19/03/2016.
 */
public class Usuario implements Serializable{
    private int ID;
    private String LOGIN;
    private String SENHA;
    private String NOME;
    private String SEXO;
    private String FACE;
    private Boolean FACEpublic;
    private String WPP;
    private Boolean WPPpublic;
    private String INSTA;
    private Boolean INSTApublic;
    private String SNAP;
    private Boolean SNAPpublic;
    private String TWITTER;
    private Boolean TWITTERpublic;
    private String EMAIL;
    private Boolean EMAILpublic;
    private String LINK;
    private Boolean LINKpublic;
    private String DTINSCRICAO;

    public void carregarUsuario(JSONObject jsonUsuario){
        try {
            this.ID = jsonUsuario.getInt("id");
            this.LOGIN = jsonUsuario.getString("login");
            this.SENHA = jsonUsuario.getString("senha");
            this.NOME = jsonUsuario.getString("nome");
            this.SEXO = jsonUsuario.getString("sexo");
            this.FACE = jsonUsuario.getString("face");
            this.FACEpublic = jsonUsuario.getBoolean("facepublic");
            this.WPP = jsonUsuario.getString("wpp");
            this.WPPpublic = jsonUsuario.getBoolean("wpppublic");
            this.INSTA = jsonUsuario.getString("insta");
            this.INSTApublic = jsonUsuario.getBoolean("instapublic");
            this.SNAP = jsonUsuario.getString("snap");
            this.SNAPpublic = jsonUsuario.getBoolean("snappublic");
            this.TWITTER = jsonUsuario.getString("twitter");
            this.TWITTERpublic = jsonUsuario.getBoolean("twitterpublic");
            this.EMAIL = jsonUsuario.getString("email");
            this.EMAILpublic = jsonUsuario.getBoolean("emailpublic");
            this.LINK = jsonUsuario.getString("link");
            this.LINKpublic = jsonUsuario.getBoolean("linkpublic");
            this.DTINSCRICAO = jsonUsuario.getString("dtinscricao");
            setTWITTER(jsonUsuario.getString("twitter"));
        }catch (Exception e) {
            Log.e("Classe Usuario", "Falha ao carregar perfil", e);
        }

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public String getSENHA() {
        return SENHA;
    }

    public void setSENHA(String SENHA) {
        this.SENHA = SENHA;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getSEXO() {
        return SEXO;
    }

    public void setSEXO(String SEXO) {
        this.SEXO = SEXO;
    }

    public String getFACE() {
        return FACE;
    }

    public void setFACE(String FACE) {
        this.FACE = FACE;
    }

    public Boolean getFACEpublic() {
        return FACEpublic;
    }

    public void setFACEpublic(Boolean FACEpublic) {
        this.FACEpublic = FACEpublic;
    }

    public String getWPP() {
        return WPP;
    }

    public void setWPP(String WPP) {
        this.WPP = WPP;
    }

    public Boolean getWPPpublic() {
        return WPPpublic;
    }

    public void setWPPpublic(Boolean WPPpublic) {
        this.WPPpublic = WPPpublic;
    }

    public String getINSTA() {
        return INSTA;
    }

    public void setINSTA(String INSTA) {
        this.INSTA = INSTA;
    }

    public Boolean getINSTApublic() {
        return INSTApublic;
    }

    public void setINSTApublic(Boolean INSTApublic) {
        this.INSTApublic = INSTApublic;
    }

    public String getSNAP() {
        return SNAP;
    }

    public void setSNAP(String SNAP) {
        this.SNAP = SNAP;
    }

    public Boolean getSNAPpublic() {
        return SNAPpublic;
    }

    public void setSNAPpublic(Boolean SNAPpublic) {
        this.SNAPpublic = SNAPpublic;
    }

    public String getTWITTER() {
        return TWITTER;
    }

    public void setTWITTER(String TWITTER) {
        this.TWITTER = TWITTER;
    }

    public Boolean getTWITTERpublic() {
        return TWITTERpublic;
    }

    public void setTWITTERpublic(Boolean TWITTERpublic) {
        this.TWITTERpublic = TWITTERpublic;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Boolean getEMAILpublic() {
        return EMAILpublic;
    }

    public void setEMAILpublic(Boolean EMAILpublic) {
        this.EMAILpublic = EMAILpublic;
    }

    public String getLINK() {
        return LINK;
    }

    public void setLINK(String LINK) {
        this.LINK = LINK;
    }

    public Boolean getLINKpublic() {
        return LINKpublic;
    }

    public void setLINKpublic(Boolean LINKpublic) {
        this.LINKpublic = LINKpublic;
    }

    public String getDTINSCRICAO() {
        return DTINSCRICAO;
    }

    public void setDTINSCRICAO(String DTINSCRICAO) {
        this.DTINSCRICAO = DTINSCRICAO;
    }
}
