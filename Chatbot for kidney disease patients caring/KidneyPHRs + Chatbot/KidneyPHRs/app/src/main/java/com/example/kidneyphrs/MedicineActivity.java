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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.MedicineInfoContent.MEDNAME;

public class MedicineActivity extends AppCompatActivity {

    private Button btn_addMedicine;
    private EditText txtf_drugSearch;

    private MedicineInfoHelper helper;
    private int getID;
    private String filter;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        txtf_drugSearch = (EditText)findViewById(R.id.txtf_drugSearch);

        filter = "";

        helper = new MedicineInfoHelper(this);

        try {
            Cursor cursor = getMedicine();
            showMedicine(cursor);
        }
        finally {
            helper.close();
        }

        txtf_drugSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // adapter.getFilter().filter(s);
                filter = s.toString();
                try {
                    Cursor cursor = getMedicine();
                    showMedicine(cursor);
                }
                finally {
                    helper.close();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Button to add medicine
        btn_addMedicine = (Button)findViewById(R.id.btn_addMedicine);
        btn_addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineActivity.this, AddMedicineActivity.class);
                startActivity(intent);
            }
        });

    }

    private static String[] COLUMNS = new String[]{_ID, MEDNAME};
    private static int[] VIEWS = {R.id.txt_id, R.id.txt_medName};

    private void showMedicine(final Cursor cursor) {
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.medicine_item, cursor, COLUMNS, VIEWS);
        adapter = new SimpleCursorAdapter(this, R.layout.medicine_item, cursor, COLUMNS, VIEWS);

        ListView list = (ListView)findViewById(android.R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MedicineActivity.this, MedicineDetailActivity.class);
                getID = cursor.getInt(cursor.getColumnIndex("_id"));
                intent.putExtra("id", getID);
                startActivity(intent);
            }
        });
    }

    private Cursor getMedicine() {
        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME_CONCLUDE, COLUMNS, null, null, null, null, null);
        Cursor cursor = db.query(MedicineInfoContent.TABLE_NAME_MED, COLUMNS, MEDNAME + " LIKE '%" + filter + "%'", null, null, null, null);
//        String sql = "SELECT _id, Medname FROM " + TABLE_NAME_MED + " LIKE '%" + filter + "%'" ;
//        Cursor cursor = db.rawQuery(sql, null);
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
            Intent homeIntent = new Intent(MedicineActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(MedicineActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
