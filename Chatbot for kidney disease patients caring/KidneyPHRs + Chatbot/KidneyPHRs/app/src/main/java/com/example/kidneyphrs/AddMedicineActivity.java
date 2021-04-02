package com.example.kidneyphrs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import static com.example.kidneyphrs.MedicineInfoContent.AMOUNT;
import static com.example.kidneyphrs.MedicineInfoContent.BEFOREBED;
import static com.example.kidneyphrs.MedicineInfoContent.EVENING;
import static com.example.kidneyphrs.MedicineInfoContent.IMGMED;
import static com.example.kidneyphrs.MedicineInfoContent.MEDNAME;
import static com.example.kidneyphrs.MedicineInfoContent.MORNING;
import static com.example.kidneyphrs.MedicineInfoContent.NOON;
import static com.example.kidneyphrs.MedicineInfoContent.TABLE_NAME_MED;
import static com.example.kidneyphrs.MedicineInfoContent.TIMEMEAL;
import static com.example.kidneyphrs.MedicineInfoContent.UNIT;

public class AddMedicineActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_save;
    private EditText txtf_medName;
    private EditText txtf_medAmount;

    private Spinner medUnit_spinner;

    private RadioButton radio_beforeMeal;
    private RadioButton radio_afterMeal;

    private CheckBox checkBox_morning;
    private CheckBox checkBox_noon;
    private CheckBox checkBox_evening;
    private CheckBox checkBox_beforeBed;

    private String medname;

    private int medamount;
    private int morning;
    private int noon;
    private int evening;
    private int beforebed;

    private String timemeal = "";
    private String[] medUnit = {"เม็ด", "ช้อนโต๊ะ", "ช้อนชา", "ซอง"};
    private String select_medunit;

    private String img_med;

    private ImageView img_addmed;

    static final int GALLERY_INTENT = 2;

    Intent selectIntent;

    Uri uri;

    private MedicineInfoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        helper = new MedicineInfoHelper(AddMedicineActivity.this);

        img_addmed = (ImageView)findViewById(R.id.img_addmed);
        img_addmed.setOnClickListener(this);

        txtf_medName = (EditText) findViewById(R.id.txtf_medName);
        txtf_medAmount = (EditText)findViewById(R.id.txtf_medAmount);

        img_med = "null";

        medUnit_spinner = (Spinner)findViewById(R.id.medUnit_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, medUnit);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        medUnit_spinner.setAdapter(adapter);
        medUnit_spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                        int position = medUnit_spinner.getSelectedItemPosition();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }

                });

        //Button save medicine information (back to medicine main)
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                medname = txtf_medName.getText().toString();
                medamount = Integer.parseInt(txtf_medAmount.getText().toString());
                select_medunit = medUnit_spinner.getSelectedItem().toString();

                addMedicineInfo(medname, medamount, select_medunit, morning, noon, evening, beforebed, timemeal, img_med);

                Intent intent = new Intent(AddMedicineActivity.this, MedicineActivity.class);
                startActivity(intent);
            }
        });

        addListenerOnButton();
    }

    private void addMedicineInfo(String medname, Integer medamount, String select_medunit, Integer morning, Integer noon, Integer evening, Integer beforebed, String timemeal, String img_med){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MEDNAME, medname);
        values.put(AMOUNT, medamount);
        values.put(UNIT, select_medunit);
        values.put(MORNING, morning);
        values.put(NOON, noon);
        values.put(EVENING, evening);
        values.put(BEFOREBED, beforebed);
        values.put(TIMEMEAL, timemeal);
        values.put(IMGMED, img_med);
        db.insert(TABLE_NAME_MED, null, values);
    }

    public void addListenerOnButton() {

        radio_beforeMeal = (RadioButton) findViewById(R.id.radio_beforeMeal);
        radio_beforeMeal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                timemeal = "ก่อนอาหาร";
            }
        });

        radio_afterMeal = (RadioButton) findViewById(R.id.radio_afterMeal);
        radio_afterMeal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                timemeal = "หลังอาหาร";
            }
        });

        checkBox_morning = (CheckBox) findViewById(R.id.checkBox_morning);
        checkBox_morning.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if (((CheckBox) v).isChecked()) {
                    morning = 1;
                }
            }
        });

        checkBox_noon = (CheckBox) findViewById(R.id.checkBox_noon);
        checkBox_noon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if (((CheckBox) v).isChecked()) {
                    noon = 1;
                }
            }
        });

        checkBox_evening = (CheckBox) findViewById(R.id.checkBox_evening);
        checkBox_evening.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if (((CheckBox) v).isChecked()) {
                    evening = 1;
                }
            }
        });

        checkBox_beforeBed = (CheckBox) findViewById(R.id.checkBox_beforeBed);
        checkBox_beforeBed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if (((CheckBox) v).isChecked()) {
                    beforebed = 1;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_addmed:
                Intent intent2 = new Intent(Intent.ACTION_PICK);
                intent2.setType("image/*");
                selectIntent = intent2;
                startActivityForResult(intent2,GALLERY_INTENT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            uri = data.getData();
            img_addmed.setImageURI(uri);

            img_med = uri.toString();
        }
    }
    /*------------------ EMERGENCY MENU ------------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_emergency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(AddMedicineActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
