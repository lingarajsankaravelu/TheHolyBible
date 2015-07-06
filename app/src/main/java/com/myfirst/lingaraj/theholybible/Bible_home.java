package com.myfirst.lingaraj.theholybible;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Bible_home extends AppCompatActivity {
    public myExpandableadapter adapter;
    public Bible_shared_preference ob;
    public ExpandableListView myexpandable;
    public List<String> parent;
    public List<String> child;
    HashMap<String,List<String>> bind_and_display;
    //Expandable list view intialization.

    public ClipboardManager cm;
    public  Spinner overflowbutton;
    public static final String preferance_name="Bible_Preference";
    public DrawerLayout mydrawer;
    public Toolbar mytoolToolbar;
    public String [] mylist;
    Fragment myfrag;
    public DatabaseAssetHelper mydb;
    FragmentManager mymanager;
    private Context mcontext;



    public android.support.v7.app.ActionBarDrawerToggle myActionBarDrawerToggle;
    public ArrayAdapter<String> navigationdrawerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_home);
        ob=new Bible_shared_preference(this);
         checkScreenSize();


        mylist = getResources().getStringArray(R.array.bible_List);
        mydrawer = (DrawerLayout) findViewById(R.id.mydrawerlayout);
        mytoolToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //Expandable listview Intitalization
        myexpandable=(ExpandableListView) findViewById(R.id.theexpandables);
        bind_and_display=new HashMap<String,List<String>>();
        parent = new ArrayList<String>();

        setSupportActionBar(mytoolToolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        SetMyCustomTheme();
        set_home_page();
        mytoolToolbar.setTitleTextColor(getResources().getColor(R.color.whitebackgroud));

        Set_Expandable_listview();



        initdrawer();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
     mydb= new DatabaseAssetHelper(this);


        mydb.close();

    }

    private void checkScreenSize() {
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                ob.SetData(20f);
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                 ob.SetData(18f);
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                ob.SetData(14f);
                break;

        }



    }


    public void Set_Expandable_listview() {

       parent= Arrays.asList(getResources().getStringArray(R.array.Bible1));

        bind_and_display.put(parent.get(0),Arrays.asList(getResources().getStringArray(R.array.old_Testament)));
        bind_and_display.put(parent.get(1),Arrays.asList(getResources().getStringArray(R.array.New_Testament)));

            adapter = new myExpandableadapter(this, parent, bind_and_display);
            myexpandable.setAdapter(adapter);
            myexpandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    v.setSelected(true);
                    int gposition = groupPosition;
                    int cposition = childPosition;
                    Child_click_display(gposition, cposition);

                    return false;
                }
            });




    }

    private void Child_click_display(int gposition, int cposition) {

        if(gposition==0)
        {
            switch (cposition+1)
            {
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
        else  if(gposition==1)
        {
            switch (cposition+1) {
                case 1:
                    myfrag = new matthew_main();
                    fragmentChange(myfrag);
                    break;
                case 2:
                    myfrag = new mark_main();
                    fragmentChange(myfrag);
                    break;
                case 3:
                    myfrag = new luke_main();
                    fragmentChange(myfrag);
                    break;
                case 4:
                    myfrag = new john_main();
                    fragmentChange(myfrag);
                    break;
                case 5:
                    myfrag = new acts_main();
                    fragmentChange(myfrag);
                    break;
                case 6:
                    myfrag = new romans_main();
                    fragmentChange(myfrag);
                    break;
                case 7:
                    myfrag = new firstchorinthians_main();
                    fragmentChange(myfrag);
                    break;
                case 8:
                    myfrag = new secondchorinthians_main();
                    fragmentChange(myfrag);
                    break;
                case 9:
                    myfrag = new galatians_main();
                    fragmentChange(myfrag);
                    break;
                case 10:
                    myfrag = new Ephesians_main();
                    fragmentChange(myfrag);
                    break;
                case 11:
                    myfrag = new philippians_main();
                    fragmentChange(myfrag);
                    break;
                case 12:
                    myfrag = new colossians_main();
                    fragmentChange(myfrag);
                    break;
                case 13:
                    myfrag = new firstthesolonians_main();
                    fragmentChange(myfrag);
                    break;
                case 14:
                    myfrag = new secondthesolonians_main();
                    fragmentChange(myfrag);
                    break;
                case 15:
                    myfrag = new firsttimothy_main();
                    fragmentChange(myfrag);
                    break;
                case 16:
                    myfrag = new secondtimothy_main();
                    fragmentChange(myfrag);
                    break;
                case 17:
                    myfrag = new titus_main();
                    fragmentChange(myfrag);
                    break;
                case 18:
                    myfrag = new philemon_main();
                    fragmentChange(myfrag);
                    break;
                case 19:
                    myfrag = new Hebrews_main();
                    fragmentChange(myfrag);
                    break;
                case 20:
                    myfrag = new james_main();
                    fragmentChange(myfrag);
                    break;
                case 21:
                    myfrag = new first_peter();
                    fragmentChange(myfrag);
                    break;
                case 22:
                    myfrag = new secondpeter_main();
                    fragmentChange(myfrag);
                    break;
                case 23:
                    myfrag = new firstjohn_main();
                    fragmentChange(myfrag);
                    break;
                case 24:
                    myfrag = new secondjohn_main();
                    fragmentChange(myfrag);
                    break;
                case 25:
                    myfrag = new thirdjohn_main();
                    fragmentChange(myfrag);
                    break;
                case 26:
                    myfrag = new jude_main();
                    fragmentChange(myfrag);
                    break;
                case 27:
                    myfrag = new revelation_main();
                    fragmentChange(myfrag);
                    break;


            }




        }



    }

    public void set_home_page() {
        myfrag= new bible_cover();
        mymanager = getSupportFragmentManager();

        mymanager.beginTransaction().replace(R.id.myframe,myfrag).show(myfrag).commit();


    }

   /* public void set_listview_header() {
        try {

            View header=getLayoutInflater().inflate(R.layout.listview_header_layout,null);
            listView_drawer.addHeaderView(header);

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
           }
*/

    private void SetMyCustomTheme() {



        LayoutInflater mInflater = (LayoutInflater) getSupportActionBar().getThemedContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        // setting up custom view
        View mCustomView = mInflater.inflate(R.layout.activity_customactionbar, null);
       //TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        //change it back to final Spinner

       /* overflowbutton.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int size = position;
                Get_text_view(size);
                //set_shared_preferencevalue(size);
                ob.SetData(size);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });*/



        getSupportActionBar().setCustomView(mCustomView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);



    }
  //setting shared preference which will be available through out the application
  /*  public void  set_shared_preferencevalue(int size)
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
*/

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
                 invalidateOptionsMenu();
             }
         };


        mydrawer.setDrawerListener(myActionBarDrawerToggle);
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

        getMenuInflater().inflate(R.menu.menu_customactionbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
       /* if(id==R.id.help)
        {
            Intent myintent= new Intent(this,help_main.class);
            startActivity(myintent);
            finish();

        }*/
         if(id==R.id.share)
        {
            ShareIntent();
        }



        return super.onOptionsItemSelected(item);
    }

    public void ShareIntent() {
            cm = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
             String text;//=  cm.getPrimaryClip().getItemAt(0).toString();
        if(cm.hasPrimaryClip())
        {
            ClipData.Item item=cm.getPrimaryClip().getItemAt(0);
            text=item.getText().toString();
            Intent sharingIntent= new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(sharingIntent,"Share Via"));

        }
        else
        {
            Toast.makeText(this,"Copy something to share",Toast.LENGTH_LONG).show();




        }






    }
}
