package com.bishua.rememberme2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Button learnMore;
    private Button reiteration;
    private Button exit;
    private LessonList list;
    public static final String TYPE_NEW = "0";
    public static final String TYPE_OLD = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database database = new Database(this);
        SQLiteDatabase sqdb = database.getWritableDatabase();
        sqdb.close();
        database.close();

        learnMore = (Button) findViewById(R.id.learnMore);
        learnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLessons(TYPE_NEW);
                if(list.getList().size() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "nothing to Learn", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                letsLearn(false);
            }
        });
        reiteration = (Button) findViewById(R.id.reiteration);
        reiteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLessons(TYPE_OLD);
                if(list.getList().size() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "nothing to repeat", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                letsLearn(true);
            }
        });

        exit = (Button) findViewById(R.id.button3);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getLessons(String type){
        Database database = new Database(this);
        SQLiteDatabase sqdb = database.getReadableDatabase();
        Cursor cursor = sqdb.query(Database.TABLE_NAME_LESSONS, new String[]
                {Database.UID, Database.ENGTEXT, Database.RUTEXT, Database.ISLEARNED},
                Database.ISLEARNED + "=" + type,
                null,
                null,
                null,
                null);


        Lesson lesson = null;
        list = new LessonList();
        int count = 0;
        while (cursor.moveToNext() &&  count<10) {

            lesson = new Lesson(cursor.getInt(cursor.getColumnIndex(Database.UID)),
                    cursor.getString(cursor.getColumnIndex(Database.ENGTEXT)),
                    cursor.getString(cursor.getColumnIndex(Database.RUTEXT)));
            list.putValue(lesson);
            count++;
        }
        Log.i("LOG_TAG", " " + list.getList().size());
        sqdb.close();
        database.close();


    }

    private void letsLearn(Boolean isLearned){
        Intent intent = new Intent(this, LearningActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", list);
        intent.putExtras(bundle);
        intent.putExtra("isLearned", isLearned);
        startActivity(intent);
    }


    private void dbPut(){
        Database database = new Database(this);
        SQLiteDatabase sqdb = database.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Database.RUTEXT, "слово");
        cv.put(Database.ENGTEXT, "word");
        cv.put(Database.ISLEARNED, 0);
        sqdb.insert(Database.TABLE_NAME_LESSONS, null, cv );
        sqdb.close();
        database.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
