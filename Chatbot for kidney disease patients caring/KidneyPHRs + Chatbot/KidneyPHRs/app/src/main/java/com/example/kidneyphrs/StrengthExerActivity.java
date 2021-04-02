package com.example.kidneyphrs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.ExerContent.CATAGORY;
import static com.example.kidneyphrs.ExerContent.EXERCISE_NAME;
import static com.example.kidneyphrs.ExerContent.TABLE_NAME;

public class StrengthExerActivity extends AppCompatActivity {

    private ExerHelper helper;
    private SimpleCursorAdapter adapter;
    private String filter;
    private int getID;
    private EditText txtf_exerSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_exer);

        txtf_exerSearch = (EditText)findViewById(R.id.txtf_exerSearch);


        filter = "";

        txtf_exerSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // adapter.getFilter().filter(s);
                filter = s.toString();
                try {
                    Cursor cursor = getExercise();
                    showExercise(cursor);
                }
                finally {
                    helper.close();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        helper = new ExerHelper(this);

        try {
            Cursor cursor = getExercise();
            showExercise(cursor);
        }
        finally {
            helper.close();
        }
    }

    private static String[] COLUMNS = new String[]{_ID, EXERCISE_NAME, CATAGORY};
    //private static String[] COLUMNS = new String[]{EXERCISE_NAME, CATAGORY};

    private Cursor getExercise() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, CATAGORY + " == 2 AND " + EXERCISE_NAME + " LIKE '%" + filter +"%'", null, null, null, null);
        //String sql = "SELECT "+ EXERCISE_NAME + " FROM " + TABLE_NAME;
        //Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    private static int[] VIEWS = {R.id.txt_id, R.id.txt_exerciseName};
    //private static int[] VIEWS = {R.id.txt_exerciseName};

    private void showExercise(final Cursor cursor) {
        adapter = new SimpleCursorAdapter(this, R.layout.exercise_item, cursor, COLUMNS, VIEWS);
        //final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.exer_item, cursor, COLUMNS, VIEWS);
        //setListAdapter(adapter);

        ListView list = (ListView)findViewById(android.R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StrengthExerActivity.this, ExerciseByTypeActivity.class);
                intent.putExtra("backTo", 1);
                getID = cursor.getInt(cursor.getColumnIndex("_id"));
                intent.putExtra("id", getID);
                startActivity(intent);
            }
        });

    }

    /*------------------ HOME MENU ------------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        getMenuInflater().inflate(R.menu.menu_emergency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent homeIntent = new Intent(StrengthExerActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(StrengthExerActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
