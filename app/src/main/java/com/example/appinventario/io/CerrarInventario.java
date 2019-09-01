package com.example.appinventario.io;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.appinventario.model.Empleado;
import com.example.appinventario.ui.MainActivity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CerrarInventario extends AsyncTask<String, Integer, Boolean> {

   // private Empleado[] listaEmpleados;
   Context context;
  // private   String IdInventario = "2";

    protected Boolean doInBackground(String... params) {

        String IdInventario = "";
        boolean resul = false;


        //--URL del webservice namespace
        final String NAMESPACE = "http://WSAppInventario.org/";
        //--IP para conectarse al localhost + el puerto y el nombre del webservice
        final String URL = "http://192.168.0.21/WSAppInventario/WebService.asmx";
        //--Nombre dle metodo a consumir
        final String METHOD_NAME = "Login";
        //--URL con el nombre del metodo
        final String SOAP_ACTION = "http://WSAppInventario.org/CerrarInventario";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //--Enviamos los parametros a consultar al webservice
        request.addProperty("IdInventario", IdInventario);
      //  request.addProperty("Pwd", pwd);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(URL);

        //    transporte.debug = true;
  /*      try {
            transporte.call(SOAP_ACTION, envelope);
            //--OBtiene el resultado
            SoapObject resSoap = (SoapObject) envelope.getResponse();

            Inventario = new Empleado[resSoap.getPropertyCount()];

            for (int i = 0; i < listaEmpleados.length; i++) {
                //--Asigna los resultados a la variable por index de posicion en el XML
                SoapObject ic = (SoapObject) resSoap.getProperty(i);

                Empleado emp = new Empleado();

                emp.IdEmpleado = Integer.parseInt(ic.getProperty(0).toString());
                emp.Emp_Nombre = ic.getProperty(1).toString();
                emp.Emp_Login = ic.getProperty(2).toString();
                emp.Emp_Password = ic.getProperty(3).toString();

                listaEmpleados[i] = emp;

                resul = true;

            } */
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

            } catch (Exception e) {

            resul = false;
            // Log.e("Resultado", e.getMessage());
        }


        return resul;


    }

    protected void onPostExecute(Boolean result) {


        if (result) {

          /*  Toast toast1 =
                    Toast.makeText(context,
                            "Inventario Cerrado", Toast.LENGTH_SHORT);

            toast1.show(); */


        } else {
         /*   Toast toast2 =
                    Toast.makeText(context,
                            "Error Cerrar Invwentario", Toast.LENGTH_SHORT);

            toast2.show(); */
        }


    }
}


