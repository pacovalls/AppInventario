package com.example.appinventario.ui;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appinventario.R;
import com.example.appinventario.model.Inventario;
import com.example.appinventario.ui.adapter.InventarioAdapter;
import com.example.appinventario.ui.fragment.InventarioDialogFragment;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import java.util.ArrayList;
import java.util.Collection;

import static java.lang.System.getProperty;

public class InventarioActivity extends AppCompatActivity implements View.OnClickListener {

    private InventarioAdapter inventarioAdapter;
    private LinearLayout layoutSearchBox;


    private EditText etQuery;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        //Necesario para que no salga error en operaciones de larga duracion en el hilo principal
          if (android.os.Build.VERSION.SDK_INT > 9) {
             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
             StrictMode.setThreadPolicy(policy);
          }


            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.NumInventarioView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            Inventario inv = new Inventario();
            ArrayList<Inventario> myDataset = new ArrayList<>(inv.lstInventarios());
            inventarioAdapter = new InventarioAdapter(myDataset);
            recyclerView.setAdapter(inventarioAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((View.OnClickListener) this);





        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown()) {
                    fab.hide();
                }
            }

            //  @Override
            public void onScrollStateChange(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                }

                super.onScrollStateChanged(recyclerView, newState);

            }

        });

        layoutSearchBox = (LinearLayout) findViewById(R.id.layoutSearchBox);

        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.search) {
                            if (layoutSearchBox.getVisibility() == View.VISIBLE)
                                layoutSearchBox.setVisibility(View.GONE);
                            else
                                layoutSearchBox.setVisibility(View.VISIBLE);
                            return true;
                        }

                        return true;
                    }
                });

        Button btnQuery = (Button) findViewById(R.id.btnQuery);
        btnQuery.setOnClickListener(this);

        etQuery = (EditText) findViewById(R.id.etQueryHeader);
    }



    //  @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                showCrearInventarioDialog();
                break;
        }

    }

    private void showCrearInventarioDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        InventarioDialogFragment newFragment = new InventarioDialogFragment();


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }



}
