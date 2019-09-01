package com.example.appinventario.ui.adapter;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appinventario.R;
import com.example.appinventario.model.Inventario;
import com.example.appinventario.ui.InvLineasActivity;
import com.example.appinventario.ui.InventarioActivity;


import java.util.ArrayList;

public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.MyViewHolder> {

       private ArrayList<Inventario> mDataset;





        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // each data item is just a string in this case
            // contex
            Context context;
            // text views
            TextView IdInventario;
            TextView Inv_Descrip;
            TextView Inv_Fecha;
            TextView Inv_Estado;
            TextView Inv_Observaciones;
            // buttons
            Button btnVerInventario;
            Button btnCerrarInventario;
            // params
           // String Inv_id, InvDescrip;



            MyViewHolder(View v) {
                super(v);
                context = v.getContext();


                IdInventario = (TextView) v.findViewById(R.id.IdInventario);
                Inv_Descrip = (TextView) v.findViewById(R.id.Inv_Descrip);
                Inv_Fecha = (TextView) v.findViewById(R.id.Inv_Fecha);
                Inv_Estado = (TextView) v.findViewById(R.id.Inv_Estado);
                Inv_Observaciones = (TextView) v.findViewById(R.id.Inv_Observaciones);

                btnVerInventario = (Button) v.findViewById(R.id.btnVerInventario);
                btnCerrarInventario = (Button) v.findViewById(R.id.btnCerrarInventario);



            }

            void setOnClickListeners() {
                btnVerInventario.setOnClickListener(this);
                btnCerrarInventario.setOnClickListener(this);
            }

            //@Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.btnVerInventario:
                      // Intent intent = new Intent(context, LineasInvActivity.class);
                       Intent intent = new Intent(context, InvLineasActivity.class);
                       intent.putExtra ("IdInventario", IdInventario.getText());
                       //intent.putExtra("Inv_id", Inv_id);
                      // intent.putExtra("InventarioDescrip", InventarioDescrip.getText());
                        context.startActivity(intent);
                        break;
                    case R.id.btnCerrarInventario:


                          if (Inv_Estado.getText().toString().equals("Inventario En Curso") ) {



                             Inventario inv = new Inventario();
                             inv.CerrarInventario(IdInventario.getText().toString());

                              Toast.makeText(context, "Inventario Cerrado "+ IdInventario.getText().toString(), Toast.LENGTH_SHORT).show();

                             Inv_Estado.setText("Inventario Cerrado");


                          }


                          else {


                             Toast.makeText(context, "El inventario ya esta cerrado ", Toast.LENGTH_SHORT).show();
                          }

                        break;

                }
            }

        }

        // Provide a suitable constructor (depends on the kind of dataset)
         public  InventarioAdapter(ArrayList<Inventario> myDataset) {
            mDataset = myDataset;
        }

       // public  void refreshInventario(ArrayList<Inventario> mDataset) {
       public  void refreshInventario(ArrayList<Inventario> newmDataset) {
            this.mDataset.clear();
            mDataset.addAll(newmDataset);
            notifyDataSetChanged();




        }

        // Create new views (invoked by the layout manager)
        @Override
        public InventarioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {


            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_inventario, parent, false);

            return  new MyViewHolder(v);
        }



        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.IdInventario.setText(mDataset.get(position).getIdInventario());
            holder.Inv_Descrip.setText(mDataset.get(position).getInv_Descrip());
            holder.Inv_Fecha.setText(mDataset.get(position).getInv_Fecha());
            holder.Inv_Estado.setText(mDataset.get(position).getInv_Estado());
            holder.Inv_Observaciones.setText(mDataset.get(position).getInv_Observaciones());



            // set events
            holder.setOnClickListeners();

            // params needed to show the details
           // holder.hoja_id = currentHeader.getId();
          //  holder.responsable = currentHeader.getResponsable().trim();

        }




        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }




    }



