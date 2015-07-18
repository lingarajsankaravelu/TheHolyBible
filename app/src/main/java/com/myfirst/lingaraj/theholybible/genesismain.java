package com.myfirst.lingaraj.theholybible;

import android.database.sqlite.*;
import android.database.Cursor;
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

import static android.database.sqlite.SQLiteDatabase.openDatabase;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by lingaraj on 6/12/2015.
 */
public class genesismain extends Fragment implements View.OnTouchListener {
    final static float STEP = 200;
    float mRatio = 1.0f;
    int mBaseDist;
    float mBaseRatio;
    float fontsize = 13;
    public ScrollView myscrollview;
    public DatabaseAssetHelper mydb;
    public  Bible_shared_preference ob;

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
        myscrollview=(ScrollView)rootview.findViewById(R.id.scrollView_genesis);
        mydb=new DatabaseAssetHelper(getActivity());
        ob= new Bible_shared_preference(getActivity());
        myadap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mylist);
               get_shared_preferencevalue();


           Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/times.ttf");
           mytextview.setTypeface(tf);






        setHasOptionsMenu(true);
        mySpinner.setAdapter(myadap);

        myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mytextview.setOnTouchListener(this);

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
    private void get_shared_preferencevalue()
    {

        mytextview.setTextSize(ob.getData());

    }






    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);




    }

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
               // mytextview.setTextSize(ob.getData());
            }


        }
        ob.SetData(mytextview.getTextSize());


        return false;
    }



}