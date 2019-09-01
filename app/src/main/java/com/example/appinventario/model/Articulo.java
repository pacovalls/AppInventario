package com.example.appinventario.model;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Hashtable;

public class Articulo implements KvmSerializable {

    public String IdArticulo;
    public String Art_Descrip;
    public String Art_CodigoEan;

    public Articulo (String idArticulo, String art_Descrip, String art_CodigoEan) {

        this.IdArticulo = idArticulo ;
        this.Art_Descrip = art_Descrip;
        this.Art_CodigoEan =  art_CodigoEan;

    }

    public Articulo() {

       this.IdArticulo = "";
       this.Art_Descrip= "";
       this.Art_CodigoEan = "";

    }

    @Override
    public Object getProperty(int index) {
        switch(index)
        {
            case 0:
                return IdArticulo;
            case 1:
                return Art_Descrip;
            case 2:
                return Art_CodigoEan;
         }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void setProperty(int index, Object value) {

        switch(index)
        {
            case 0:
                IdArticulo = value.toString();
                break;
            case 1:
                Art_Descrip = value.toString();
                break;
            case 2:
                Art_CodigoEan = value.toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {


        switch(index)
        {
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "IdArticulo";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Art_Descrip";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Art_CodigoEan";
                break;
            default:break;
        }
    }

    public ArrayList  LeerCodigoEan(String art_CodigoEan)   {

        ArrayList<Articulo> myDataset = new ArrayList<>();

        //--URL del webservice namespace
        final String NAMESPACE = "http://WSAppInventario.org/";
        //--IP para conectarse al localhost + el puerto y el nombre del webservice
        final String URL = "http://192.168.0.21/WSAppInventario/WebService.asmx";
        //--Nombre dle metodo a consumir
        final String METHOD_NAME = "LeerCodigoEan";
        //--URL con el nombre del metodo
        final String SOAP_ACTION = "http://WSAppInventario.org/LeerCodigoEan";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //--Enviamos los parametros a consultar al webservice
             request.addProperty("Art_CodigoEan", art_CodigoEan);
        //   request.addProperty("Pwd", pwd);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);
        //  transporte.debug = true;

        try {
            transporte.call(SOAP_ACTION, envelope);
            //--OBtiene el resultado
            SoapObject resSoap = (SoapObject) envelope.getResponse();
            int nPropiedades = resSoap.getPropertyCount();

            for (int i = 0; i < nPropiedades; i++) {
                //--Asigna los resultados a la variable por index de posicion en el XML
                SoapObject ic = (SoapObject) resSoap.getProperty(i);



                myDataset.add(new Articulo(
                        ic.getProperty("IdArticulo").toString(),
                        ic.getProperty("Art_Descrip").toString(),
                        ic.getProperty("Art_CodigoEan").toString()));

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return myDataset;

    }

}
