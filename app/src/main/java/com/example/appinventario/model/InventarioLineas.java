package com.example.appinventario.model;

import android.os.StrictMode;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import java.util.ArrayList;
import java.util.Hashtable;

public class InventarioLineas implements KvmSerializable {


    public String IdInventario;
    public String IdLinea;
    public String IdArticulo;
    public String Art_Descrip;
    public String Art_CodigoEan;
    public String Invl_Cantidad;




    public InventarioLineas (String IdInventario, String IdLinea, String IdArticulo, String Art_Descrip, String Art_CodigoEan, String Invl_Cantidad) {

        this.IdInventario = IdInventario;
        this.IdLinea = IdLinea;
        this.IdArticulo = IdArticulo;
        this.Art_Descrip = Art_Descrip;
        this.Art_CodigoEan = Art_CodigoEan;
        this.Invl_Cantidad = Invl_Cantidad;
    }

    public InventarioLineas () {

        this.IdInventario = "";
        this.IdLinea = "";
        this.IdArticulo = "";
        this.Art_Descrip = "";
        this.Art_CodigoEan = "";
        this.Invl_Cantidad = "";
    }


    public String getIdInventario() {
        return IdInventario;
    }

    public void setIdInventario(String idInventario) {
        IdInventario = idInventario;
    }

    public String getIdLinea() {
        return IdLinea;
    }

    public void setIdLinea(String idLinea) {
        IdLinea = idLinea;
    }

    public String getIdArticulo() { return IdArticulo;  }

    public void setIdArticulo(String idArticulo) {IdArticulo = idArticulo;   }

    public String getArt_Descrip() {
        return Art_Descrip;
    }

    public void setArt_Descrip(String art_Descrip) {
        Art_Descrip = art_Descrip;
    }

    public String getArt_CodigoEan() {
        return Art_CodigoEan;
    }

    public void setArt_CodigoEan(String art_CodigoEan) {
        Art_CodigoEan = art_CodigoEan;
    }

    public String getInvl_Cantidad() {
        return Invl_Cantidad;
    }

    public void setInvl_Cantidad(String invl_Cantidad) {
        Invl_Cantidad = invl_Cantidad;
    }

    @Override
    public Object getProperty(int index) {

        switch(index)
        {
            case 0:
                return getIdInventario();
            case 1:
                return getIdLinea();
            case 2:
                return getIdArticulo();
            case 3:
                return getArt_Descrip();
            case 4:
                return getArt_CodigoEan();
            case 5:
                return getInvl_Cantidad();
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 6;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch(index)
        {
            case 0:
                // NumInventario = Integer.parseInt(value.toString());
                setIdInventario(value.toString());
                break;
            case 1:
                setIdLinea(value.toString());
                break;
            case 2:
                setIdArticulo(value.toString());
                break;
            case 3:
                setArt_Descrip(value.toString());
                break;
            case 4:
                setArt_CodigoEan(value.toString());
                break;
            case 5:
                setInvl_Cantidad(value.toString());
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
                //  info.type = PropertyInfo.INTEGER_CLASS;
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "IdInventario";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "IdLinea";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "IdArticulo";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Art_Descrip";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Art_CodigoEan";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Invl_Cantidad";
                break;
            default:break;
        }
    }



    public ArrayList lstInvLineas(String IdInventario)   {



        ArrayList<InventarioLineas> myDataset = new ArrayList<>();




        //--URL del webservice namespace
        final String NAMESPACE = "http://WSAppInventario.org/";
        //--IP para conectarse al localhost + el puerto y el nombre del webservice
        final String URL = "http://192.168.0.21/WSAppInventario/WebService.asmx";
        //--Nombre dle metodo a consumir
        final String METHOD_NAME = "ListaInvLineas";
        //--URL con el nombre del metodo
        final String SOAP_ACTION = "http://WSAppInventario.org/ListaInvLineas";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //--Enviamos los parametros a consultar al webservice
          request.addProperty("IdInventario", IdInventario);
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


                myDataset.add(new InventarioLineas(
                        ic.getProperty("IdInventario").toString(),
                        ic.getProperty("IdLinea").toString(),
                        ic.getProperty("IdArticulo").toString(),
                        ic.getProperty("Art_Descrip").toString(),
                        ic.getProperty("Art_CodigoEan").toString(),
                        ic.getProperty("Invl_Cantidad").toString()));

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return myDataset;
    }

    public Boolean  NuevoInvLinea (String IdInventario, String Art_CodigoEan, String Invl_Cantidad)   {


        //Necesario para que no salga error en operaciones de larga duracion en el hilo principal
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Boolean resul = false;

        //--URL del webservice namespace
        final String NAMESPACE = "http://WSAppInventario.org/";
        //--IP para conectarse al localhost + el puerto y el nombre del webservice
        final String URL = "http://192.168.0.21/WSAppInventario/WebService.asmx";
        //--Nombre dle metodo a consumir
        final String METHOD_NAME = "NuevoInvLinea";
        //--URL con el nombre del metodo
        final String SOAP_ACTION = "http://WSAppInventario.org/NuevoInvLinea";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //--Enviamos los parametros a consultar al webservice
        request.addProperty("IdInventario", IdInventario);
        request.addProperty("Art_CodigoEan",Art_CodigoEan);
        request.addProperty("Invl_Cantidad", Invl_Cantidad);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);
        transporte.debug = true;

        try {
            transporte.call(SOAP_ACTION, envelope);
            //--OBtiene el resultado
            SoapObject resSoap = (SoapObject) envelope.getResponse();
            int nPropiedades = resSoap.getPropertyCount();

            for (int i = 0; i < nPropiedades; i++) {
                //--Asigna los resultados a la variable por index de posicion en el XML
                SoapObject ic = (SoapObject) resSoap.getProperty(i);

                // resul = ic.getProperty(0).toString();
                resul = false;

            }

        }
        catch (Exception e)
        {

            //  e.printStackTrace();
            resul = false;

        }

        return resul;

    }

