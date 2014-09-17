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
import android.widget.TextView;


public class LearningActivity extends ActionBarActivity {

    private Button buttonNext;
    private Button buttonPrev;
    private Button buttonRemember;
    private Button buttonForgot;
    private TextView enText;
    private TextView ruText;
    private TextView counter;
    private LessonList list;
    private Lesson lesson;
    private int current;
    private boolean isLearned;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
//        buttonNext = (Button) findViewById(R.id.buttonNext);
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (current == list.getList().size()) return;
//                current++;
//                initControls();
//            }
//        });
//        buttonPrev = (Button) findViewById(R.id.buttonPrev);
//        buttonPrev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (current == 0) return;
//                current--;
//                initControls();
//
//            }
//        });
        buttonRemember = (Button) findViewById(R.id.rememberButton);
        buttonRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLessonType(1);
                if (current == list.getList().size()-1){
                    goMain();
                    return;
                }
                current++;
                initControls();
            }
        });
        buttonForgot = (Button) findViewById(R.id.forgotButton);
        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLessonType(0);
                if (current == list.getList().size()-1){
                    goMain();
                    return;
                }
                current++;
                initControls();
            }
        });

        enText = (TextView) findViewById(R.id.textEn);
        ruText = (TextView) findViewById(R.id.textRu);
        counter = (TextView) findViewById(R.id.counter);

        Bundle bundle = getIntent().getExtras();
        list = (LessonList) bundle.getSerializable("list");
        lesson = list.getList().get(current);
        isLearned = getIntent().getBooleanExtra("isLearned", false);
        initControls();

    }




    public void initControls(){
        lesson = list.getList().get(current);
        enText.setText(lesson.getEnText());
        ruText.setText(lesson.getRuText());
        if(isLearned){
            ruText.setVisibility(View.INVISIBLE);
        }else{
            buttonForgot.setVisibility(View.INVISIBLE);
        }

        counter.setText("< "+(current +1 )+ " of " + list.getList().size() +   " >");
    }

    private void changeLessonType(int i){
        Database database = new Database(this);
        SQLiteDatabase sqdb = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.ISLEARNED,  i);
        sqdb.update(Database.TABLE_NAME_LESSONS, contentValues, Database.UID + " = " + lesson.getId(),
                null);
        sqdb.close();
        database.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.learning, menu);
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

    private void goMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

//    @Override
//    public void onBackPressed() {
//        goMain();
//
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("list", list);
        outState.putInt("current", current);
        outState.putBoolean("isLearned", isLearned);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        list = (LessonList) savedInstanceState.getSerializable("list");
        current = savedInstanceState.getInt("current");
        isLearned = savedInstanceState.getBoolean("isLearned");
        initControls();
    }
}
