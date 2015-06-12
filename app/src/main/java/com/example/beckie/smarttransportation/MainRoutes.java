package com.example.beckie.smarttransportation;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainRoutes extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addMainJourneys();

    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getBaseContext(),Buses.class);
            if (position==0){
                intent.putExtra("MAIN_ROUTE","Mbra-Kla");
                startActivity(intent);

            }else if (position==1){
                intent.putExtra("MAIN_ROUTE","Kla-Mbra");
                startActivity(intent);
            }

        }
    });
    }

    private void addMainJourneys() {
       // Spinner spinnerNumPeople = (Spinner)findViewById(R.id.spinner_number_of_people);
        String[] items = {"Mbra-kla","Kla-Mbra"};
        //  Cursor cursor =
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(),
                R.layout.simple_list_item_1,items);
      //  ArrayList<String> arrayList = new ArrayList<>();
        //arrayList.add("1");
        //arrayList.add("2");
        //arrayList.add("3");
        //spinnerNumPeople.setAdapter(arrayAdapter);
        getListView().setAdapter(arrayAdapter);



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_routes, menu);
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
}
