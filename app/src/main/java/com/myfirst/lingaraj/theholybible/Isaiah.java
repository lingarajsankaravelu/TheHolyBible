package com.myfirst.lingaraj.theholybible;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lingaraj on 6/19/2015.
 */
public class Isaiah extends Fragment implements View.OnTouchListener {
    final static float STEP = 200;
    float mRatio = 1.0f;
    int mBaseDist;
    float mBaseRatio;
    float fontsize = 13;
    public ScrollView myscrollview;
    public Bible_shared_preference ob;
    public DatabaseAssetHelper mydb;
    public Spinner mySpinner;
    public TextView mytextview;
    public ArrayAdapter<String> myadap;
    public String[] mylist = {"ISAIAH 1", "ISAIAH 2", "ISAIAH 3", "ISAIAH 4", "ISAIAH 5", "ISAIAH 6", "ISAIAH 7", "ISAIAH 8", "ISAIAH 9", "ISAIAH 10", "ISAIAH 11", "ISAIAH 12"
            , "ISAIAH 13", "ISAIAH 14", "ISAIAH 15", "ISAIAH 16", "ISAIAH 17", "ISAIAH 18", "ISAIAH 19", "ISAIAH 20", "ISAIAH 21", "ISAIAH 22", "ISAIAH 23", "ISAIAH 24", "ISAIAH 25"
            , "ISAIAH 26", "ISAIAH 27", "ISAIAH 28", "ISAIAH 29", "ISAIAH 30","ISAIAH 31"," ISAIAH 32", "ISAIAH 33", "ISAIAH 34", "ISAIAH 35", "ISAIAH 36"," ISAIAH 37", "ISAIAH 38", "ISAIAH 39", "ISAIAH 40"," ISAIAH 41", "ISAIAH 42"," ISAIAH 43"," ISAIAH 44", "ISAIAH 45", "ISAIAH 46"," ISAIAH 47", "ISAIAH 48"," ISAIAH 49"," ISAIAH 50"
            , "ISAIAH 61", "ISAIAH 62"
            , "ISAIAH 63", "ISAIAH 64", "ISAIAH 65", "ISAIAH 66"



    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootview = inflater.inflate(R.layout.genesis_main, container, false);
        mySpinner = (Spinner) rootview.findViewById(R.id.number_spin);
        mytextview=(TextView)rootview.findViewById(R.id.mytextview);
        myscrollview=(ScrollView)rootview.findViewById(R.id.scrollView_genesis);
        mydb=new DatabaseAssetHelper(getActivity());
        ob=new Bible_shared_preference(getActivity());
        myadap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mylist);
        //get_shared_preferencevalue();

        mySpinner.setAdapter(myadap);
        setHasOptionsMenu(true);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/times.ttf");
        mytextview.setTypeface(tf);
        get_shared_preferencevalue();


        myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mytextview.setOnTouchListener(this);

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





        String query="SELECT sayings FROM isaiah WHERE id=?";
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
       mytextview.setTextSize(ob.getData());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    int getDistance(MotionEvent event) {
        int dx = (int) (event.getX(0) - event.getX(1));
        int dy = (int) (event.getY(0) - event.getY(1));
        return (int) (Math.sqrt(dx * dx + dy * dy));
    }


    public boolean onTouch(View v, MotionEvent event) {


        if (event.getPointerCount() == 2) {

            int action = event.getAction();
            int pureaction = action & MotionEvent.ACTION_MASK;
            if (pureaction == MotionEvent.ACTION_POINTER_DOWN) {

                mBaseDist = getDistance(event);
                mBaseRatio = mRatio;
                myscrollview.requestDisallowInterceptTouchEvent(true);
            }
            else {
                float delta = (getDistance(event) - mBaseDist) / STEP;
                float multi = (float) Math.pow(2, delta);
                mRatio = Math.min(1024.0f, Math.max(0.1f, mBaseRatio * multi));
                mytextview.setTextSize(mRatio + 13);
            }


        }
        ob.SetData(mytextview.getTextSize());
        return false;
    }





}
