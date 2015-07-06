package com.myfirst.lingaraj.theholybible;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by lingaraj on 6/18/2015.
 *
 */
public class DatabaseAssetHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "theholybible.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DatabaseAssetHelper(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);

    }


}
