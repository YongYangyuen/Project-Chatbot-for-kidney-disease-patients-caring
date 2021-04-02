package com.example.kidneyphrs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.PersonalInfoContent.PHONE;
import static com.example.kidneyphrs.PersonalInfoContent.TABLE_NAME_INFOR;

public class EmergencyActivity extends AppCompatActivity {

    private Button calling;

    private TextView txt_phonecall;

    private String phone;

    Intent callIntent;

    private PersonalInfoHelper helper;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        calling = (Button) findViewById(R.id.calling);
        calling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent = new Intent(Intent.ACTION_CALL);
//                intent.setData(Uri.parse("tel:" +number));
//                uri = Uri.parse(phone);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
            }
        });


        helper = new PersonalInfoHelper(this);

        try {
            Cursor cursor = getInformation();
            showInformation(cursor);
        } finally {
            helper.close();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private static String[] COLUMNS = new String[]{_ID, PHONE};

    private Cursor getInformation() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT _id, Phone FROM " + TABLE_NAME_INFOR;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    private void showInformation(Cursor cursor) {

        StringBuilder Phone = new StringBuilder("");

        while (cursor.moveToNext()) {
            phone = cursor.getString(1);
            Phone.append(phone);
        }

        txt_phonecall = (TextView) findViewById(R.id.txt_phonecall);
        txt_phonecall.setText(phone);

    }
    /*------------------ HOME MENU ------------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent homeIntent = new Intent(EmergencyActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
