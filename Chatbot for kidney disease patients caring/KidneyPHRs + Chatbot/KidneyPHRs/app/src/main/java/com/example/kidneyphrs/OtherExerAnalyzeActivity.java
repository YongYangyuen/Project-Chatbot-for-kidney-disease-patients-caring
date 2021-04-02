package com.example.kidneyphrs;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Calendar;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.OtherExerContent.DATE;
import static com.example.kidneyphrs.OtherExerContent.EXERCISE_NAME;
import static com.example.kidneyphrs.OtherExerContent.TABLE_NAME;

public class OtherExerAnalyzeActivity extends AppCompatActivity {

    private Button btn_datePick;
    private Button btn_allDay;

    private int pDay, pMonth, pYear;
    private int getID;

    private OtherExerResultHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_exer_analyze);

        helper = new OtherExerResultHelper(this);
        try {
            Cursor cursor = getExer();
            show(cursor);

        }
        finally {
            helper.close();
        }

        btn_datePick = (Button)findViewById(R.id.btn_datePick);
        btn_datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                pYear = c.get(Calendar.YEAR);
                pMonth = c.get(Calendar.MONTH);
                pDay = c.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(OtherExerAnalyzeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String s = year + "-" + String.format("%02d", monthOfYear + 1) + "-" + String.format("%02d", dayOfMonth);

                                try {
                                    Cursor cursor = getByDate(s);
                                    show(cursor);

                                }
                                finally {
                                    helper.close();
                                }
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();
            }
        });

        btn_allDay = (Button)findViewById(R.id.btn_allDay);
        btn_allDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor cursor = getExer();
                    show(cursor);

                }
                finally {
                    helper.close();
                }
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
            Intent homeIntent = new Intent(OtherExerAnalyzeActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(OtherExerAnalyzeActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private static String[] COLUMNS = new String[]{_ID, DATE, EXERCISE_NAME};

    private Cursor getExer(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT _id, DATE(date) AS date, ExerciseName FROM " + TABLE_NAME ;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    private static int[] VIEWS = {R.id.txt_id, R.id.txt_dateAnalyze, R.id.txt_exerNameAnalyze};

    private void show(final Cursor cursor){
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.other_exer_item, cursor, COLUMNS, VIEWS);

        ListView list = (ListView)findViewById(android.R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OtherExerAnalyzeActivity.this, OtherExerAnalyzeDetailActivity.class);
                getID = cursor.getInt(cursor.getColumnIndex("_id"));
                intent.putExtra("id", getID);
                startActivity(intent);
            }
        });
    }

    private Cursor getByDate(String d){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT _id, DATE(date) AS date, ExerciseName FROM " + TABLE_NAME + " WHERE DATE(date) = '" + d + "'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
}
