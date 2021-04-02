package com.example.kidneyphrs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.ExerContent.CATAGORY;
import static com.example.kidneyphrs.ExerContent.EXERCISE_NAME;
import static com.example.kidneyphrs.ExerContent.TABLE_NAME;

public class ExerciseByTypeActivity extends AppCompatActivity {

    private TextView txt_exerName;
    private Button btn_start;
    private Button btn_finish;
    private Button btn_howTo;

    private int temp;
    private String name;

    private ExerHelper helper;

    private long startTime = 0L;
    private long timeInMillisec = 0L;
    private long timeSwapBuff = 0L;
    private long timeUpdate = 0L;
    private int t = 1;
    private int sec = 0;
    private int min = 0;
    private int hour = 0;
    private Handler handler;
    private TextView txt_timer;
    private int timeInSecs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_by_type);

        Bundle bundle = getIntent().getExtras();
        temp = bundle.getInt("id");

        helper = new ExerHelper(this);

        try{
            Cursor cursor = getExerciseName(temp);
            showExerciseName(cursor);
        }
        finally {
            helper.close();
        }

        //timer
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_finish = (Button)findViewById(R.id.btn_finish);
        txt_timer = (TextView)findViewById(R.id.txt_timer);
        handler = new Handler();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t == 1){
                    btn_start.setText("พัก");
                    startTime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);
                    t = 0;
                }
                else{
                    btn_start.setText("เริ่ม");
                    timeSwapBuff += timeInMillisec;
                    handler.removeCallbacks(updateTimer);
                    t = 1;
                }
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseByTypeActivity.this, ExerGetAmountActivity.class);
                handler.removeCallbacks(updateTimer);
                intent.putExtra("name", name);
                intent.putExtra("position", temp);
                intent.putExtra("time", timeInSecs);
                startActivity(intent);
                finish();
            }
        });

        btn_howTo = (Button)findViewById(R.id.btn_howTo);
        btn_howTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp == 1){
                    Intent intent = new Intent(ExerciseByTypeActivity.this, ExerHowF01Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("position", temp);
                    startActivity(intent);
                }
                else if(temp == 2){
                    Intent intent = new Intent(ExerciseByTypeActivity.this, ExerHowF02Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("position", temp);
                    startActivity(intent);
                }
                else if(temp == 3){
                    Intent intent = new Intent(ExerciseByTypeActivity.this, ExerHowF03Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("position", temp);
                    startActivity(intent);
                }
                else if(temp == 4){
                    Intent intent = new Intent(ExerciseByTypeActivity.this, ExerHowF04Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("position", temp);
                    startActivity(intent);
                }
                else if(temp == 5){
                    Intent intent = new Intent(ExerciseByTypeActivity.this, ExerHowS01Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("position", temp);
                    startActivity(intent);
                }
                else if(temp == 6){
                    Intent intent = new Intent(ExerciseByTypeActivity.this, ExerHowS02Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("position", temp);
                    startActivity(intent);
                }
                else if(temp == 7){
                    Intent intent = new Intent(ExerciseByTypeActivity.this, ExerHowS03Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("position", temp);
                    startActivity(intent);
                }
                else if(temp == 8){
                    Intent intent = new Intent(ExerciseByTypeActivity.this, ExerHowS04Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("position", temp);
                    startActivity(intent);
                }
                else if(temp == 9){
                    Intent intent = new Intent(ExerciseByTypeActivity.this, ExerHowS05Activity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("position", temp);
                    startActivity(intent);
                }
            }
        });
    }

    private static String[] COLUMNS = new String[]{_ID, EXERCISE_NAME, CATAGORY};
    private Cursor getExerciseName(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, "_ID = " + id, null, null, null, null);
        return cursor;
    }

    private void showExerciseName(Cursor cursor){
        StringBuilder builder = new StringBuilder("");
        while (cursor.moveToNext()){
            name = cursor.getString(1);
            builder.append(name);
        }
        txt_exerName = (TextView)findViewById(R.id.txt_exerName);
        txt_exerName.setText(builder);
    }

    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            timeInMillisec = SystemClock.uptimeMillis() - startTime;
            timeUpdate = timeSwapBuff + timeInMillisec;
            sec = (int)(timeUpdate / 1000);
            timeInSecs = sec;
            min = sec / 60;
            sec = sec % 60;
            hour = min / 60;
            min = min % 60;
            txt_timer.setText("" + String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec));
            handler.postDelayed(this, 0);
        }
    };

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
            Intent homeIntent = new Intent(ExerciseByTypeActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(ExerciseByTypeActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
