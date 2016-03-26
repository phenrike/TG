package com.mainp.paulosantos.mainp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class UserPerfilAcvt extends AppCompatActivity{

    private static String SOAP_ACTION="http://192.168.0.26/WSmainP/Service.asmx/teste";
    private static String NAMESPACE="http://nsmainp.com/";
    private static String METHODNAME="teste";
    private static String URL="http://192.168.0.26/WSmainP/Service.asmx?WSDL";

    ProgressDialog dialogo;
    String str="";
    String NOME="";
    TextView text = (TextView) findViewById(R.id.tvName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_perfil_acvt);

        TextView text = (TextView) findViewById(R.id.tvName);
        new asynMainp().execute();
    }

    class asynMainp extends AsyncTask<String,String,String>{

        protected void OnPreExecute() {

            dialogo = new ProgressDialog(UserPerfilAcvt.this);
            dialogo.setMessage("Aguarde, o mainP está carregando (:");
            dialogo.setIndeterminate(false);
            dialogo.setCancelable(false);
            dialogo.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                if (InvocaWS()){
                    return str="ok";
                }else {
                    return str="erro";
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        protected void OnPostExecute(String s) {
            dialogo.dismiss();
            if (s.equals("ok")){
                carregarNome();
            }else {
                Log.e("Script","Falhou my friend" + s.toString());
            }
        }
    }

    public boolean InvocaWS() throws XmlPullParserException, IOException {
        boolean re = true;
        try {
            SoapObject resposta = new SoapObject(NAMESPACE,METHODNAME);
            SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            sobre.dotNet = true;
            sobre.setOutputSoapObject(resposta);
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call(SOAP_ACTION,sobre);

            String resultado = sobre.getResponse().toString();
            JSONObject jsonO = new JSONObject(resultado);
            NOME = jsonO.getString("NOME");

        } catch (IOException e) {
            e.printStackTrace();
            re = false;
        }catch (XmlPullParserException e){
            e.printStackTrace();
            re = false;
        }catch (JSONException e){
            e.printStackTrace();
        }

        return re;
    }

    public void carregarNome(){
        text.setText(NOME);
    }

    }
