package com.bishua.rememberme2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by BishUA on 17.09.2014.
 */
public class DbData {


    private String ru [] = {"слово", "яблоко", "карандаш", "дерево", "машина",
            "автобус", "ананас", "груша", "торт", "масло",
            "работа", "глаза", "кот", "собака", "попугай"};

    private String en [] = {"word", "apple", "pencil", "tree", "car",
            "Bus", "pineapple", "pear", "cake", "oil",
            "work", "eyes", "cat", "dog", "parrot"};



    public void dbPut(SQLiteDatabase sqLiteDatabase) {



        for  (int i =0; i<15; i++){
            ContentValues cv = new ContentValues();
            cv.put(Database.RUTEXT, ru[i]);
            cv.put(Database.ENGTEXT, en[i]);
            cv.put(Database.ISLEARNED, 0);
            sqLiteDatabase.insert(Database.TABLE_NAME_LESSONS, null, cv);
        }



    }

}
