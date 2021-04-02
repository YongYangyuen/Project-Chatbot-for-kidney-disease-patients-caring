package com.example.kidneyphrs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.kidneyphrs.PersonalInfoContent.BIRTHDAY;
import static com.example.kidneyphrs.PersonalInfoContent.GENDER;
import static com.example.kidneyphrs.PersonalInfoContent.HEIGHT;
import static com.example.kidneyphrs.PersonalInfoContent.PHONE;
import static com.example.kidneyphrs.PersonalInfoContent.TABLE_NAME_INFOR;
import static com.example.kidneyphrs.PersonalInfoContent.USERNAME;
import static com.example.kidneyphrs.PersonalInfoContent.WEIGHT;
public class EditPhoneActivity extends AppCompatActivity {

    private Button btn_save;

    private EditText txtf_editphone;

    private String editphone;

    private String username;
    private int weight;
    private String sex;
    private int height;
    private String birth;

    private PersonalInfoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone);

        helper = new PersonalInfoHelper(EditPhoneActivity.this);

        txtf_editphone = (EditText)findViewById(R.id.txtf_editphone);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        birth = bundle.getString("birth");
        sex = bundle.getString("gender");
        weight = bundle.getInt("weight");
        height = bundle.getInt("height");

        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to DB

                editphone = txtf_editphone.getText().toString();

                addPersonalInfo(username, birth, sex, weight, height, editphone);

                Intent intent = new Intent(EditPhoneActivity.this, PersonalInfoActivity.class);
                startActivity(intent);
            }
        });
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
            Intent homeIntent = new Intent(EditPhoneActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private void addPersonalInfo(String username, String birth, String sex, Integer weight, Integer height, String editphone){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(BIRTHDAY, birth);
        values.put(GENDER, sex);
        values.put(WEIGHT, weight);
        values.put(HEIGHT, height);
        values.put(PHONE, editphone);
        db.insert(TABLE_NAME_INFOR, null, values);
    }
}
