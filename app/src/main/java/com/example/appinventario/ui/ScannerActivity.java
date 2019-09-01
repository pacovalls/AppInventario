package com.example.appinventario.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.appinventario.R;
import com.example.appinventario.model.InventarioLineas;
import me.dm7.barcodescanner.zxing.ZXingScannerView;




public class ScannerActivity extends AppCompatActivity  implements View.OnClickListener{

   private ZXingScannerView escanerView;

   private Button btnscan;
   private Button btncantidad;
   private TextView etformato, etcontenido, Cantidad;

    private EditText  etCantidad ;
    private TextInputLayout  tilInvl_Cantidad;


    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);


        //Necesario para que no salga error en operaciones de larga duracion en el hilo principal
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        btnscan = (Button)findViewById(R.id.scan_button);
        btncantidad = (Button)findViewById(R.id.cant_button);

        etformato = (TextView)findViewById(R.id.scan_formato);
        etcontenido = (TextView)findViewById(R.id.scan_contenido);
        btnscan.setOnClickListener(this);
        btncantidad.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.scan_button:


                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                scanIntegrator.initiateScan();


                break;

            case R.id.cant_button:

                try {




                    InventarioLineas nueva = new InventarioLineas();
                    nueva.NuevoInvLinea("15", "5099752000029", "33");


                    Toast toast = Toast.makeText(getApplicationContext(),
                        "Añadido artículo a inventario"      , Toast.LENGTH_SHORT);
                    toast.show();


                } catch  (Exception e){

                    e.printStackTrace();;

            }


           /*   Toast toast = Toast.makeText(getApplicationContext(),
                        "15" + etcontenido.getText().toString()+ etInvl_Cantidad.getText().toString()  , Toast.LENGTH_SHORT);
                toast.show();
             */


              //  invlin.NuevoInvLinea("15", etformato.getText().toString(), etInvl_Cantidad.getText().toString());

                break;
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

            String scanContent = scanningResult.getContents();
            etcontenido.setText( scanContent);
            String scanFormat = scanningResult.getFormatName();
            etformato.setText( scanFormat);


        }else{

            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
            toast.show();
        }



    }




}
