package com.example.appinventario.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import static android.content.ContentValues.TAG;


public class SimpleScannerActivity extends Activity implements ZBarScannerView.ResultHandler {


    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
   /*     // Do something with the result here
        Log.v(TAG, rawResult.getContents()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);  */

        //Scan Results
        final String code = rawResult.getContents();

        final String cEan = rawResult.getContents().toString();
        //Scan format (QrCode, pdf417 etc.)
        final String format = rawResult.getBarcodeFormat().getName();
        //Concat
        final String fullMessage = "Contents = "+code+"·, Format = "+format;

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }


        //   showMessageDialog(fullMessage);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Codigo Ean");
        builder.setMessage(rawResult.getContents().toString());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        mScannerView.resumeCameraPreview(this);



        alertDialog.cancel();
    }

    public void showMessageDialog(String message) {

        // DialogFragment fragment = MessageDialogFragment.newInstance("Scan Results", message, this);
        //  fragment.show(getSuppotFragmentManager(),"");

    }


}