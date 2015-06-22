package com.example.lingaraj.theholybible;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.*;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.PublicKey;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openDatabase;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by lingaraj on 6/12/2015.
 */
public class genesismain extends Fragment  {
    public DatabaseAssetHelper mydb;
    public static final String preferance_name="Bible_Preference";

    public Spinner mySpinner;
    public TextView mytextview;
    public ArrayAdapter<String> myadap;
    public String[] mylist = {"GENESIS 1", "GENESIS 2", "GENESIS 3", "GENESIS 4", "GENESIS 5", "GENESIS 6", "GENESIS 7", "GENESIS 8", "GENESIS 9", "GENESIS 10", "GENESIS 11", "GENESIS 12"
            , "GENESIS 13", "GENESIS 14", "GENESIS 15", "GENESIS 16", "GENESIS 17", "GENESIS 18", "GENESIS 19", "GENESIS 20", "GENESIS 21", "GENESIS 22", "GENESIS 23", "GENESIS 24", "GENESIS 25"
            , "GENESIS 26", "GENESIS 27", "GENESIS 28", "GENESIS 29", "GENESIS 30","GENESIS 31"," GENESIS 32", "GENESIS 33", "GENESIS 34", "GENESIS 35", "GENESIS 36"," GENESIS 37", "GENESIS 38", "GENESIS 39", "GENESIS 40"," GENESIS 41", "GENESIS 42"," GENESIS 43"," GENESIS 44", "GENESIS 45", "GENESIS 46"," GENESIS 47", "GENESIS 48"," GENESIS 49"," GENESIS 50"



    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootview = inflater.inflate(R.layout.genesis_main, container, false);
        mySpinner = (Spinner) rootview.findViewById(R.id.number_spin);
        mytextview=(TextView)rootview.findViewById(R.id.mytextview);
        mydb=new DatabaseAssetHelper(getActivity());
        myadap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mylist);

        get_shared_preferencevalue();


           Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/times.ttf");
           mytextview.setTypeface(tf);






        setHasOptionsMenu(true);
        mySpinner.setAdapter(myadap);

        myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            int item = position + 1;
            getData(item);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            int item = 1;
            getData(item);

        }
    });


        return rootview;



    }
/* Getting shared preference value and setting the same value to our textbox, The textbox size will be set to 18, if the value,
   returned was null, which happens,when the application was closed and restared again.
   */
    private void get_shared_preferencevalue() {

        try {


            SharedPreferences sp = this.getActivity().getSharedPreferences(preferance_name,Context.MODE_PRIVATE);
            //SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this.getActivity());
            int value=sp.getInt("my_int_key",18);

            if(value==0)
            {
                mytextview.setTextSize(18);

            }
            else
            {
                mytextview.setTextSize(value);
            }


        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }




    /* private void checksharedpreference() {
         int prefer_value=myprefernce.getInt("key_value", Integer.parseInt(null));
         if(prefer_value==0)
         {

         }
         else if(prefer_value>mytextview.getTextSize())
         {

             mytextview.setTextSize(prefer_value);
         }
     }
 */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void getData(int item)
{
  int myid=item;





     String query="SELECT sayings FROM genesis WHERE id=?";
    try {
        SQLiteDatabase sq = mydb.getReadableDatabase();

        Cursor mycursor = sq.rawQuery(query, new String[]{String.valueOf(myid)});
        mycursor.moveToFirst();

        String sayings = mycursor.getString(mycursor.getColumnIndex("sayings")).toString();
       //String sayings= mycursor.getString(0).toString();
        mycursor.close();
        mytextview.setText(sayings);
        mytextview.setVisibility(View.VISIBLE);

       // Toast.makeText(getActivity(),item,Toast.LENGTH_LONG).show();

     mydb.close();
    }
   catch(Exception e)
    {
       Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
    }

}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

}