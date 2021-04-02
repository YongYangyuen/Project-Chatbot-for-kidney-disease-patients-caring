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
import static com.example.kidneyphrs.FeelingContent.BACKACHE;
import static com.example.kidneyphrs.FeelingContent.DIARRHEA;
import static com.example.kidneyphrs.FeelingContent.DRYSKIN;
import static com.example.kidneyphrs.FeelingContent.FINGERNUMBNESS;
import static com.example.kidneyphrs.FeelingContent.HEADACHE;
import static com.example.kidneyphrs.FeelingContent.ITCHSKIN;
import static com.example.kidneyphrs.FeelingContent.MOREFEELING;
import static com.example.kidneyphrs.FeelingContent.RASHSKIN;
import static com.example.kidneyphrs.FeelingContent.SLEEPING;
import static com.example.kidneyphrs.FeelingContent.TABLE_NAME_FEELING;
import static com.example.kidneyphrs.FeelingContent.TIRED;
import static com.example.kidneyphrs.FeelingContent.URINARYDISORDER;
import static com.example.kidneyphrs.FeelingContent.VOMIT;

public class ShowFeelingDetailActivity extends AppCompatActivity {

    private Button btn_mainmenu;

    private String Date;

    private FeelingHelper helper;

    private TextView txt_feeling;
    private TextView txt_temp;

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

    StringBuilder Feeling = new StringBuilder("");
    int temp_sleeping = 0;
    int temp_headache = 0;
    int temp_vomit = 0;
    int temp_backache = 0;
    int temp_itch_skin = 0;
    int temp_dry_skin = 0;
    int temp_rash_skin = 0;
    int temp_tired = 0;
    int temp_diarrhea = 0;
    int temp_swelling = 0;
    int temp_urinary_disorders = 0;
    int temp_finger_numbness = 0;
    int temp_morefeeling = 0;
    StringBuilder moreFeeling = new StringBuilder("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feeling_detail);

        // Button to Main
        btn_mainmenu = (Button) findViewById(R.id.btn_mainmenu);
        btn_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowFeelingDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();

        Date = bundle.getString("Date");
//        txt_temp = (TextView)findViewById(R.id.txt_temp);
//        txt_temp.setText(Date);

        txt_feeling = (TextView)findViewById(R.id.txt_feeling);

        helper = new FeelingHelper(this);

        try {
            Cursor cursor = getFeeling();
            showFeeling(cursor);
        } finally {
            helper.close();
        }
    }

