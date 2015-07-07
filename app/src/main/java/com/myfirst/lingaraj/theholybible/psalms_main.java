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
public class psalms_main extends Fragment implements View.OnTouchListener {
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
        myscrollview=(ScrollView) rootview.findViewById(R.id.scrollView_genesis);
        mydb=new DatabaseAssetHelper(getActivity());
        ob= new Bible_shared_preference(getActivity());
        myadap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mylist);


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





        String query="SELECT sayings FROM psalms WHERE id=?";
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
