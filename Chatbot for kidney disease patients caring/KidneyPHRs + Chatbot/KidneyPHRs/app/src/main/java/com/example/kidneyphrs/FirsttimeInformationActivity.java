package com.example.kidneyphrs;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.kidneyphrs.PersonalInfoContent.BIRTHDAY;
import static com.example.kidneyphrs.PersonalInfoContent.GENDER;
import static com.example.kidneyphrs.PersonalInfoContent.HEIGHT;
import static com.example.kidneyphrs.PersonalInfoContent.PHONE;
import static com.example.kidneyphrs.PersonalInfoContent.TABLE_NAME_INFOR;
import static com.example.kidneyphrs.PersonalInfoContent.USERNAME;
import static com.example.kidneyphrs.PersonalInfoContent.WEIGHT;

public class FirsttimeInformationActivity extends AppCompatActivity {

    private Button btn_save;
    private Button  btn_datePick;

    private RadioButton radioMale;
    private RadioButton radioFemale;

    private EditText txtf_username;
    private EditText txtf_weight;
    private EditText txtf_height;
    private EditText txtf_phone;

    private String username;
    private String phone;

    private String sex = "";

    private String birth;

    private int weight;
    private int height;

    private int pDay, pMonth, pYear;

    private PersonalInfoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsttime_information);

        helper = new PersonalInfoHelper(FirsttimeInformationActivity.this);

        txtf_username = (EditText) findViewById(R.id.txtf_username);
        txtf_weight = (EditText)findViewById(R.id.txtf_weight);
        txtf_height = (EditText)findViewById(R.id.txtf_height);
        txtf_phone = (EditText)findViewById(R.id.txtf_phone);

        txtf_weight.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strEnteredVal = txtf_weight.getText().toString();

                if (!strEnteredVal.equals("")) {
                    int num = Integer.parseInt(strEnteredVal);
                    if (num <= 200) {
                        weight = num;
                        txtf_weight.setTextColor(Color.parseColor("#000000"));
                    } else {
                        txtf_weight.setTextColor(Color.parseColor("#CC3333"));
                        Toast.makeText(getApplicationContext(), "กรุณาป้อนค่าให้ถูกต้อง", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txtf_height.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strEnteredVal = txtf_height.getText().toString();

                if (!strEnteredVal.equals("")) {
                    int num = Integer.parseInt(strEnteredVal);
                    if (num <= 250) {
                        height = num;
                        txtf_height.setTextColor(Color.parseColor("#000000"));
                    } else {
                        txtf_height.setTextColor(Color.parseColor("#CC3333"));
                        Toast.makeText(getApplicationContext(), "กรุณาป้อนค่าให้ถูกต้อง", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Button to PersonalInfo
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to DB
                username = txtf_username.getText().toString();
                weight = Integer.parseInt(txtf_weight.getText().toString());
                height = Integer.parseInt(txtf_height.getText().toString());
                phone = txtf_phone.getText().toString();
//                sex = "female";
                addPersonalInfo(username, birth, sex, weight, height, phone);

                Intent intent = new Intent(FirsttimeInformationActivity.this, MainActivity.class);
//                intent.putExtra("first_time", firstTime);
//                intent.putExtra("Gender", gender);
//                intent.putExtra("weight", weight);
//                intent.putExtra("height", height);
                startActivity(intent);
            }
        });

        addListenerOnButton();

        btn_datePick = (Button)findViewById(R.id.btn_datePick);
        btn_datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                pYear = c.get(Calendar.YEAR);
                pMonth = c.get(Calendar.MONTH);
                pDay = c.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(FirsttimeInformationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                birth = String.format("%02d", dayOfMonth) + " - " + String.format("%02d", monthOfYear + 1) + " - " + year ;

                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();
            }
        });
    }

    private void addPersonalInfo(String username, String birth, String sex, Integer weight, Integer height, String phone){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(BIRTHDAY, birth);
        values.put(GENDER, sex);
        values.put(WEIGHT, weight);
        values.put(HEIGHT, height);
        values.put(PHONE, phone);
        db.insert(TABLE_NAME_INFOR, null, values);
    }

    public void addListenerOnButton() {

        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioMale.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                sex = "ชาย";
            }
        });

        radioFemale = (RadioButton) findViewById(R.id.radioFemale);
        radioFemale.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sex = "หญิง";
            }
        });

    }
}
