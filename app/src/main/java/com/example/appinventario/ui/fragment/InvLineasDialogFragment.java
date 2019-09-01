package com.example.appinventario.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.appinventario.R;
import com.example.appinventario.model.InventarioLineas;
import com.example.appinventario.ui.InvLineasActivity;
import com.example.appinventario.ui.ScannerActivity;
import com.example.appinventario.ui.SimpleScannerActivity;
import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class InvLineasDialogFragment extends DialogFragment implements View.OnClickListener {

   // public class InvLineasDialogFragment extends DialogFragment {

   // private ZXingScannerView escanerView;

    private EditText etIdInventario, etIdLinea, etIdArticulo, etArt_Descripcion, etArt_CodigoEan, etInvl_Cantidad;
    private TextInputLayout tilIdInventario, tilIdLinea, tilIdArticulo, tilArt_Descripcion, tilArt_CodigoEan, tilInvl_Cantidad;

    private ImageButton btnCaptureEan, btnCheckEan;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.dialog_nuevo_invlineas, container, false);

        //***********************
        //Inflate the layout to use as dialog or embeddef fragment


        View view =  inflater.inflate(R.layout.dialog_nuevo_invlineas, container, false);



   /*     Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Registrar nuevo articulo");
       ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);  */



        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

        setHasOptionsMenu(true);

        // Capture QR, Patrimonial Barcode & Old code
        btnCaptureEan = (ImageButton) view.findViewById(R.id.btnCaptureEan);
        btnCaptureEan.setOnClickListener(this);


        // Check if QR is available
        btnCheckEan = (ImageButton) view.findViewById(R.id.btnCheckEan);
        btnCheckEan.setOnClickListener(this);

        etIdInventario = (EditText) view.findViewById(R.id.etIdInventario);
        etIdLinea = (EditText) view.findViewById(R.id.etIdLinea);
        etIdArticulo = (EditText) view.findViewById(R.id.etIdArticulo);
        etArt_Descripcion = (EditText) view.findViewById(R.id.etArt_Descripcion);
        etArt_CodigoEan = (EditText) view.findViewById(R.id.etArt_CodigoEan);
        etInvl_Cantidad = (EditText) view.findViewById(R.id.etInvl_Cantidad);

        tilIdInventario = (TextInputLayout) view.findViewById(R.id.tilIdInventario);
        tilIdLinea = (TextInputLayout) view.findViewById(R.id.tilIdLinea);
        tilIdArticulo = (TextInputLayout) view.findViewById(R.id.tilIdArticulo);
        tilArt_Descripcion = (TextInputLayout) view.findViewById(R.id.tilArt_Descripcion);
        tilArt_CodigoEan = (TextInputLayout) view.findViewById(R.id.tilArt_CodigoEan);
        tilInvl_Cantidad = (TextInputLayout) view.findViewById(R.id.tilInvl_Cantidad);


        //   etIdInventario = (EditText) viewl.findViewById(R.id.etIdInventario);
        //    tilIdInventario = (TextInputLayout) viewl.findViewById(R.id.tilIdInventario);

        return view;


    }




    //** The system calls this only when creating the layou in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {

        Dialog dialog = super.onCreateDialog(saveInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save) {
           //  Toast.makeText(getContext(),"Peticion a webservice",Toast.LENGTH_SHORT).show();
          //  validarFormulario();
            return true;
        } else if (id==android.R.id.home) {
            dismiss();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCaptureEan:

                Intent intentQR = new Intent(getContext(), SimpleScannerActivity.class);
                startActivityForResult(intentQR, 1);
                break;

            case R.id.btnCheckEan:
                //performCheckQrRequest();
               // Intent intent1 = new Intent(getContext(),  ScannerActivity.class);
             //   startActivityForResult(intent1, 1);


                InventarioLineas nueva = new InventarioLineas();
             //   nueva.NuevoInvLinea("15", "5099752000029", "33");

             //   nueva.NuevoInvLinea("16","01455147952","80");



                Toast toast = Toast.makeText(getContext(),
                        "Añadido artículo a inventario"      , Toast.LENGTH_SHORT);
                toast.show();
                break;


        }
    }


    public void ScanerEan() {

          Intent intent1 = new Intent(getContext(),  ScannerActivity.class);
           startActivityForResult(intent1, 1);

    }







}


