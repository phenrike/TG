package com.mainp.paulosantos.mainp;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Classe do Web Service
 */
public class WSmainP {

    public WSmainP() {

    }

    public String CarregarPerfil() throws IOException, XmlPullParserException {

        SoapObject soap = new SoapObject("http://nsmainp.com/", "CarregarPerfil");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);

        HttpTransportSE httptrans = new HttpTransportSE("http://192.168.0.26/WSmainP/Service.asmx");

        httptrans.call("CarregarPerfil", envelope);

        Object res = envelope.getResponse();

        return res.toString();
    }
}


