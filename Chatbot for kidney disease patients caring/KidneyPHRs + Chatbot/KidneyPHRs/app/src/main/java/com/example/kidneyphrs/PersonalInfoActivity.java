package com.example.kidneyphrs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.PersonalInfoContent.BIRTHDAY;
import static com.example.kidneyphrs.PersonalInfoContent.GENDER;
import static com.example.kidneyphrs.PersonalInfoContent.HEIGHT;
import static com.example.kidneyphrs.PersonalInfoContent.PHONE;
import static com.example.kidneyphrs.PersonalInfoContent.TABLE_NAME_INFOR;
import static com.example.kidneyphrs.PersonalInfoContent.USERNAME;
import static com.example.kidneyphrs.PersonalInfoContent.WEIGHT;

public class PersonalInfoActivity extends AppCompatActivity {

    private TextView txt_showusername;
    private TextView txt_showweight;
    private TextView txt_showheight;
    private TextView txt_showphone;
    private TextView txt_showgender;
    private TextView txt_showbirthday;
    private Button btn_editweight;
    private Button btn_editheight;
    private Button btn_editphone;

    private String username;
    private int weight;
    private int height;

    private String phone;
    private String gender;
    private String birth;

    private PersonalInfoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        helper = new PersonalInfoHelper(this);

        try {
            Cursor cursor = getInformation();
            showInformation(cursor);
        } finally {
            helper.close();
        }

//        Bundle bundle = getIntent().getExtras();
//        gender = bundle.getString("Gender");

        btn_editweight = (Button)findViewById(R.id.btn_editweight);
        btn_editweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalInfoActivity.this, EditWeightActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("birth", birth);
                intent.putExtra("gender", gender);
                intent.putExtra("height", height);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });

        btn_editheight = (Button)findViewById(R.id.btn_editheight);
        btn_editheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(PersonalInfoActivity.this, EditHeightActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("birth", birth);
                intent.putExtra("gender", gender);
                intent.putExtra("weight", weight);
                intent.putExtra("height", height);
                startActivity(intent);
            }
        });

        btn_editphone = (Button)findViewById(R.id.btn_editphone);
        btn_editphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalInfoActivity.this, EditPhoneActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("birth", birth);
                intent.putExtra("gender", gender);
                intent.putExtra("weight", weight);
                intent.putExtra("height", height);
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
            Intent homeIntent = new Intent(PersonalInfoActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(PersonalInfoActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private static String[] COLUMNS = new String[]{_ID, USERNAME, BIRTHDAY, GENDER, WEIGHT, HEIGHT, PHONE};
    private Cursor getInformation(){
        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.query(PersonalInfoContent.TABLE_NAME_INFOR, COLUMNS,  , null, null, null, null);
        String sql = "SELECT _id, Username, Birth, Gender, Weight, Height, Phone FROM " + TABLE_NAME_INFOR ;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    private void showInformation(Cursor cursor) {
        StringBuilder Username = new StringBuilder("");
        StringBuilder Birth = new StringBuilder("");
        StringBuilder Gender = new StringBuilder("");
        StringBuilder Weight = new StringBuilder("");
        StringBuilder Height = new StringBuilder("");
        StringBuilder Phone = new StringBuilder("");

        while (cursor.moveToNext()) {
            username = cursor.getString(1);
            Username.append(username);
            birth = cursor.getString(2);
            Birth.append(birth);
            gender = cursor.getString(3);
            Gender.append(gender);
            weight = cursor.getInt(4);
            Weight.append(weight);
            height = cursor.getInt(5);
            Height.append(height);
            phone = cursor.getString(6);
            Phone.append(phone);
        }

        txt_showusername = (TextView)findViewById(R.id.txt_showusername);
        txt_showbirthday = (TextView)findViewById(R.id.txt_showbirthday);
        txt_showgender = (TextView)findViewById(R.id.txt_showgender);
        txt_showweight = (TextView)findViewById(R.id.txt_showweight);
        txt_showheight = (TextView)findViewById(R.id.txt_showheight);
        txt_showphone = (TextView)findViewById(R.id.txt_showphone);

        txt_showusername.setText(username);
        txt_showbirthday.setText(birth);
        txt_showgender.setText(gender);
        txt_showweight.setText(Integer.toString(weight));
        txt_showheight.setText(Integer.toString(height));
        txt_showphone.setText(phone);
//        System.out.print(gender);
    }
}
