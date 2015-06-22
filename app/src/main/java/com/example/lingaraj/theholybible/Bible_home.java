package com.example.lingaraj.theholybible;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.v4.*;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Bible_home extends ActionBarActivity  {
    public ListView listView_drawer;
    public  Spinner overflowbutton;
    public static final String preferance_name="Bible_Preference";

    public Bundle mybundle;
    public int transfer;
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
        SetMyCustomTheme();










        mytoolToolbar.setTitleTextColor(getResources().getColor(R.color.whitebackgroud));
        // navigationdrawerAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.bible_List));
        set_listview_header();
        navigationdrawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylist);
        listView_drawer.setAdapter(navigationdrawerAdapter);


        initdrawer();
     mydb= new DatabaseAssetHelper(this);
        mydb.close();

    }

    public void set_listview_header() {
        try {

            View header=getLayoutInflater().inflate(R.layout.listview_header_layout,null);
            listView_drawer.addHeaderView(header);

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
           }


    private void SetMyCustomTheme() {
        Integer [] mysize={1,2,3,4,5,6,7,8,9,10};
        String [] mysize1={"Tsize","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
        "18","19","20","21","22","23","24","25"};

        LayoutInflater mInflater = (LayoutInflater) getSupportActionBar().getThemedContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        // setting up custom view
        View mCustomView = mInflater.inflate(R.layout.activity_customactionbar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        //change it back to final Spinner
         overflowbutton=(Spinner) mCustomView.findViewById(R.id.imageButton);
        ArrayAdapter<String> actionbar_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,mysize1);
        actionbar_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overflowbutton.setAdapter(actionbar_adapter);
        overflowbutton.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int size = position;
                Get_text_view(size);
                set_shared_preferencevalue(size);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });



        getSupportActionBar().setCustomView(mCustomView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);



    }
  //setting shared preference which will be available through out the application
    public void  set_shared_preferencevalue(int size)
    {
      try
      {
         SharedPreferences sp=getSharedPreferences(preferance_name,Activity.MODE_PRIVATE);
          SharedPreferences.Editor myeditor=sp.edit();
          myeditor.putInt("my_int_key",size);

          myeditor.commit();
      }
      catch (Exception e)
      {
          Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
      }
    }


    public void Get_text_view(int size)
    {
        if(size<=0)
        {

        }
        else
        {
            try {

              TextView changetextsize=(TextView) myfrag.getActivity().findViewById(R.id.mytextview);
                changetextsize.setTextSize(size);


            }
            catch (Exception e)
            {

            }
        }
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
                int posid = position;

                switch (posid) {
                    case 1:
                        myfrag = new genesismain();
                        fragmentChange(myfrag);
                        break;
                    case 2:
                        myfrag = new exodus_main();
                        fragmentChange(myfrag);
                        break;
                    case 3:
                        myfrag = new leviticus_main();
                        fragmentChange(myfrag);
                        break;
                    case 4:
                        myfrag = new numbers_main();
                        fragmentChange(myfrag);
                        break;
                    case 5:
                        myfrag = new deuteronomy_main();
                        fragmentChange(myfrag);
                        break;
                    case 6:
                        myfrag = new joshua_main();
                        fragmentChange(myfrag);
                        break;
                    case 7:
                        myfrag = new judges_main();
                        fragmentChange(myfrag);
                        break;
                    case 8:
                        myfrag = new ruth_main();
                        fragmentChange(myfrag);
                        break;
                    case 9:
                        myfrag = new one_samuel_main();
                        fragmentChange(myfrag);
                        break;
                    case 10:
                        myfrag = new second_samuel_main();
                        fragmentChange(myfrag);
                        break;
                    case 11:
                        myfrag = new one_kings_main();
                        fragmentChange(myfrag);
                        break;
                    case 12:
                        myfrag = new second_kings_main();
                        fragmentChange(myfrag);
                        break;
                    case 13:
                        myfrag = new first_chronicles();
                        fragmentChange(myfrag);
                        break;
                    case 14:
                        myfrag = new second_chronicles();
                        fragmentChange(myfrag);
                        break;
                    case 15:
                        myfrag = new ezra_main();
                        fragmentChange(myfrag);
                        break;
                    case 16:
                        myfrag = new nehemiah();
                        fragmentChange(myfrag);
                        break;
                    case 17:
                        myfrag = new esther_main();
                        fragmentChange(myfrag);
                        break;
                    case 18:
                        myfrag = new job_main();
                        fragmentChange(myfrag);
                        break;
                    case 19:
                        myfrag = new psalms_main();
                        fragmentChange(myfrag);
                        break;
                    case 20:
                        myfrag = new proverbs_main();
                        fragmentChange(myfrag);
                        break;
                    case 21:
                        myfrag = new Eccesiastes_main();
                        fragmentChange(myfrag);
                        break;
                    case 22:
                        myfrag = new songofsolomon();
                        fragmentChange(myfrag);
                        break;
                    case 23:
                        myfrag = new Isaiah();
                        fragmentChange(myfrag);
                        break;
                    case 24:
                        myfrag = new Jeremiah();
                        fragmentChange(myfrag);
                        break;
                    case 25:
                        myfrag = new Lamentation_main();
                        fragmentChange(myfrag);
                        break;
                    case 26:
                        myfrag = new Ezekiel();
                        fragmentChange(myfrag);
                        break;
                    case 27:
                        myfrag = new Daniel();
                        fragmentChange(myfrag);
                        break;
                    case 28:
                        myfrag = new Hosea_main();
                        fragmentChange(myfrag);
                        break;
                    case 29:
                        myfrag = new Joel();
                        fragmentChange(myfrag);
                        break;
                    case 30:
                        myfrag = new Amos_main();
                        fragmentChange(myfrag);
                        break;
                    case 31:
                        myfrag = new obadiah_main();
                        fragmentChange(myfrag);
                        break;
                    case 32:
                        myfrag = new Jonah_main();
                        fragmentChange(myfrag);
                        break;
                    case 33:
                        myfrag = new Micah_main();
                        fragmentChange(myfrag);
                        break;
                    case 34:
                        myfrag = new Nahum_main();
                        fragmentChange(myfrag);
                        break;
                    case 35:
                        myfrag = new Habakkuk_main();
                        fragmentChange(myfrag);
                        break;
                    case 36:
                        myfrag = new Zephaniah_main();
                        fragmentChange(myfrag);
                        break;
                    case 37:
                        myfrag = new Haggai_main();
                        fragmentChange(myfrag);
                        break;
                    case 38:
                        myfrag = new Zechariah_main();
                        fragmentChange(myfrag);
                        break;
                    case 39:
                        myfrag = new Malachi_main();
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

        mymanager.beginTransaction().replace(R.id.myframe, newfrag).show(newfrag).addToBackStack(null).commit();


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
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        int count=getFragmentManager().getBackStackEntryCount();
        if(mydrawer.isDrawerOpen(Gravity.LEFT))
        {
            mydrawer.closeDrawer(Gravity.LEFT);
        }
        else if(count>1)
        {
            getFragmentManager().popBackStack();
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
        //MenuInflater menuInflater=getMenuInflater();

       //MenuItem mitem=menu.findItem(R.id.myoverflow);
        //Spinner spin =(Spinner) mitem.getActionView();
       // ListView over_list=(ListView) mitem.getActionView();

        //setupoverflowlist(spin);

        return true;
    }


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
