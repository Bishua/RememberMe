package com.bishua.rememberme2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BishUA on 16.09.2014.
 */
public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rm_database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME_LESSONS = "lessons";
    public static final String UID = "_id";
    public static final String RUTEXT = "rutext";
    public static final String ENGTEXT = "engtext";
    public static final String ISLEARNED = "islearned";

    private static final String SQL_CREATE_LESSONS = "CREATE TABLE "
            + TABLE_NAME_LESSONS + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + RUTEXT + " VARCHAR(255)," + ENGTEXT + " VARCHAR(255)," + ISLEARNED +  " INTEGER);";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME_LESSONS;



    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_LESSONS);
        DbData dbData = new DbData();
        dbData.dbPut(sqLiteDatabase);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
