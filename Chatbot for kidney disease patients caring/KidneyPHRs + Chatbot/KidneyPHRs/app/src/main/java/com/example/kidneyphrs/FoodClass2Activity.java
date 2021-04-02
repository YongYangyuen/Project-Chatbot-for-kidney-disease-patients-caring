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
import static com.example.kidneyphrs.FoodContent.CALORY;
import static com.example.kidneyphrs.FoodContent.CATEGORY;
import static com.example.kidneyphrs.FoodContent.FOOD_NAME;

public class FoodClass2Activity extends AppCompatActivity {

    private FoodHelper helper;
    private SimpleCursorAdapter adapter;
    private String filter;
    private int getID;

    EditText txtfill_foodSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_class_1);

        txtfill_foodSearch = (EditText)findViewById(R.id.txtfill_foodSearch);

        filter = "";

        helper = new FoodHelper(this);

        try {
            Cursor cursor = getFood();
            showFood(cursor);
        }
        finally {
            helper.close();
        }

        txtfill_foodSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // adapter.getFilter().filter(s);
                filter = s.toString();
                try {
                    Cursor cursor = getFood();
                    showFood(cursor);
                }
                finally {
                    helper.close();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
            Intent homeIntent = new Intent(FoodClass2Activity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(FoodClass2Activity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private static String[] COLUMNS = new String[]{_ID,FOOD_NAME, CALORY};
    private static int[] VIEWS = {R.id.txt_id,R.id.txt_foodName, R.id.txt_calory};

    private Cursor getFood() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(FoodContent.TABLE_NAME, COLUMNS, CATEGORY + "== 2 AND " + FOOD_NAME + " LIKE '%" + filter + "%'", null, null, null, null);

        return cursor;
    }

    private void showFood(final Cursor cursor) {
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.food_item, cursor, COLUMNS, VIEWS);
        adapter = new SimpleCursorAdapter(this, R.layout.food_item, cursor, COLUMNS, VIEWS);
        //setListAdapter(adapter);
//        ListView list = getListView();
        ListView list = (ListView)findViewById(android.R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FoodClass2Activity.this, FoodTypeActivity.class);
                intent.putExtra("backTo", 1);
                getID = cursor.getInt(cursor.getColumnIndex("_id"));
                intent.putExtra("id", getID);
                startActivity(intent);
            }
        });

    }

}
