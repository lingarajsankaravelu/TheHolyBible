package com.example.lingaraj.theholybible;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lingaraj on 6/18/2015.
 */
public class numbers_main extends Fragment {
    public static final String preferance_name="Bible_Preference";
    public DatabaseAssetHelper mydb;
    public Spinner mySpinner;
    public TextView mytextview;
    public ArrayAdapter<String> myadap;
    public String[] mylist = {"NUMBERS 1", "NUMBERS 2", "NUMBERS 3", "NUMBERS 4", "NUMBERS 5", "NUMBERS 6", "NUMBERS 7", "NUMBERS 8", "NUMBERS 9", "NUMBERS 10", "NUMBERS 11", "NUMBERS 12"
            , "NUMBERS 13", "NUMBERS 14", "NUMBERS 15", "NUMBERS 16", "NUMBERS 17", "NUMBERS 18", "NUMBERS 19", "NUMBERS 20", "NUMBERS 21", "NUMBERS 22", "NUMBERS 23", "NUMBERS 24", "NUMBERS 25"
            , "NUMBERS 26", "NUMBERS 27", "NUMBERS 28", "NUMBERS 29", "NUMBERS 30","NUMBERS 31"," NUMBERS 32", "NUMBERS 33", "NUMBERS 34", "NUMBERS 35", "NUMBERS 36"




    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootview = inflater.inflate(R.layout.genesis_main, container, false);
        mySpinner = (Spinner) rootview.findViewById(R.id.number_spin);
        mytextview=(TextView)rootview.findViewById(R.id.mytextview);
        mydb=new DatabaseAssetHelper(getActivity());
        myadap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mylist);


        mySpinner.setAdapter(myadap);
        setHasOptionsMenu(true);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/times.ttf");
        mytextview.setTypeface(tf);
        get_shared_preferencevalue();


        myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int item=position+1;
                getData(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                int item=1;
                getData(item);

            }
        });


        return rootview;



    }

    public void getData(int item)
    {
        int myid=item;





        String query="SELECT sayings FROM numbers WHERE id=?";
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
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }


    }
    private void get_shared_preferencevalue() {

        try {


            SharedPreferences sp = this.getActivity().getSharedPreferences(preferance_name, Context.MODE_PRIVATE);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }



}

