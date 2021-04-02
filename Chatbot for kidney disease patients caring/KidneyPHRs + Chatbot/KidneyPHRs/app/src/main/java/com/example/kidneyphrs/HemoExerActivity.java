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
import android.widget.TextView;

import static com.example.kidneyphrs.HemoContent.AMOUNT;
import static com.example.kidneyphrs.HemoContent.TABLE_NAME;


public class HemoExerActivity extends AppCompatActivity {

    private Button btn_save;
    private String temp;
    private Button btn_increseAmount;
    private TextView txt_amount;
    private static int count = 0;
    private int oneSet = 5;
    private Button btn_editAmount;

    private HemoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hemo_exer);

        txt_amount = (TextView)findViewById(R.id.txt_amount);

        temp = Integer.toString(count);
        txt_amount.setText(temp);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            oneSet = bundle.getInt("countSet");
        }

        btn_editAmount = (Button)findViewById(R.id.btn_editAmount);
        btn_editAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HemoExerActivity.this, EditHemoCountActivity.class);
                startActivity(intent);
            }
        });

        btn_increseAmount = (Button)findViewById(R.id.btn_increseAmount);
        btn_increseAmount.setText("แตะเพื่อ +" + oneSet);
        btn_increseAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += oneSet;
                temp = Integer.toString(count);
                txt_amount.setText(temp);
            }
        });

        helper = new HemoHelper(HemoExerActivity.this);

        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save to database
                addHemoExer(count);

                //go to HemoResult
                Intent intent = new Intent(HemoExerActivity.this, HemoResultActivity.class);
                intent.putExtra("amount", count);
                startActivity(intent);
                finish();
            }
        });

    }

    private void addHemoExer(int amount){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AMOUNT, amount);
        db.insertOrThrow(TABLE_NAME, null, values);
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
            Intent homeIntent = new Intent(HemoExerActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(HemoExerActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/


}
