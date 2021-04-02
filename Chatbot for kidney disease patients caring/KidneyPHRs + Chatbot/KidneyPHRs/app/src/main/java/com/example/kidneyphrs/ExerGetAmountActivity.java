package com.example.kidneyphrs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.kidneyphrs.OtherExerContent.AMOUNT;
import static com.example.kidneyphrs.OtherExerContent.EXERCISE_NAME;
import static com.example.kidneyphrs.OtherExerContent.TABLE_NAME;
import static com.example.kidneyphrs.OtherExerContent.TIME_SEC;

public class ExerGetAmountActivity extends AppCompatActivity {

    private EditText txtf_exerAmount;
    private Button btn_save;

    private int amount;
    private int position;
    private int timeInSecs;
    private String name;

    private OtherExerResultHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer_get_amount);

        helper = new OtherExerResultHelper(ExerGetAmountActivity.this);

        //get exercise name
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        position = bundle.getInt("position");
        timeInSecs = bundle.getInt("time");

        txtf_exerAmount = (EditText)findViewById(R.id.txtf_exerAmount);

        //save
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to DB
                amount = Integer.parseInt(txtf_exerAmount.getText().toString());
                addExerResult(name, timeInSecs, amount);

                //go to result
                Intent intent = new Intent(ExerGetAmountActivity.this, ExerResultActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("time", timeInSecs);
                intent.putExtra("amount", amount);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addExerResult(String name ,int time, int amount){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXERCISE_NAME, name);
        values.put(AMOUNT, amount);
        values.put(TIME_SEC, time);
        db.insertOrThrow(TABLE_NAME, null, values);
    }
}
