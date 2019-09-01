package com.example.appinventario.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appinventario.R;

import com.example.appinventario.model.Inventario;
import com.example.appinventario.model.InventarioLineas;
import com.example.appinventario.ui.InvLineasActivity;
import com.example.appinventario.ui.fragment.ShowInvLineasDialog;


import java.util.ArrayList;

public  class InvLineasAdapter extends RecyclerView.Adapter<InvLineasAdapter.MyViewHolder>  {



    private ArrayList<InventarioLineas> mDataset1;



    //private static String idInventario, IdLinea;

    // Provide a reference to the views for each data item
    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView CantidadNueva;

        // context
        Context context;

        // text views
        TextView tvIdInventario;
        TextView tvIdArticulo;
        TextView tvIdLinea;
        TextView tvArtDescripcion;
        TextView tvArtCodigoEan;
        TextView tvInvl_Cantidad;

        // til viewa

        EditText tilIdInventario;

        // buttons
        Button btnModificar;
        Button btnEliminar;
        // id
      //  String qr_code;

        MyViewHolder(View y) {
            super(y);
            context = y.getContext();

            tvIdInventario = (TextView) y.findViewById(R.id.tvIdInventario);
            tvIdArticulo = (TextView) y.findViewById(R.id.tvIdArticulo);
            tvIdLinea = (TextView) y.findViewById(R.id.tvIdLinea);
            tvArtDescripcion = (TextView) y.findViewById(R.id.tvArtDescripcion);
            tvArtCodigoEan = (TextView) y.findViewById(R.id.tvArtCodigoEan);
            tvInvl_Cantidad = (TextView) y.findViewById(R.id.tvInvl_Cantidad);
            btnModificar = (Button) y.findViewById(R.id.btnModificar);
            btnEliminar = (Button) y.findViewById(R.id.btnEliminar);


        }

        void setOnClickListeners() {
            btnModificar.setOnClickListener(this);
            btnEliminar.setOnClickListener(this);



        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnModificar:



                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    View promptView = layoutInflater.inflate(R.layout.dialog_input_cantidad, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setView(promptView);

                    final EditText cantidad = (EditText) promptView.findViewById(R.id.cantidad);
                    // setup a dialog window
                    alertDialogBuilder.setCancelable(false)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    tvInvl_Cantidad.setText(cantidad.getText().toString());

                                    InventarioLineas invlin = new InventarioLineas();
                                    invlin.ActualizaInvLinea(tvIdInventario.getText().toString(), tvIdLinea.getText().toString(), tvInvl_Cantidad.getText().toString());

                                }
                            })
                            .setNegativeButton("Cancelar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                          // create an alert dialog
                       AlertDialog alert = alertDialogBuilder.create();
                       alert.show();





                 break;

                case R.id.btnEliminar:


                            //Mostrar un mensaje de confirmación antes de  borrar articulo
                            AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(context);
                            alertDialog1.setMessage("¿Desea eliminar elarticulo del inventario ?");
                            alertDialog1.setTitle("Eliminar Articulo");
                            alertDialog1.setIcon(android.R.drawable.ic_dialog_alert);
                            alertDialog1.setCancelable(false);
                            alertDialog1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    InventarioLineas invlin = new InventarioLineas();
                                    invlin.BorrarInvLinea(tvIdInventario.getText().toString(), tvIdLinea.getText().toString());
                                    Toast.makeText(context, "Articulo Borrado ", Toast.LENGTH_SHORT).show();


                                }
                            });
                            alertDialog1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Toast.makeText(context, "Operación Cancelada ", Toast.LENGTH_SHORT).show();
                                }
                            });
                            alertDialog1.show();


                 break;
            }



        }



    }

        // Provide a suitable constructor (depends on the kind of dataset)
        public  InvLineasAdapter(ArrayList<InventarioLineas> myDataset1) {
            mDataset1 = myDataset1;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public InvLineasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
            // create a new view
            View y = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_invlinea, parent, false);


            return  new InvLineasAdapter.MyViewHolder(y);

         }

        public  void actualizarDatos(ArrayList<InventarioLineas> mDataset1) {
            mDataset1.clear();
            mDataset1.addAll(mDataset1);
            notifyDataSetChanged();
        }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(InvLineasAdapter.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tvIdInventario.setText(mDataset1.get(position).getIdInventario());
        holder.tvIdArticulo.setText(mDataset1.get(position).getIdArticulo());
        holder.tvIdLinea.setText(mDataset1.get(position).getIdLinea());
        holder.tvArtDescripcion.setText(mDataset1.get(position).getArt_Descrip());
        holder.tvArtCodigoEan.setText(mDataset1.get(position).getArt_CodigoEan());
        holder.tvInvl_Cantidad.setText(mDataset1.get(position).getInvl_Cantidad());

        // set events
        holder.setOnClickListeners();





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return mDataset1.size();
    }




}
