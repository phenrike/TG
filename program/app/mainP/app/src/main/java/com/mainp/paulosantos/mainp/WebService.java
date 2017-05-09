package com.mainp.paulosantos.mainp;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by Paulo Santos on 23/10/2016.
 */
public class WebService {

    JSONObject jObect;

    public JSONObject carregarPerfil() {

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://192.168.0.17/WSmainP/Service.asmx/CarregarPerfil");
        //HttpGet httpget = new HttpGet("https://api.myjson.com/bins/42eia");

        try {
            HttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();

            if (entity != null) {

                String retSrc = EntityUtils.toString(entity);
                // parsing JSON
                this.jObect = new JSONObject(retSrc); //Convert String to JSON Object
            }
        } catch (Exception e) {
            Log.e("Classe WebService.java", "Falha ao acessar Web service", e);
        }

        return jObect;

    }
}