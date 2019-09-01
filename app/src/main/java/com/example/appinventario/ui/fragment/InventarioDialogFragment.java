package com.example.appinventario.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.appinventario.R;
import com.example.appinventario.model.Inventario;
import com.example.appinventario.ui.InventarioActivity;
import com.example.appinventario.ui.MainActivity;
import com.example.appinventario.ui.SimpleScannerActivity;
import com.example.appinventario.ui.adapter.InventarioAdapter;

import java.util.ArrayList;
import java.util.List;

public class InventarioDialogFragment extends DialogFragment implements View.OnClickListener {

 /* Cambios para poner Spinner almacen
    private Toolbar toolbar;
    private EditText etNumInventario, etInventarioDescrip, etFechaInventario, etAlmacen, etEmpleado, etObservaciones;
    private TextInputLayout tilNumInventario, tilInventarioDescrip, tilFechaInventario, tilAlmacen, tilEmpleado, tilObservaciones; */

 //   private InventarioAdapter inventarioAdapter;

    private Spinner spinnerAlmacen;
    private EditText  etInventarioDescrip, etIdEmpleado,  etEmpleado, etObservaciones;
    private TextInputLayout tilInventarioDescrip, tilObservaciones, tilIdEmpleado, tilEmpleado;

    private Button btnGuardar;

  /*  SharedPreferences prefs = getSharedPreferences("login_data", Context.MODE_PRIVATE);
    String idEmp  = prefs.getString("IdEmpleado", "");
    String emp_Nom = prefs.getString("Emp_Nombre", "");  */


    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)  {
     //Inflate the layout to use as dialog or embeddef fragment




     View view =  inflater.inflate(R.layout.dialog_nuevo_inventario, container, false);





     Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
     toolbar.setTitle("Nuevo Inventario");
     ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

     ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
     if (actionBar != null) {
         actionBar.setDisplayHomeAsUpEnabled(true);
         actionBar.setHomeButtonEnabled(true);
         actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
     }
     setHasOptionsMenu(true);



        btnGuardar = (Button) view.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);

     etInventarioDescrip = (EditText) view.findViewById(R.id.etInventarioDescrip);
     etObservaciones = (EditText) view.findViewById(R.id.etObservaciones);
  //   etAlmacen = (EditText) view.findViewById(R.id.etAlmacen);

     etEmpleado = (EditText) view.findViewById(R.id.etEmpleado);
     etIdEmpleado = (EditText) view.findViewById(R.id.etEmpleado);


     tilInventarioDescrip = (TextInputLayout) view.findViewById(R.id.tilInventarioDescrip);
     tilObservaciones = (TextInputLayout) view.findViewById(R.id.tilObservaciones);
   //  tilAlmacen = (TextInputLayout) view.findViewById(R.id.tilAlmacen);

   //  tilIdEmpleado = idEmp.getText();
   //  tilEmpleado = emp_Nom.getText();

     // Spinner Almacen
        spinnerAlmacen = (Spinner) view.findViewById(R.id.spinnerAlmacen);
        List<String> list = new ArrayList<String>();
        list.add("Almacen Valencia");
        list.add("Almacen Murcia");
        list.add("Almacen Baleares");
        list.add("Almacen Cataluña");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAlmacen.setAdapter(spinnerArrayAdapter);



     return view;




 }


//** The system calls this only when creating the layou in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {



        Dialog dialog = super.onCreateDialog(saveInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

  /*  @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
     inflater.inflate(R.menu.save_menu, menu);

    }  */

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btnGuardar:



                Inventario inv = new Inventario();
                inv.NuevoInventario(etInventarioDescrip.getText().toString(),"2", etObservaciones.getText().toString());

                getActivity().finish();


                 ArrayList<Inventario> Dataset = new ArrayList<>(inv.lstInventarios());
                InventarioAdapter inventarioAdapter = new InventarioAdapter(Dataset);
                inventarioAdapter = new InventarioAdapter(Dataset);


                inventarioAdapter.refreshInventario(Dataset);
           //     inventarioAdapter.notifyDataSetChanged();


                 getFragmentManager().beginTransaction().remove(this).commit();


                Intent intent = new Intent(getActivity(),InventarioActivity.class);
                startActivity(intent);

                Toast.makeText(getActivity(), "Nuevo Inventario creado ", Toast.LENGTH_SHORT).show();


                break;

        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     int id = item.getItemId();

     if (id == R.id.save) {
     //    Toast.makeText(getContext(),"Peticion a webservice", Toast.LENGTH_SHORT).show();
         validarFormulario();
         return true;
     } else if (id==android.R.id.home) {
         dismiss();
         return true;
     }

     return super.onOptionsItemSelected(item);
    }

    private void validarFormulario(){



       if (!validateEditText(etInventarioDescrip, tilInventarioDescrip, R.string.error_InventarioDescrip)) {
           return;
       }

      /* if (!validateEditText(etAlmacen, tilAlmacen, R.string.error_Almacen)) {
           return;
       } */

       if (!validateEditText(etEmpleado, tilEmpleado, R.string.error_Empleado)) {
           return;
       }


       final String InventarioDescrip = etInventarioDescrip.getText().toString().trim();
      //   final String responsable = spinnerResponsible.getText().toString().trim();
     //   final String Almacen = etAlmacen.getText().toString().trim();
       final String Empleado = etEmpleado.getText().toString().trim();
      // final String activo = checkPendiente.isChecked() ? "0" : "1";
       final String observaciones = etObservaciones.getText().toString().trim();



   }


    private boolean validateEditText(EditText editText, TextInputLayout textInputLayout, int errorString) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError(getString(errorString));
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }


}