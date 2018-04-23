package com.example.smart_cart.Activities;

import android.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smart_cart.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;




public class MainActivity extends AppCompatActivity {
    private static final int ACTION__REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startBtn = findViewById(R.id.startBtn);
        final Activity activity = this;
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator= new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
                        .setPrompt("Please on the QR code")  //note
                        .setCameraId(0)                          //use back camera
                        .setBeepEnabled(true)                   //sound used
                        .setBarcodeImageEnabled(true)
                        .initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show();
            }
            else{
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ShppingActivity.class);
                intent.putExtra("str", result.getContents());
                startActivity(intent);
            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
