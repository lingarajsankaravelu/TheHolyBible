package com.myfirst.lingaraj.theholybible;

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
public class job_main extends Fragment implements View.OnTouchListener {
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
    public String[] mylist = {"JOB 1", "JOB 2", "JOB 3", "JOB 4", "JOB 5", "JOB 6", "JOB 7", "JOB 8", "JOB 9", "JOB 10", "JOB 11", "JOB 12"
            , "JOB 13", "JOB 14", "JOB 15", "JOB 16", "JOB 17", "JOB 18", "JOB 19", "JOB 20", "JOB 21", "JOB 22", "JOB 23", "JOB 24", "JOB 25"
            , "JOB 26", "JOB 27", "JOB 28", "JOB 29", "JOB 30","JOB 31"," JOB 32", "JOB 33", "JOB 34", "JOB 35", "JOB 36"," JOB 37", "JOB 38", "JOB 39", "JOB 40"," JOB 41", "JOB 42"



    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootview = inflater.inflate(R.layout.genesis_main, container, false);
        mySpinner = (Spinner) rootview.findViewById(R.id.number_spin);
        mytextview=(TextView)rootview.findViewById(R.id.mytextview);
        myscrollview=(ScrollView)rootview.findViewById(R.id.scrollView_genesis);
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





        String query="SELECT sayings FROM job WHERE id=?";
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