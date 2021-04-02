package com.example.kidneyphrs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

public class EditHeightActivity extends AppCompatActivity {

    private Button btn_save;

    private EditText txtf_editheight;

    private int editheight;

    private String username;
    private int weight;
    private String phone;
    private String sex;
    private String birth;

    private PersonalInfoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_height);

        helper = new PersonalInfoHelper(EditHeightActivity.this);

        txtf_editheight = (EditText)findViewById(R.id.txtf_editheight);

        txtf_editheight.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strEnteredVal = txtf_editheight.getText().toString();

                if (!strEnteredVal.equals("")) {
                    int num = Integer.parseInt(strEnteredVal);
                    if (num <= 250) {
                        editheight = num;
                        txtf_editheight.setTextColor(Color.parseColor("#000000"));
                    } else {
                        txtf_editheight.setTextColor(Color.parseColor("#CC3333"));
                        Toast.makeText(getApplicationContext(), "กรุณาป้อนค่าให้ถูกต้อง", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        birth = bundle.getString("birth");
        sex = bundle.getString("gender");
        weight = bundle.getInt("weight");
        phone = bundle.getString("phone");

        //Button to PersonalInfo
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to DB

                editheight = Integer.parseInt(txtf_editheight.getText().toString());

                addPersonalInfo(username, birth, sex, weight, editheight, phone);

                Intent intent = new Intent(EditHeightActivity.this, PersonalInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addPersonalInfo(String username, String birth, String sex, Integer weight, Integer editheight, String phone){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(BIRTHDAY, birth);
        values.put(GENDER, sex);
        values.put(WEIGHT, weight);
        values.put(HEIGHT, editheight);
        values.put(PHONE, phone);
        db.insert(TABLE_NAME_INFOR, null, values);
    }
}
