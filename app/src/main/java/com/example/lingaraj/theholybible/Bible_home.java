package com.example.lingaraj.theholybible;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.support.v4.*;
import android.widget.Switch;
import android.widget.Toast;


public class Bible_home extends ActionBarActivity {
    public ListView listView_drawer;
    public DrawerLayout mydrawer;
    public Toolbar mytoolToolbar;
    public String [] mylist;
    Fragment myfrag;
    FragmentManager mymanager;




    public android.support.v7.app.ActionBarDrawerToggle myActionBarDrawerToggle;
    public ArrayAdapter<String> navigationdrawerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_home);


        listView_drawer=(ListView) findViewById(R.id.listview_drawer);
        try {


            mylist = getResources().getStringArray(R.array.bible_List);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString() ,Toast.LENGTH_SHORT).show();
        }


        mydrawer=(DrawerLayout)findViewById(R.id.mydrawerlayout);




        mytoolToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mytoolToolbar.setTitleTextColor(getResources().getColor(R.color.whitebackgroud));
      // navigationdrawerAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.bible_List));
        navigationdrawerAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mylist);
        listView_drawer.setAdapter(navigationdrawerAdapter);



        initdrawer();

        myfrag= new genesismain();
        mymanager=getSupportFragmentManager();
        mymanager.beginTransaction().replace(R.id.myframe,myfrag).show(myfrag).commit();


    }


    // Intializing the listview to be displayed on the left drawer toggle
    private void initlistview() {


    }

    private void initdrawer() {
         myActionBarDrawerToggle=new android.support.v7.app.ActionBarDrawerToggle(this,mydrawer,mytoolToolbar,R.string.drawer_opened,R.string.drawaer_closed) {
             public void onDrawerClosed(View drawerview) {
                 super.onDrawerClosed(drawerview);
                 getSupportActionBar().setTitle(getTitle());
                 invalidateOptionsMenu();
             }

             public void onDrawerOpen(View drawerview)

             {
                 super.onDrawerOpened(drawerview);
                 getSupportActionBar().setTitle(getTitle());
                 invalidateOptionsMenu();
             }
         };
        mydrawer.setDrawerListener(myActionBarDrawerToggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        myActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        myActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bible_home, menu);
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

        if(myActionBarDrawerToggle.onOptionsItemSelected(item))
        {
            int position=item.getItemId();
            switch (position)
            {
                case 1:

                    Toast.makeText(this, position ,Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Toast.makeText(this, "other than first item pressed" ,Toast.LENGTH_SHORT).show();
                    break;



            }
        }



        return super.onOptionsItemSelected(item);
    }
}
