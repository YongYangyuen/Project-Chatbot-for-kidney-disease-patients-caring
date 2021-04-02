package com.example.kidneyphrs;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
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

public class MedicineDetailActivity extends AppCompatActivity {

    private Button btn_mainmenu;

    private TextView txt_medName;
    private TextView txt_medAmount;
    private TextView txt_medUnit;
    private TextView txt_Timemeal;
    private TextView txt_Meal;

    private String medname;
    private Integer amount;
    private String unit;
    private String timemeal;
    private Integer morning;
    private Integer noon;
    private Integer evening;
    private Integer beforebed;
    private String img_med;

    private int temp;

    Uri uri;

    private MedicineInfoHelper helper;

    private ImageView img_showmed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);

        // Button to Main
        btn_mainmenu = (Button) findViewById(R.id.btn_mainmenu);
        btn_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(MedicineDetailActivity.this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

        });

        helper = new MedicineInfoHelper(MedicineDetailActivity.this);

        img_showmed = (ImageView)findViewById(R.id.img_showmed);


        Bundle bundle = getIntent().getExtras();
        temp = bundle.getInt("id");

        helper = new MedicineInfoHelper(this);

        try {
            Cursor cursor = getMedicine(temp);
            showMedicine(cursor);
        } finally {
            helper.close();
        }

    }

    /*------------------ HOME MENU ------------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent homeIntent = new Intent(MedicineDetailActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }

        if (id == R.id.action_delete) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MedicineDetailActivity.this);

            // set title
            alertDialogBuilder.setTitle("ลบ");

            // set dialog message
            alertDialogBuilder
                    .setMessage("คุณต้องการลบยาใช่หรือไม่?")
                    .setCancelable(false)
                    .setPositiveButton("ลบ",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            deleteMed(temp);
                            Intent intent = new Intent(MedicineDetailActivity.this, MedicineActivity.class);
                            startActivity(intent);
                            finish();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("ยกเลิก",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.dismiss();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private static String[] COLUMNS = new String[]{_ID, MEDNAME, AMOUNT, UNIT, MORNING, NOON, EVENING, BEFOREBED, TIMEMEAL, IMGMED };
    private Cursor getMedicine(int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(MedicineInfoContent.TABLE_NAME_MED, COLUMNS, "_ID = " + id, null, null, null, null);
        return cursor;
    }

    private void showMedicine(Cursor cursor) {
        StringBuilder Medname = new StringBuilder("");
        StringBuilder Amount = new StringBuilder("");
        StringBuilder Unit = new StringBuilder("");
        StringBuilder Meal = new StringBuilder("");
        StringBuilder Timemeal = new StringBuilder("");
        StringBuilder Imgmed = new StringBuilder("");

        while (cursor.moveToNext()) {
            medname = cursor.getString(1);
            Medname.append(medname);
            amount = cursor.getInt(2);
            Amount.append(amount);
            unit = cursor.getString(3);
            Unit.append(unit);
            morning = cursor.getInt(4);
            noon = cursor.getInt(5);
            evening = cursor.getInt(6);
            beforebed = cursor.getInt(7);
            timemeal = cursor.getString(8);
            Timemeal.append(timemeal);
            img_med = cursor.getString(9);
            Imgmed.append(img_med);

            if(morning == 1){
                Meal.append("เช้า  ");
            }
            if(noon == 1){
                Meal.append("เที่ยง  ");
            }
            if(evening == 1){
                Meal.append("เย็น  ");
            }
            if(beforebed == 1){
                Meal.append("ก่อนนอน  ");
            }



        }

        txt_medName = (TextView)findViewById(R.id.txt_medName);
        txt_medAmount = (TextView)findViewById(R.id.txt_medAmount);
        txt_medUnit = (TextView)findViewById(R.id.txt_medUnit);
        txt_Meal = (TextView)findViewById(R.id.txt_Meal);
        txt_Timemeal = (TextView)findViewById(R.id.txt_Timemeal);


        txt_medName.setText(medname);
        txt_medAmount.setText(Integer.toString(amount));
        txt_medUnit.setText(unit);
        txt_Meal.setText(Meal);
        txt_Timemeal.setText(timemeal);
        uri = Uri.parse(img_med);
        img_showmed.setImageURI(uri);

    }

    private void deleteMed(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_MED + " WHERE " + _ID + " == '" + id + "'");
    }
}
