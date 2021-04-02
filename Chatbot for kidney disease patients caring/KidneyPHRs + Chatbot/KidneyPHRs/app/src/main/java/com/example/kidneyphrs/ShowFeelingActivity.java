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
import java.util.Date;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.FeelingContent.TABLE_NAME_FEELING;
import static com.example.kidneyphrs.MedicineInfoContent.DATE;

public class ShowFeelingActivity extends AppCompatActivity {

    private Button btn_datePick;
    private Button btn_allDay;
    private Date dateTemp;
    private int pDay, pMonth, pYear;

    private FeelingHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feeling);

        helper = new FeelingHelper(this);

        try {
            Cursor cursor = getFeeling();
            showFeeling(cursor);
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(ShowFeelingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String s = year + "-" + String.format("%02d", monthOfYear + 1) + "-" + String.format("%02d", dayOfMonth);

                                try {
                                    Cursor cursor = getFeelingByDate(s);
                                    showFeeling(cursor);

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
                    Cursor cursor = getFeeling();
                    showFeeling(cursor);

                }
                finally {
                    helper.close();
                }
            }
        });
    }

    private static String[] COLUMNS = new String[]{_ID, DATE};
    private static int[] VIEWS = {R.id.txt_id, R.id.txt_date};

    private Cursor getFeeling() {
        SQLiteDatabase db = helper.getReadableDatabase();
        // Cursor cursor = db.query(TABLE_NAME_CONCLUDE, COLUMNS, null, null, null, null, null);
        String sql = "SELECT _id, DATE(Date) AS Date FROM " + TABLE_NAME_FEELING + " GROUP BY DATE(" + TABLE_NAME_FEELING + ".Date)";
        //String sql = "SELECT _id, DATE(Date) AS Date FROM " + TABLE_NAME_FEELING ;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    private void showFeeling(final Cursor cursor) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.feeling_item, cursor, COLUMNS, VIEWS);

        ListView list = (ListView)findViewById(android.R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowFeelingActivity.this, ShowFeelingDetailActivity.class);
                intent.putExtra("position", position + 1);
                String temp = cursor.getString(cursor.getColumnIndex("Date"));
                intent.putExtra("Date", temp);
                startActivity(intent);
            }
        });
    }

    private Cursor getFeelingByDate(String d){
        SQLiteDatabase db = helper.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
        String sql = "SELECT _id, DATE(Date) AS Date FROM " + TABLE_NAME_FEELING + " WHERE DATE(Date) = '" + d + "' GROUP BY DATE(Date)";
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
            Intent homeIntent = new Intent(ShowFeelingActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(ShowFeelingActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
