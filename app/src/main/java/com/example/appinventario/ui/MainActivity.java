package com.example.appinventario.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appinventario.R;
import com.example.appinventario.model.Empleado;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etIdEmpleado, etUsername, etPassword;
    private TextView txtResultado;
    private ListView lstEmpleados;

    //--Variables tipo string
    String user, pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Necesario para que no salga error en operaciones de larga duracion en el hilo principal
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        etIdEmpleado = (EditText) findViewById(R.id.etIdEmpleado);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        txtResultado = (TextView) findViewById(R.id.txtResultado);


        Button btnInicioSesion = (Button) findViewById(R.id.btnInicioSesion);
        btnInicioSesion.setOnClickListener(this);

        Button btnContacto = (Button) findViewById(R.id.btnContacto);
        btnContacto.setOnClickListener(this);

        lstEmpleados = (ListView) findViewById(R.id.lstEmpleados);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInicioSesion:

                user = etUsername.getText().toString();
                pwd = etPassword.getText().toString();


        /*    TareaWSLogin logins = new TareaWSLogin();
            logins.execute(); */

                WSLogin logins = new WSLogin();
                logins.execute();


                break;
            case R.id.btnContacto:

                Toast.makeText(this, "Paco Valls: pacovalls@hotmail.es", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // private String jsonResult;

    private class WSLogin extends AsyncTask<String, Integer, Boolean> {

        private Empleado[] listaEmpleados;

        protected Boolean doInBackground(String... params) {

            boolean resul = false;

            //--URL del webservice namespace
            final String NAMESPACE = "http://WSAppInventario.org/";
            //--IP para conectarse al localhost + el puerto y el nombre del webservice
            final String URL = "http://192.168.0.21/WSAppInventario/WebService.asmx";
            //--Nombre dle metodo a consumir
            final String METHOD_NAME = "Login";
            //--URL con el nombre del metodo
            final String SOAP_ACTION = "http://WSAppInventario.org/Login";
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            //--Enviamos los parametros a consultar al webservice
            request.addProperty("User", user);
            request.addProperty("Pwd", pwd);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);

            //    transporte.debug = true;
            try {
                transporte.call(SOAP_ACTION, envelope);
                //--OBtiene el resultado
                SoapObject resSoap = (SoapObject) envelope.getResponse();

                listaEmpleados = new Empleado[resSoap.getPropertyCount()];

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

                }
            } catch (Exception e) {

                resul = false;
                // Log.e("Resultado", e.getMessage());
            }


            return resul;


        }

        protected void onPostExecute(Boolean result) {


            if (result) {
                //Rellenamos la lista con los nombres de los clientes
                final String[] datos = new String[listaEmpleados.length];


                for (int i = 0; i < listaEmpleados.length; i++)
                    datos[i] = listaEmpleados[i].Emp_Nombre;

                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, datos);

                lstEmpleados.setAdapter(adaptador);

                txtResultado.setText("Bienvenido ");





                ListInventario();
            } else {
                //txtResultado.setText("Login no correcto");

                Toast toast2 =
                        Toast.makeText(getApplicationContext(),
                                "LOGIN INCORRECTO; Revisar usuario y password", Toast.LENGTH_SHORT);

                toast2.show();
            }


        }
    }

        public void ListInventario() {

        /*    SharedPreferences prefs = getSharedPreferences("login_data", Context.MODE_PRIVATE);
            SharedPreferences.Editor  editor = prefs.edit();
            editor.putString("IdEmpleado" , etIdEmpleado.getText().toString());
            editor.putString("Emp_Nombre" , etUsername.getText().toString());  */

            Intent intent = new Intent(MainActivity.this, InventarioActivity.class);
            startActivity(intent);


        }
    }



