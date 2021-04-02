package com.example.kidneyphrs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.kidneyphrs.PersonalInfoContent.BIRTHDAY;
import static com.example.kidneyphrs.PersonalInfoContent.GENDER;
import static com.example.kidneyphrs.PersonalInfoContent.HEIGHT;
import static com.example.kidneyphrs.PersonalInfoContent.PHONE;
import static com.example.kidneyphrs.PersonalInfoContent.TABLE_NAME_INFOR;
import static com.example.kidneyphrs.PersonalInfoContent.USERNAME;
import static com.example.kidneyphrs.PersonalInfoContent.WEIGHT;

public class EditWeightActivity extends AppCompatActivity {

    private Button btn_save;

    private EditText txtf_editweight;

    private int editweight;

    private PersonalInfoHelper helper;

    private String username;
    private int height;
    private String phone;
    private String sex;
    private String birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_weight);

        helper = new PersonalInfoHelper(EditWeightActivity.this);

        txtf_editweight = (EditText)findViewById(R.id.txtf_editweight);

        txtf_editweight.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strEnteredVal = txtf_editweight.getText().toString();

                if (!strEnteredVal.equals("")) {
                    int num = Integer.parseInt(strEnteredVal);
                    if (num <= 200) {
                        editweight = num;
                        txtf_editweight.setTextColor(Color.parseColor("#000000"));
                    } else {
                        txtf_editweight.setTextColor(Color.parseColor("#CC3333"));
                        Toast.makeText(getApplicationContext(), "กรุณาป้อนค่าให้ถูกต้อง", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        birth = bundle.getString("birth");
        sex = bundle.getString("gender");
        height = bundle.getInt("height");
        phone = bundle.getString("phone");

        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to DB

//                editweight = Integer.parseInt(txtf_editweight.getText().toString());

                addPersonalInfo(username, birth, sex, editweight, height, phone);

                Intent intent = new Intent(EditWeightActivity.this, PersonalInfoActivity.class);
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
            Intent homeIntent = new Intent(EditWeightActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private void addPersonalInfo(String username, String birth, String sex, Integer editweight, Integer height, String phone){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(BIRTHDAY, birth);
        values.put(GENDER, sex);
        values.put(WEIGHT, editweight);
        values.put(HEIGHT, height);
        values.put(PHONE, phone);
        db.insert(TABLE_NAME_INFOR, null, values);
    }
}
