package com.example.lingaraj.theholybible;

import android.app.Activity;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.support.v4.*;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


public class Bible_home extends ActionBarActivity  {
    public ListView listView_drawer;
    public DrawerLayout mydrawer;
    public Toolbar mytoolToolbar;
    public String [] mylist;
    Fragment myfrag;
    public DatabaseAssetHelper mydb;
    FragmentManager mymanager;




    public android.support.v7.app.ActionBarDrawerToggle myActionBarDrawerToggle;
    public ArrayAdapter<String> navigationdrawerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_home);


        listView_drawer = (ListView) findViewById(R.id.listview_drawer);
        mylist = getResources().getStringArray(R.array.bible_List);
        mydrawer = (DrawerLayout) findViewById(R.id.mydrawerlayout);
        mytoolToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        mytoolToolbar.setTitleTextColor(getResources().getColor(R.color.whitebackgroud));
        // navigationdrawerAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.bible_List));
        navigationdrawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylist);
        listView_drawer.setAdapter(navigationdrawerAdapter);

        initdrawer();
     mydb= new DatabaseAssetHelper(this);
        mydb.close();

    }
    // Intializing the listview to be displayed on the left drawer toggle


    // Drawer Intialiazitoin and onclick listener
    private void initdrawer() {
         myActionBarDrawerToggle=new android.support.v7.app.ActionBarDrawerToggle(this,mydrawer,mytoolToolbar,R.string.drawer_opened,R.string.drawaer_closed) {

             public void onDrawerClosed(View drawerview) {
                 super.onDrawerClosed(drawerview);

                 invalidateOptionsMenu();
             }

             public void onDrawerOpen(View drawerview)

             {
                 super.onDrawerOpened(drawerview);
              //   getSupportActionBar().setTitle(getTitle());
                 invalidateOptionsMenu();
             }
         };



          mydrawer.setDrawerListener(myActionBarDrawerToggle);
        listView_drawer.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int posid= position+1;

                switch (posid)
                {
                    case 1:
                        myfrag=new genesismain();
                        fragmentChange(myfrag);
                        break;
                    case 2:
                        myfrag=new exodus_main();
                        fragmentChange(myfrag);
                        break;
                    case 3:
                        myfrag=new leviticus_main();
                        fragmentChange(myfrag);
                        break;
                    case 4:
                        myfrag=new numbers_main();
                        fragmentChange(myfrag);
                        break;
                    case 5:
                        myfrag=new deuteronomy_main();
                        fragmentChange(myfrag);
                        break;
                    case 6:
                        myfrag= new joshua_main();
                        fragmentChange(myfrag);
                        break;
                    case 7:
                        myfrag= new judges_main();
                        fragmentChange(myfrag);
                        break;
                    case 8:
                        myfrag= new ruth_main();
                        fragmentChange(myfrag);
                        break;
                    case 9:
                        myfrag= new one_samuel_main();
                        fragmentChange(myfrag);
                        break;
                    case 10:
                        myfrag=new second_samuel_main();
                        fragmentChange(myfrag);
                        break;
                    case 11:
                        myfrag= new one_kings_main();
                        fragmentChange(myfrag);
                        break;
                    case 12:
                        myfrag= new second_kings_main();
                        fragmentChange(myfrag);
                        break;
                    case 13:
                        myfrag=new first_chronicles();
                        fragmentChange(myfrag);
                        break;
                    case 14:
                        myfrag= new second_chronicles();
                        fragmentChange(myfrag);
                        break;
                    case 15:
                        myfrag= new ezra_main();
                        fragmentChange(myfrag);
                        break;
                    case 16:
                        myfrag= new nehemiah();
                        fragmentChange(myfrag);
                        break;
                    case 17:
                        myfrag= new esther_main();
                        fragmentChange(myfrag);
                        break;
                    case 18:
                        myfrag=new job_main();
                        fragmentChange(myfrag);
                        break;
                    case 19:
                        myfrag= new psalms_main();
                        fragmentChange(myfrag);
                        break;
                    case 20:
                        myfrag= new proverbs_main();
                        fragmentChange(myfrag);
                        break;
                    case 21:
                        myfrag= new Eccesiastes_main();
                        fragmentChange(myfrag);
                        break;
                    case 22:
                        myfrag=new songofsolomon();
                        fragmentChange(myfrag);
                        break;
                    case 23:
                        myfrag= new Isaiah();
                        fragmentChange(myfrag);
                        break;
                    case 24:
                        myfrag=new Jeremiah();
                        fragmentChange(myfrag);
                        break;
                    case 25:
                        myfrag=new Lamentation_main();
                        fragmentChange(myfrag);
                        break;
                    case 26:
                        myfrag= new Ezekiel();
                        fragmentChange(myfrag);
                        break;
                    case 27:
                        myfrag=new Daniel();
                        fragmentChange(myfrag);
                        break;
                    case 28:
                        myfrag= new Hosea_main();
                        fragmentChange(myfrag);
                        break;
                    case 29:
                        myfrag= new Joel();
                        fragmentChange(myfrag);
                        break;
                    case 30:
                        myfrag=new Amos_main();
                        fragmentChange(myfrag);
                        break;
                    case 31:
                        myfrag=new obadiah_main();
                        fragmentChange(myfrag);
                        break;
                    case 32:
                        myfrag= new Jonah_main();
                        fragmentChange(myfrag);
                        break;
                    case 33:
                        myfrag=new Micah_main();
                        fragmentChange(myfrag);
                        break;
                    case 34:
                        myfrag= new Nahum_main();
                        fragmentChange(myfrag);
                        break;
                    case 35:
                        myfrag=new Habakkuk_main();
                        fragmentChange(myfrag);
                        break;
                    case 36:
                        myfrag= new Zephaniah_main();
                        fragmentChange(myfrag);
                        break;
                    case 37:
                        myfrag=new Haggai_main();
                        fragmentChange(myfrag);
                        break;
                    case 38:
                        myfrag=new Zechariah_main();
                        fragmentChange(myfrag);
                        break;
                    case 39:
                        myfrag= new Malachi_main();
                        fragmentChange(myfrag);
                        break;
                }
            }
        });
    }




   // changing Fragment according to listview selection
    public  void fragmentChange(Fragment newfrag)
    {

        mymanager = getSupportFragmentManager();
        mymanager.beginTransaction().replace(R.id.myframe, newfrag).show(newfrag).commit();
        mydrawer.closeDrawer(Gravity.LEFT);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    public void onBackPressed() {
        if(mydrawer.isDrawerOpen(Gravity.LEFT))
        {
            mydrawer.closeDrawer(Gravity.LEFT);
        }
        else {
            super.onBackPressed();
        }
        }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bible_home, menu);
       // MenuInflater menuInflater=getMenuInflater();
        //menuInflater.inflate(R.menu.menu_bible_home,menu);
        //MenuItem mitem=menu.findItem(R.id.mymenu);
        //Spinner spin =(Spinner) mitem.getActionView();
        //setupSpinner(spin);

        return false;
    }

   /* public void setupSpinner(Spinner spin) {
       Integer[] textsize={12,14,16,18,20};
        ArrayAdapter<Integer> adapter;
        adapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,textsize);
        spin.setAdapter(adapter);

//assign adapter to the Spinner
       // spin.setAdapter(adapter);

    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
