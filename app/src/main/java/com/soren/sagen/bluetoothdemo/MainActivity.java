package com.soren.sagen.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;


    public void turnBluetoothOff(View view){

        BA.disable();
        if (BA.isEnabled()){

            Toast.makeText(getApplicationContext(),"Bluetooth could not disabled",Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(getApplicationContext(),"Bluetooth turned off",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BA.isEnabled()){

            Toast.makeText(getApplicationContext(),"Bluetooth is on",Toast.LENGTH_LONG).show();

        }else {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);

            if (BA.isEnabled()){

                Toast.makeText(getApplicationContext(),"Bluetooth turned on",Toast.LENGTH_LONG).show();

            }
        }
    }




    public void findDiscoverableDevice(View view){
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(intent);
    }

    public void pairedDevice(View view){

        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();

        ListView listView = findViewById(R.id.paired_device_lv);

        ArrayList pairedDeviceArrayList = new ArrayList();

        for (BluetoothDevice bluetoothDevice : pairedDevices){

            pairedDeviceArrayList.add(bluetoothDevice.getName());


        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,pairedDeviceArrayList);

        listView.setAdapter(arrayAdapter);

    }

}
