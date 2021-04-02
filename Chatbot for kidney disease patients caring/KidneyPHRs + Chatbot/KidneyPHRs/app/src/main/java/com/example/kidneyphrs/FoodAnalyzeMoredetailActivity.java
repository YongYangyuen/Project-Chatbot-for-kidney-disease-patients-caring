package com.example.kidneyphrs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.FoodConcludeContent.FOOD_NAME_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.MEAL;
import static com.example.kidneyphrs.FoodConcludeContent.TABLE_NAME_CONCLUDE;

public class FoodAnalyzeMoredetailActivity extends AppCompatActivity {

    private String Date;

    private FoodConcludeHelper helper;

    private int getID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_analyze_moredetail);

        Bundle bundle = getIntent().getExtras();
        Date = bundle.getString("Date");

        helper = new FoodConcludeHelper(this);

        try {
            Cursor cursor = getFood();
            showFood(cursor);
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
            Intent homeIntent = new Intent(FoodAnalyzeMoredetailActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(FoodAnalyzeMoredetailActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private static String[] COLUMNS = new String[]{_ID, FOOD_NAME_CONCLUDE, MEAL};

    private static int[] VIEWS = {R.id.txt_id, R.id.txt_foodName, R.id.txt_meal};

    private void showFood(final Cursor cursor) {

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.food_moredetail_item, cursor, COLUMNS, VIEWS);

        ListView list = (ListView)findViewById(android.R.id.list);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FoodAnalyzeMoredetailActivity.this, FoodAnalyzeDetailEndActivity.class);
                intent.putExtra("Date", Date);
                getID = cursor.getInt(cursor.getColumnIndex("_id"));
                intent.putExtra("id", getID);
                startActivity(intent);
            }
        });
    }

    private Cursor getFood() {
        SQLiteDatabase db = helper.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_NAME_CONCLUDE, COLUMNS, null, null, null, null, null);
        String sql = "SELECT _id, FoodName, Meal FROM " + TABLE_NAME_CONCLUDE + " WHERE DATE(Date) = '" + Date +"'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
}