    public Boolean  BorrarInvLinea(String IdInventario, String IdLinea)   {


        //Necesario para que no salga error en operaciones de larga duracion en el hilo principal
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Boolean resul = true;

        //--URL del webservice namespace
        final String NAMESPACE = "http://WSAppInventario.org/";
        //--IP para conectarse al localhost + el puerto y el nombre del webservice
        final String URL = "http://192.168.0.21/WSAppInventario/WebService.asmx";
        //--Nombre dle metodo a consumir
        final String METHOD_NAME = "BorrarInvLinea";
        //--URL con el nombre del metodo
        final String SOAP_ACTION = "http://WSAppInventario.org/BorrarInvLinea";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //--Enviamos los parametros a consultar al webservice
        request.addProperty("IdInventario", IdInventario);
        request.addProperty("IdLinea", IdLinea);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);
        transporte.debug = true;

        try {
            transporte.call(SOAP_ACTION, envelope);
            //--OBtiene el resultado
            SoapObject resSoap = (SoapObject) envelope.getResponse();
            int nPropiedades = resSoap.getPropertyCount();

            for (int i = 0; i < nPropiedades; i++) {
                //--Asigna los resultados a la variable por index de posicion en el XML
                SoapObject ic = (SoapObject) resSoap.getProperty(i);

                // resul = ic.getProperty(0).toString();
                resul = false;

            }

        }
        catch (Exception e)
        {
            //  e.printStackTrace();
            resul = false;
        }
        return resul;
    }

    public Boolean  ActualizaInvLinea(String IdInventario, String IdLinea, String Invl_Cantidad)   {


        //Necesario para que no salga error en operaciones de larga duracion en el hilo principal
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Boolean resul = true;

        //--URL del webservice namespace
        final String NAMESPACE = "http://WSAppInventario.org/";
        //--IP para conectarse al localhost + el puerto y el nombre del webservice
        final String URL = "http://192.168.0.21/WSAppInventario/WebService.asmx";
        //--Nombre dle metodo a consumir
        final String METHOD_NAME = "ActualizaInvLinea";
        //--URL con el nombre del metodo
        final String SOAP_ACTION = "http://WSAppInventario.org/ActualizaInvLinea";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //--Enviamos los parametros a consultar al webservice
        request.addProperty("IdInventario", IdInventario);
        request.addProperty("IdLinea", IdLinea);
        request.addProperty("Invl_Cantidad", Invl_Cantidad);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);
        transporte.debug = true;

        try {
            transporte.call(SOAP_ACTION, envelope);
            //--OBtiene el resultado
            SoapObject resSoap = (SoapObject) envelope.getResponse();
            int nPropiedades = resSoap.getPropertyCount();

            for (int i = 0; i < nPropiedades; i++) {
                //--Asigna los resultados a la variable por index de posicion en el XML
                SoapObject ic = (SoapObject) resSoap.getProperty(i);

                // resul = ic.getProperty(0).toString();
                resul = false;

            }

        }
        catch (Exception e)
        {
            //  e.printStackTrace();
            resul = false;
        }
        return resul;
    }


}

