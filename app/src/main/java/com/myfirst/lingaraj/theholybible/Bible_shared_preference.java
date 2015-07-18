package com.myfirst.lingaraj.theholybible;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lingaraj on 7/2/2015.
 */
public class Bible_shared_preference extends Activity {
    public static final String preferance_name = "Bible_Preference";

    public Context Sharecontext;
public Bible_shared_preference(Context context)
{
    this.Sharecontext=context;
}


    public Float getData() {


        SharedPreferences sp = Sharecontext.getSharedPreferences(preferance_name, Context.MODE_PRIVATE);
       // SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(Sharecontext);

        Float value = sp.getFloat("my_int_key", 12);
        if(value!=12)
        {
            value=value-2f;
        }

        return value;


    }
    public void SetData(Float size)
    {
        SharedPreferences sp=Sharecontext.getSharedPreferences(preferance_name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor myeditor=sp.edit();
        myeditor.putFloat("my_int_key",size);
        myeditor.commit();

    }


}
