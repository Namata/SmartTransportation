package com.example.beckie.smarttransportation;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Button buttonGlobal;
    Button buttonSwift;
    ImageView imageViewMain ,imageVAnim,imageView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageVAnim=(ImageView) findViewById(R.id.imageView_bus_type);
        imageView3=(ImageView) findViewById(R.id.imageView2);
        Animation fade = AnimationUtils.loadAnimation(this,R.anim.animation);
        imageVAnim.startAnimation(fade);
        Animation zoom = AnimationUtils.loadAnimation(this,R.anim.zoom);
        imageView3.startAnimation(zoom);

        views();
    }

     void views(){
         //instantiate d buttons
         buttonGlobal = (Button)findViewById(R.id.button_select_global);
         buttonSwift = (Button)findViewById(R.id.button_select_swift);
         imageViewMain = (ImageView)findViewById(R.id.imageView_bus_type);

         //set onclick listeners
         buttonGlobal.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                // Toast.makeText(getBaseContext(),"Global",Toast.LENGTH_SHORT).show();
                 //Intent intent = new Intent(getBaseContext(),Buses.class);
                 //intent.putExtra("BUS_COMPANY","GLOBAL");
                 imageViewMain.setImageResource(R.drawable.global_bus);
                 startActivity(new Intent(getBaseContext(),MainRoutes.class));


             }
         });
         buttonSwift.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                // Toast.makeText(getBaseContext(),"Swift",Toast.LENGTH_SHORT).show();
                 //Intent intent = new Intent(getBaseContext(),Buses.class);
                // intent.putExtra("BUS_COMPANY","SWIFT");
                 imageViewMain.setImageResource(R.drawable.swift_bus);
                 startActivity(new Intent(getBaseContext(),MainRoutes.class));
             }
         });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
