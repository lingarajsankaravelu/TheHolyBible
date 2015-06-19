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
public class first_chronicles extends Fragment {
    public DatabaseAssetHelper mydb;
    public Spinner mySpinner;
    public TextView mytextview;
    public ArrayAdapter<String> myadap;
    public String[] mylist = {"1st CHRONICLES 1", "1st CHRONICLES 2", "1st CHRONICLES 3", "1st CHRONICLES 4", "1st CHRONICLES 5", "1st CHRONICLES 6", "1st CHRONICLES 7", "1st CHRONICLES 8", "1st CHRONICLES 9", "1st CHRONICLES 10", "1st CHRONICLES 11", "1st CHRONICLES 12"
            , "1st CHRONICLES 13", "1st CHRONICLES 14", "1st CHRONICLES 15", "1st CHRONICLES 16", "1st CHRONICLES 17", "1st CHRONICLES 18", "1st CHRONICLES 19", "1st CHRONICLES 20", "1st CHRONICLES 21", "1st CHRONICLES 22", "1st CHRONICLES 23", "1st CHRONICLES 24", "1st CHRONICLES 25"
            , "1st CHRONICLES 26", "1st CHRONICLES 27", "1st CHRONICLES 28", "1st CHRONICLES 29"




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





        String query="SELECT sayings FROM firstchronicle WHERE id=?";
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
