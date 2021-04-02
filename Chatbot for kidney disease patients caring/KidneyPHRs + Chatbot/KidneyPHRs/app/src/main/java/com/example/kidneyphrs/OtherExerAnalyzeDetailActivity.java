package com.example.kidneyphrs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.OtherExerContent.AMOUNT;
import static com.example.kidneyphrs.OtherExerContent.DATE;
import static com.example.kidneyphrs.OtherExerContent.EXERCISE_NAME;
import static com.example.kidneyphrs.OtherExerContent.TABLE_NAME;
import static com.example.kidneyphrs.OtherExerContent.TIME_SEC;

public class OtherExerAnalyzeDetailActivity extends AppCompatActivity {

    private int getID;

    private TextView txt_exerciseName;
    private TextView txt_date;
    private TextView txt_time;
    private TextView txt_amount;
    private Button btn_mainmenu;

    private OtherExerResultHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_exer_analyze_detail);

        // Button to Main
        btn_mainmenu = (Button) findViewById(R.id.btn_mainmenu);
        btn_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(OtherExerAnalyzeDetailActivity.this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

        });

        Bundle bundle = getIntent().getExtras();
        getID = bundle.getInt("id");

        txt_exerciseName = (TextView)findViewById(R.id.txt_exerciseName);
        txt_date = (TextView)findViewById(R.id.txt_date);
        txt_time = (TextView)findViewById(R.id.txt_time);
        txt_amount = (TextView)findViewById(R.id.txt_amount);

        helper = new OtherExerResultHelper(this);

        try{
            Cursor cursor = getExerInfo();
            show(cursor);
        }
        finally {
            helper.close();
        }
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
            Intent homeIntent = new Intent(OtherExerAnalyzeDetailActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(OtherExerAnalyzeDetailActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private static String[] COLUMNS = new String[]{_ID, EXERCISE_NAME, DATE, TIME_SEC, AMOUNT};

    private Cursor getExerInfo(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT _id, ExerciseName, DATE(date) AS date, timeInSec, amount FROM " + TABLE_NAME + " WHERE _id = " + getID;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    private void show(final Cursor cursor){
        StringBuilder name = new StringBuilder("");
        StringBuilder date = new StringBuilder("");
        StringBuilder time = new StringBuilder("");
        StringBuilder amount = new StringBuilder("");

        while (cursor.moveToNext()){
            name.append(cursor.getString(1));
            date.append(cursor.getString(2));
            time.append("" + String.format("%02d", cursor.getInt(3)/60/60) + ":" + String.format("%02d", (cursor.getInt(3)/60)%60) + ":" + String.format("%02d", cursor.getInt(3)%60));
            amount.append("" + cursor.getInt(4));
        }
        txt_exerciseName.setText(name);
        txt_date.setText(date);
        txt_time.setText(time);
        txt_amount.setText(amount);
    }
}
