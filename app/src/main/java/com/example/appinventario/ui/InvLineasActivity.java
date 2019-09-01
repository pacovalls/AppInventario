package com.example.appinventario.ui;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;


import com.example.appinventario.R;
import com.example.appinventario.model.InventarioLineas;
import com.example.appinventario.ui.adapter.InvLineasAdapter;
import com.example.appinventario.ui.fragment.InvLineasDialogFragment;


import java.util.ArrayList;

public class InvLineasActivity extends AppCompatActivity implements View.OnClickListener {

    private InvLineasAdapter invLineasAdapter;

    private String NumInventario, InventarioDescrip;

    private LinearLayout layoutSearchBox;
    private EditText etQuery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invlineas);


        //Necesario para que no salga error en operaciones de larga duracion en el hilo principal
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


      String NumInv = "";

      Bundle extras = getIntent().getExtras();
        if (extras != null) {
            NumInv = extras.getString("IdInventario");

        }



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.InvLineasView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);



/*
        ArrayList<InventarioLineas> myDataset1 = new ArrayList<>();

        myDataset1.add(new InventarioLineas("1200001", "1","AAAAA", "ITEM AAAAA", "84101300000010", "10"));
        myDataset1.add(new InventarioLineas("1200001", "2","BBBBB", "ITEM BBBBB", "84101300000022", "20"));
        myDataset1.add(new InventarioLineas("1200001", "3","CCCCC", "ITEM CCCCC", "84101300000033", "30"));
        myDataset1.add(new InventarioLineas("1200001", "4","DDDDD", "ITEM DDDDD", "84101300000044", "40"));
        myDataset1.add(new InventarioLineas("1200001", "5","AAAAA", "ITEM AAAAA", "84101300000010", "10"));
        myDataset1.add(new InventarioLineas("1200001", "6","BBBBB", "ITEM BBBBB", "84101300000022", "20"));
        myDataset1.add(new InventarioLineas("1200001", "7","CCCCC", "ITEM CCCCC", "84101300000033", "30"));
        myDataset1.add(new InventarioLineas("1200001", "8","DDDDD", "ITEM DDDDD", "84101300000044", "40"));

*/
        InventarioLineas invl = new InventarioLineas();

        ArrayList<InventarioLineas> myDataset1 = new ArrayList<>(invl.lstInvLineas(NumInv));


        invLineasAdapter = new InvLineasAdapter(myDataset1);
        recyclerView.setAdapter(invLineasAdapter);


     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Lista de articulos");
        setSupportActionBar(toolbar);  */




      ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    fab.show();

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

  /*      layoutSearchBox = (LinearLayout) findViewById(R.id.layoutSearchBox);

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

        etQuery = (EditText) findViewById(R.id.etQueryHeader);*/
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                CrearInvLineaDialog();
             break;
        }
    }

    private void CrearInvLineaDialog() {
      /*  FragmentManager fragmentManager = getSupportFragmentManager();
        InvLineasDialogFragment newFragment = new InvLineasDialogFragment();


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit(); */

     Intent intent = new Intent(InvLineasActivity.this,  ScannerActivity.class);
      startActivity(intent);

    }




}
