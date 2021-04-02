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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import static com.example.kidneyphrs.FeelingContent.BACKACHE;
import static com.example.kidneyphrs.FeelingContent.DIARRHEA;
import static com.example.kidneyphrs.FeelingContent.DRYSKIN;
import static com.example.kidneyphrs.FeelingContent.FINGERNUMBNESS;
import static com.example.kidneyphrs.FeelingContent.HEADACHE;
import static com.example.kidneyphrs.FeelingContent.ITCHSKIN;
import static com.example.kidneyphrs.FeelingContent.MOREFEELING;
import static com.example.kidneyphrs.FeelingContent.RASHSKIN;
import static com.example.kidneyphrs.FeelingContent.SLEEPING;
import static com.example.kidneyphrs.FeelingContent.SWELLING;
import static com.example.kidneyphrs.FeelingContent.TABLE_NAME_FEELING;
import static com.example.kidneyphrs.FeelingContent.TIRED;
import static com.example.kidneyphrs.FeelingContent.URINARYDISORDER;
import static com.example.kidneyphrs.FeelingContent.VOMIT;
public class AddFeelingActivity extends AppCompatActivity {

    private Button btn_save;
    private ImageButton btn_Emergency;

    private EditText txtf_morefeeling;

    private CheckBox checkBox_sleeping;
    private CheckBox checkBox_headache;
    private CheckBox checkBox_vomit;
    private CheckBox checkBox_backache;
    private CheckBox checkBox_itch_skin;
    private CheckBox checkBox_dry_skin;
    private CheckBox checkBox_rash_skin;
    private CheckBox checkBox_tired;
    private CheckBox checkBox_diarrhea;
    private CheckBox checkBox_swelling;
    private CheckBox checkBox_urinary_disorders;
    private CheckBox checkBox_finger_numbness;

    private String morefeeling;
    private int sleeping;
    private int headache;
    private int vomit;
    private int backache;
    private int itch_skin;
    private int dry_skin;
    private int rash_skin;
    private int tired;
    private int diarrhea;
    private int swelling;
    private int urinary_disorders;
    private int finger_numbness;

    private FeelingHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feeling);

        helper = new FeelingHelper(AddFeelingActivity.this);

        txtf_morefeeling = (EditText) findViewById(R.id.txtf_morefeeling);

        //Button save feeling information
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                morefeeling = txtf_morefeeling.getText().toString();

                addFeeling(sleeping, headache, vomit, backache, itch_skin, dry_skin, rash_skin, tired, diarrhea, swelling, urinary_disorders, finger_numbness, morefeeling);

                Intent intent = new Intent(AddFeelingActivity.this, FeelingMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addListenerOnButton();
    }

    private void addFeeling(Integer sleeping, Integer headache, Integer vomit, Integer backache, Integer itch_skin, Integer dry_skin, Integer rash_skin, Integer tired, Integer diarrhea, Integer swelling, Integer urinary_disorders, Integer finger_numbness, String morefeeling){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SLEEPING, sleeping);
        values.put(HEADACHE, headache);
        values.put(VOMIT, vomit);
        values.put(BACKACHE, backache);
        values.put(ITCHSKIN , itch_skin);
        values.put(DRYSKIN, dry_skin);
        values.put(RASHSKIN, rash_skin);
        values.put(TIRED, tired);
        values.put(DIARRHEA, diarrhea);
        values.put(SWELLING, swelling);
        values.put(URINARYDISORDER, urinary_disorders);
        values.put(FINGERNUMBNESS, finger_numbness);
        values.put(MOREFEELING, morefeeling);
        db.insert(TABLE_NAME_FEELING, null, values);
    }

    public void addListenerOnButton() {

        checkBox_sleeping = (CheckBox) findViewById(R.id.checkBox_sleeping);
        checkBox_sleeping.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if (((CheckBox) v).isChecked()) {
                    sleeping = 1;
                }
            }
        });

        checkBox_headache = (CheckBox) findViewById(R.id.checkBox_headache);
        checkBox_headache.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if (((CheckBox) v).isChecked()) {
                    headache = 1;
                }
            }
        });

        checkBox_vomit = (CheckBox) findViewById(R.id.checkBox_vomit);
        checkBox_vomit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if (((CheckBox) v).isChecked()) {
                    vomit = 1;
                }
            }
        });

        checkBox_backache = (CheckBox) findViewById(R.id.checkBox_backache);
        checkBox_backache.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if (((CheckBox) v).isChecked()) {
                    backache = 1;
                }
            }
        });

        checkBox_itch_skin = (CheckBox) findViewById(R.id.checkBox_itchskin);
        checkBox_itch_skin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if (((CheckBox) v).isChecked()) {
                    itch_skin = 1;
                }
            }
        });

        checkBox_dry_skin = (CheckBox) findViewById(R.id.checkBox_dryskin);
        checkBox_dry_skin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if (((CheckBox) v).isChecked()) {
                    dry_skin = 1;
                }
            }
        });

        checkBox_rash_skin = (CheckBox) findViewById(R.id.checkBox_rashskin);
        checkBox_rash_skin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if (((CheckBox) v).isChecked()) {
                    rash_skin = 1;
                }
            }
        });

        checkBox_tired = (CheckBox) findViewById(R.id.checkBox_tired);
        checkBox_tired.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if (((CheckBox) v).isChecked()) {
                    tired = 1;
                }
            }
        });

        checkBox_diarrhea = (CheckBox) findViewById(R.id.checkBox_diarrhea);
        checkBox_diarrhea.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if (((CheckBox) v).isChecked()) {
                    diarrhea = 1;
                }
            }
        });

        checkBox_swelling = (CheckBox) findViewById(R.id.checkBox_swelling);
        checkBox_swelling.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if (((CheckBox) v).isChecked()) {
                    swelling = 1;
                }
            }
        });

        checkBox_urinary_disorders = (CheckBox) findViewById(R.id.checkBox_urinary_disorders);
        checkBox_urinary_disorders.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if (((CheckBox) v).isChecked()) {
                    urinary_disorders = 1;
                }
            }
        });

        checkBox_finger_numbness = (CheckBox) findViewById(R.id.checkBox_finger_numbness);
        checkBox_finger_numbness.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if (((CheckBox) v).isChecked()) {
                    finger_numbness = 1;
                }
            }
        });

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
        if (id == R.id.action_home) {
            Intent intent = new Intent(AddFeelingActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
