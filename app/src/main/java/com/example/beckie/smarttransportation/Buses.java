package com.example.beckie.smarttransportation;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;


public class Buses extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(getSpecificTitle());
        setContentView(R.layout.activity_buses);

        insertJourneysIntoTheDatabase();
        displayJourneys();

    }


    /**
     * store journeys into the database
     */
    void insertJourneysIntoTheDatabase(){
        MainDataBase mainDataBase = new MainDataBase(getBaseContext());
        mainDataBase.open();

        Cursor cursor = mainDataBase.getAllJourneys();
        if(cursor.getCount()>=2){
            Log.i("transport","Already gotten journeys");
            return;
        }
        mainDataBase.insertJourney("Mbarara","Mbra-Kla via Masaka","-","-","8:00 AM",0);
        mainDataBase.insertJourney("Mbarara","Mbra-Kla via Masaka","-","-","11:00 AM",0);
        mainDataBase.insertJourney("Mbarara","Mbra-Kla via Masaka","-","-","2:00 PM",0);
        mainDataBase.insertJourney("Kampala","Kla-Mbra via Masaka","-","-","4:00 PM",0);
        mainDataBase.insertJourney("Kampala","Kla-Mbra via Masaka","-","-","6:00 PM",0);
        mainDataBase.insertJourney("Kampala","Kla-Mbra via Masaka","-","-","8:00 PM",0);
        mainDataBase.close();
    }

    String mainRoute = "";
    List<RowItem> rowItems;//
    void displayJourneys() {

        //determine what to display
        Intent intent = this.getIntent();
       mainRoute = intent.getStringExtra("MAIN_ROUTE");

        MainDataBase mainDataBase = new MainDataBase(getBaseContext());
        mainDataBase.open();
        Cursor cursor = mainDataBase.getAllJourneys();


        rowItems = new ArrayList<RowItem>();
        //dummyFill( c );

        int i = 0;
        if (cursor.moveToLast()) {
            do {
                //.....getMessageID(c);
                //DisplayContact(c);
                //....++totalMessagesGot;
                if (cursor.isFirst()) {
                    //beginningMessageIndex = c.getInt(MessagesDBManager.ID_CURSOR_INDEX);
                }
                Log.d("transport","cursor: "+cursor.getString(2)+"mainRoute: "+mainRoute);
                if (!cursor.getString(2).contains(mainRoute)){
                    continue;

                }
                RowItem item = new RowItem(
                        cursor.getString(2),//route
                        cursor.getString(5),//time of de
                        cursor.getString(5),
                        cursor.getString(5),
                        cursor.getString(5),
                        cursor.getString(5),
                        cursor.getString(5),
                        cursor.getInt(5),
                        cursor.getString(5),
                        cursor.getString(5)
                );

                //rowItems.add((totalItems-1)-i, item);
                rowItems.add(item);
                ++i;
            } while (cursor.moveToPrevious());


            //show these items on the list view
            CustomListViewAdapter adapter = new CustomListViewAdapter(getBaseContext(),
                    R.layout.list_item, rowItems);
            //getListView() = this.getListView();// (ListView) findViewById(R.id.list);

            getListView().setAdapter(adapter);
            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(getBaseContext(), BookBus.class));
                }
            });

        }

    }

        void getSpecificDetails() {
            Intent thisIntent = this.getIntent();
            thisIntent.getStringExtra("BUS_COMPANY");

    }

    String getSpecificTitle(){
        Intent thisIntent = this.getIntent();
        return thisIntent.getStringExtra("BUS_COMPANY");
    }


    //}




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_buses, menu);
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
