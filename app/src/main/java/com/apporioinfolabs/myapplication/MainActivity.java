package com.apporioinfolabs.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.apporioinfolabs.apporiotaxislidingbutton.ApporioTaxiSlidingButton;

public class MainActivity extends AppCompatActivity {


    ApporioTaxiSlidingButton appoio_taxi_sliding_button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appoio_taxi_sliding_button = findViewById(R.id.appoio_taxi_sliding_button);

        appoio_taxi_sliding_button.setText("Hello Apporio");
        appoio_taxi_sliding_button.setButtonType(ApporioTaxiSlidingButton.STRICT_BUTTON);
        appoio_taxi_sliding_button.setCancelable(true);
        appoio_taxi_sliding_button.setButtonColour("000000");
        appoio_taxi_sliding_button.setLoadingColour("ffbbbb");

        appoio_taxi_sliding_button.setListeners(new ApporioTaxiSlidingButton.OnTaxiSlidngListener() {
            @Override
            public void onAction() {
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                appoio_taxi_sliding_button.startLoading("Doing this action now");
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "on Cancel clicked", Toast.LENGTH_SHORT).show();
                appoio_taxi_sliding_button.startLoading("Cancelling now");
            }
        });


        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appoio_taxi_sliding_button.stopLoading();
                appoio_taxi_sliding_button.setText("Second text goes here");
            }
        });


    }
}
