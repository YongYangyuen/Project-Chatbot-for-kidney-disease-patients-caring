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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Calendar;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.HemoContent.AMOUNT;
import static com.example.kidneyphrs.HemoContent.DATE;
import static com.example.kidneyphrs.HemoContent.TABLE_NAME;

public class HemoAnalyzeActivity extends AppCompatActivity {

    private Button btn_datePick;
    private Button btn_allDay;

    private int pDay, pMonth, pYear;

    private HemoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hemo_analyze);

        helper = new HemoHelper(this);
        try {
            Cursor cursor = getHemo();
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(HemoAnalyzeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String s = year + "-" + String.format("%02d", monthOfYear + 1) + "-" + String.format("%02d", dayOfMonth);

                                try {
                                    Cursor cursor = getHemoByDate(s);
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
                    Cursor cursor = getHemo();
                    show(cursor);

                }
                finally {
                    helper.close();
                }
            }
        });
    }

    private static String[] COLUMNS = new String[]{_ID, DATE, AMOUNT};

    private Cursor getHemo(){
        SQLiteDatabase db = helper.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
        String sql = "SELECT _id, DATE(date) AS date, SUM(amount) AS amount FROM " + TABLE_NAME  + " GROUP BY DATE(" + TABLE_NAME + ".date)";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    private static int[] VIEWS = {R.id.txt_id, R.id.txt_dateAnalyze, R.id.txt_amountAnalyze};
    //private static int[] VIEWS = {R.id.txt_dateAnalyze, R.id.txt_amountAnalyze};
    private void show(Cursor cursor){

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.hemo_analyze_item, cursor, COLUMNS, VIEWS);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if(cursor.getInt(cursor.getColumnIndex("amount")) < 500){
                    view.setBackgroundColor(0xFFFF5252);
                }
                else if(cursor.getInt(cursor.getColumnIndex("amount")) >= 500){
                    view.setBackgroundColor(0xFF8BC34A);
                }
                return false;
            }
        });

        ListView list = (ListView)findViewById(android.R.id.list);
        list.setAdapter(adapter);
    }

    private Cursor getHemoByDate(String d){
        SQLiteDatabase db = helper.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
        String sql = "SELECT _id, DATE(date) AS date, SUM(amount) AS amount FROM " + TABLE_NAME + " WHERE DATE(date) = '" + d + "' GROUP BY DATE(" + TABLE_NAME + ".date)";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
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
            Intent homeIntent = new Intent(HemoAnalyzeActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(HemoAnalyzeActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