    private static String[] COLUMNS = new String[]{_ID, SLEEPING, HEADACHE, VOMIT, BACKACHE, ITCHSKIN, DRYSKIN, RASHSKIN, TIRED, DIARRHEA,  URINARYDISORDER, FINGERNUMBNESS, MOREFEELING} ;
    private Cursor getFeeling(){
        SQLiteDatabase db = helper.getReadableDatabase();
        //String sql = "SELECT _id, Sleeping, Headache, Vomit, Backache, Itchskin, Dryskin, Rashskin, Tired, Diarrhea, Swelling, Urinarydisorder, Fingernumbness, Morefeeling  FROM " + TABLE_NAME_FEELING + " WHERE DATE(Date)= '" + Date + "' GROUP BY DATE(" + TABLE_NAME_FEELING + ".Date)";
        String sql = "SELECT _id, Sleeping, Headache, Vomit, Backache, Itchskin, Dryskin, Rashskin, Tired, Diarrhea, Swelling, Urinarydisorder, Fingernumbness, Morefeeling  FROM " + TABLE_NAME_FEELING + " WHERE DATE(Date)= '" + Date + "'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    private void showFeeling(Cursor cursor) {

        while (cursor.moveToNext()) {

            sleeping = cursor.getInt(1);
            headache = cursor.getInt(2);
            vomit = cursor.getInt(3);
            backache = cursor.getInt(4);
            itch_skin = cursor.getInt(5);
            dry_skin = cursor.getInt(6);
            rash_skin = cursor.getInt(7);
            tired = cursor.getInt(8);
            diarrhea = cursor.getInt(9);
            swelling = cursor.getInt(10);
            urinary_disorders = cursor.getInt(11);
            finger_numbness = cursor.getInt(12);
            morefeeling = cursor.getString(13);

            if(sleeping == 1){
                //Feeling.append("นอนไม่ค่อยหลับ  ");
                temp_sleeping += 1;
            }
            if(headache == 1){
                //Feeling.append("ปวดหัว มึนหัว  ");
                temp_headache += 1;
            }
            if(vomit == 1){
                //Feeling.append("คลื่นไส้ อาเจียน ");
                temp_vomit += 1;
            }
            if(backache == 1){
                //Feeling.append("ปวดหลังหรือบั้นเอว  ");
                temp_backache += 1;
            }
            if(itch_skin == 1){
                //Feeling.append("คันตามผิวหนัง  ");
                temp_itch_skin += 1;
            }
            if(dry_skin == 1){
                //Feeling.append("ผิวแห้ง ผิวลอก  ");
                temp_dry_skin += 1;
            }
            if(rash_skin == 1){
                //Feeling.append("ผิวหนังขึ้นผื่น  ");
                temp_rash_skin += 1;
            }
            if(tired == 1){
                //Feeling.append("เหนื่อยง่าย เพลีย  ");
                temp_tired += 1;
            }
            if(diarrhea == 1){
                //Feeling.append("ขับถ่ายผิดปกติ  ");
                temp_diarrhea += 1;
            }
            if(swelling == 1){
                //Feeling.append("ร่างกายบวมขึ้น  ");
                temp_swelling += 1;
            }
            if(urinary_disorders == 1){
                //Feeling.append("ปัสสาวะผิดปกติ  ");
                temp_urinary_disorders += 1;
            }
            if(finger_numbness == 1){
                //Feeling.append("ชาตามปลายนิ้วมือ  ");
                temp_finger_numbness += 1;
            }
            moreFeeling.append(morefeeling + "  ");
        }

        if(temp_sleeping > 0){
            Feeling.append("นอนไม่ค่อยหลับ  ");
        }
        if(temp_headache > 0){
            Feeling.append("ปวดหัว มึนหัว  ");
        }
        if(temp_vomit > 0){
            Feeling.append("คลื่นไส้ อาเจียน ");
        }
        if(temp_backache > 0){
            Feeling.append("ปวดหลังหรือบั้นเอว  ");
        }
        if(temp_itch_skin > 0){
            Feeling.append("คันตามผิวหนัง  ");
        }
        if(temp_dry_skin > 0){
            Feeling.append("ผิวแห้ง ผิวลอก  ");
        }
        if(temp_rash_skin > 0){
            Feeling.append("ผิวหนังขึ้นผื่น  ");
        }
        if(temp_tired > 0){
            Feeling.append("เหนื่อยง่าย เพลีย  ");
        }
        if(temp_diarrhea > 0){
            Feeling.append("ขับถ่ายผิดปกติ  ");
        }
        if(temp_swelling > 0){
            Feeling.append("ร่างกายบวมขึ้น  ");
        }
        if(temp_urinary_disorders > 0){
            Feeling.append("ปัสสาวะผิดปกติ  ");
        }
        if(temp_finger_numbness > 0){
            Feeling.append("ปัสสาวะผิดปกติ  ");
        }
        if(temp_morefeeling > 0){
            Feeling.append("ชาตามปลายนิ้วมือ  ");
        }

        Feeling.append(moreFeeling);
        txt_feeling = (TextView)findViewById(R.id.txt_feeling);
        txt_feeling.setText(Feeling);

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
            Intent homeIntent = new Intent(ShowFeelingDetailActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(ShowFeelingDetailActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
