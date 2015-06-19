package com.example.lingaraj.theholybible;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
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
 * Created by lingaraj on 6/19/2015.
 */
public class psalms_main extends Fragment {
    public DatabaseAssetHelper mydb;
    public Spinner mySpinner;
    public TextView mytextview;
    public ArrayAdapter<String> myadap;
    public String[] mylist = {"PSALMS 1", "PSALMS 2", "PSALMS 3", "PSALMS 4", "PSALMS 5", "PSALMS 6", "PSALMS 7", "PSALMS 8", "PSALMS 9", "PSALMS 10", "PSALMS 11", "PSALMS 12"
            , "PSALMS 13", "PSALMS 14", "PSALMS 15", "PSALMS 16", "PSALMS 17", "PSALMS 18", "PSALMS 19", "PSALMS 20", "PSALMS 21", "PSALMS 22", "PSALMS 23", "PSALMS 24", "PSALMS 25"
            , "PSALMS 26", "PSALMS 27", "PSALMS 28", "PSALMS 29", "PSALMS 30","PSALMS 31"," PSALMS 32", "PSALMS 33", "PSALMS 34", "PSALMS 35", "PSALMS 36"," PSALMS 37", "PSALMS 38", "PSALMS 39", "PSALMS 40"," PSALMS 41", "PSALMS 42"," PSALMS 43"," PSALMS 44", "PSALMS 45", "PSALMS 46"," PSALMS 47", "PSALMS 48"," PSALMS 49"," PSALMS 50",
            "PSALMS 51", "PSALMS 52", "PSALMS 53", "PSALMS 54", "PSALMS 55", "PSALMS 56", "PSALMS 57", "PSALMS 58", "PSALMS 59", "PSALMS 60", "PSALMS 61", "PSALMS 62"
            , "PSALMS 63", "PSALMS 64", "PSALMS 65", "PSALMS 66", "PSALMS 67", "PSALMS 68", "PSALMS 69", "PSALMS 70", "PSALMS 71", "PSALMS 72", "PSALMS 73", "PSALMS 74", "PSALMS 75"
            , "PSALMS 76", "PSALMS 77", "PSALMS 78", "PSALMS 79", "PSALMS 80","PSALMS 81"," PSALMS 82", "PSALMS 83", "PSALMS 84", "PSALMS 85", "PSALMS 86"," PSALMS 87", "PSALMS 88", "PSALMS 89", "PSALMS 90"," PSALMS 91", "PSALMS 92"," PSALMS 93"," PSALMS 94", "PSALMS 95", "PSALMS 96"," PSALMS 97", "PSALMS 98"," PSALMS 99"," PSALMS 100"





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
        mytextview.setTextSize(16);


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





        String query="SELECT sayings FROM psalms WHERE id=?";
        try {
            SQLiteDatabase sq = mydb.getReadableDatabase();

            Cursor mycursor = sq.rawQuery(query, new String[]{String.valueOf(myid)});
            mycursor.moveToFirst();

            String sayings = mycursor.getString(mycursor.getColumnIndex("sayings")).toString();
            //String sayings= mycursor.getString(0).toString();
            mycursor.close();
            mytextview.setText(sayings);
            // Toast.makeText(getActivity(),item,Toast.LENGTH_LONG).show();

            mydb.close();
        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
    public void TextSize(int size)
    {
        mytextview.setTextSize(size);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.eleven)
        {
            TextSize(11);
        }
        else if(item.getItemId()==R.id.tweleve)
        {
            TextSize(12);
        }
        else if(item.getItemId()==R.id.thriteen)
        {
            TextSize(13);
        }
        else if(item.getItemId()==R.id.fourteen)
        {
            TextSize(14);
        }
        else if(item.getItemId()==R.id.fifteen)
        {
            TextSize(15);
        }
        else if(item.getItemId()==R.id.sixteen)
        {
            TextSize(16);
        }
        else if(item.getItemId()==R.id.seventeen)
        {
            TextSize(17);
        }
        else if(item.getItemId()==R.id.eighteen)
        {
            TextSize(18);
        }
        else if(item.getItemId()==R.id.nineteen)
        {
            TextSize(19);
        }
        else if(item.getItemId()==R.id.twenty)
        {
            TextSize(20);
        }
        else if(item.getItemId()==R.id.twentyone)
        {
            TextSize(21);
        }
        else if(item.getItemId()==R.id.twentytwo)
        {
            TextSize(22);
        }
        else if(item.getItemId()==R.id.twentythree)
        {
            TextSize(23);
        }
        else if(item.getItemId()==R.id.twentyfour)
        {
            TextSize(24);
        }
        else if(item.getItemId()==R.id.twentyfive)
        {
            TextSize(25);
        }
        return super.onOptionsItemSelected(item);
    }


}
