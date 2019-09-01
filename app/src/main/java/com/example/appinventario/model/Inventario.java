package com.example.appinventario.model;

import android.os.StrictMode;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Hashtable;

public class Inventario  implements KvmSerializable {

    public String IdInventario;
    public String Inv_Descrip;
    public String Inv_Fecha;
    public String IdAlmacen;
    public String Inv_Observaciones;
    public String Inv_Estado;

    public Inventario(String IdInventario, String Inv_Descrip, String Inv_Fecha, String IdAlmacen, String Inv_Observaciones, String Inv_Estado) {
        this.IdInventario= IdInventario;
        this.Inv_Descrip =  Inv_Descrip;
        this.Inv_Fecha = Inv_Fecha;
        this.IdAlmacen = IdAlmacen;
        this.Inv_Observaciones=Inv_Observaciones;
        this.Inv_Estado = Inv_Estado;

    }

    public Inventario() {
        this.IdInventario = "";
        this.Inv_Descrip= "";
        this.Inv_Fecha= "";
        this.IdAlmacen= "";
        this.Inv_Observaciones= "";
        this.Inv_Estado = "";

    }




    @Override
    public Object getProperty(int index) {

        switch(index)
        {
            case 0:
                return getIdInventario();
            case 1:
                return getInv_Descrip();
            case 2:
                return getInv_Fecha();
            case 3:
                return getIdAlmacen();
            case 4:
                return getInv_Observaciones();
            case 5:
                return getInv_Estado();
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 5;
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
                setInv_Descrip(value.toString());
                break;
            case 2:
                setInv_Fecha(value.toString());
                break;
            case 3:
                setIdAlmacen(value.toString());
                break;
            case 4:
                setInv_Observaciones(value.toString());
                break;
            case 5:
                setInv_Estado(value.toString());
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
                info.name = "Inv_Descrip";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Inv_Fecha";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "IdAlmacen";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Inv_Observaciones";
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Estado";
                break;
            default:break;
        }
    }

    public String getIdInventario() {
        return IdInventario;
    }

    public void setIdInventario(String idInventario) {
        IdInventario = idInventario;
    }

    public String getInv_Descrip() {
        return Inv_Descrip;
    }

    public void setInv_Descrip(String inv_Descrip) {
        Inv_Descrip = inv_Descrip;
    }

    public String getInv_Fecha() {
        return Inv_Fecha;
    }

    public void setInv_Fecha(String inv_Fecha) {
        Inv_Fecha = inv_Fecha;
    }

    public String getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        IdAlmacen = IdAlmacen;
    }

    public String getInv_Observaciones() {
        return Inv_Observaciones;
    }

    public void setInv_Observaciones(String inv_Observaciones) {
        Inv_Observaciones = inv_Observaciones;
    }

    public String getInv_Estado() {
        return Inv_Estado;
    }

    public void setInv_Estado(String inv_Estado) {
        Inv_Estado = inv_Estado;
    }




    public  ArrayList lstInventarios()   {

        ArrayList<Inventario> myDataset = new ArrayList<>();

        //--URL del webservice namespace
        final String NAMESPACE = "http://WSAppInventario.org/";
        //--IP para conectarse al localhost + el puerto y el nombre del webservice
        final String URL = "http://192.168.0.21/WSAppInventario/WebService.asmx";
        //--Nombre dle metodo a consumir
        final String METHOD_NAME = "ListaInventario";
        //--URL con el nombre del metodo
        final String SOAP_ACTION = "http://WSAppInventario.org/ListaInventario";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //--Enviamos los parametros a consultar al webservice
        //  request.addProperty("User", user);
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



                myDataset.add(new Inventario(
                        ic.getProperty("IdInventario").toString(),
                        ic.getProperty("Inv_Descrip").toString(),
                        ic.getProperty("Inv_Fecha").toString(),
                        ic.getProperty("IdAlmacen").toString(),
                        ic.getProperty("Inv_Observaciones").toString(),
                        ic.getProperty("Estado").toString()));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return myDataset;

    }

    public Boolean  NuevoInventario(String Inv_Descrip, String IdAlmacen, String Inv_Observaciones)   {


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
        final String METHOD_NAME = "NuevoInventario";
        //--URL con el nombre del metodo
        final String SOAP_ACTION = "http://WSAppInventario.org/NuevoInventario";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //--Enviamos los parametros a consultar al webservice
        request.addProperty("Inv_Descrip", Inv_Descrip);
        request.addProperty("IdAlmacen", IdAlmacen);
        request.addProperty("Inv_Observaciones", Inv_Observaciones);

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

    public Boolean  CerrarInventario(String IdInventario)   {


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
        final String METHOD_NAME = "CerrarInventario";
        //--URL con el nombre del metodo
        final String SOAP_ACTION = "http://WSAppInventario.org/CerrarInventario";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //--Enviamos los parametros a consultar al webservice
          request.addProperty("IdInventario", IdInventario);
        //   request.addProperty("Pwd", pwd);

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
