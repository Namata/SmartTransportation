package com.example.beckie.smarttransportation;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class BookBus extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bus);
        views();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_bus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void  views(){
        //name edititext
        final EditText nameET = (EditText)findViewById(R.id.textView_Name);
        final EditText contactET = (EditText)findViewById(R.id.textview_contact);

        final Spinner spinnerNumPeople = (Spinner)findViewById(R.id.spinner_number_of_people);
        String[] items = {"1","2","3","4"};
        //spinnerNumPeople.setPrompt("Number of people");
       // spinnerNumPeople.
      //  Cursor cursor =
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(),
                R.layout.simple_list_item_1,items);
        ArrayList<String> arrayList = new ArrayList<>();
       // arrayList.add("1");
       // arrayList.add("2");
       // arrayList.add("3");
        spinnerNumPeople.setAdapter(arrayAdapter);

        final Spinner spinnerLuggage = (Spinner)findViewById(R.id.spinner_laggage);
        String[] itemsLuggage = {"luggage","light Luggage","Heavy luggage"};
        ArrayAdapter<String> arrayAdapterLuggage = new ArrayAdapter<String>(getBaseContext(),
                R.layout.simple_list_item_1,itemsLuggage);
        spinnerLuggage.setAdapter(arrayAdapterLuggage);

        //paying button
        Button buttonPaying = (Button)findViewById(R.id.button2_pay);
        buttonPaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:*185*1#"));
                startActivity(intent);
            }
        });
        /*boooking button
        Format of data as it is send
        name,contact,numOfPeople,natureOfLuggage,paymentInfo,date,hasPayed,passengerBooking
         */
        Button buttonBooking = (Button)findViewById(R.id.button_book);
        buttonBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("0757150000", null, nameET.getText().toString() + "," + contactET.getText().toString()+ "," +
                        spinnerNumPeople.getSelectedItem().toString()+ "," + spinnerLuggage.getSelectedItem().toString() + "," +
                        "paymentInfo" + ",", null, null);
                Toast.makeText(getBaseContext(),"Booked",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
