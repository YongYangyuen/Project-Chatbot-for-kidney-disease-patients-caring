package com.example.kidneyphrs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExerResultActivity extends AppCompatActivity {

    private TextView txt_exerciseName;
    private TextView txt_time;
    private TextView txt_amount;
    private Button btn_mainmenu;

    private int amount;
    private int timeInSecs;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer_result);

        txt_exerciseName = (TextView)findViewById(R.id.txt_exerciseName);
        txt_time = (TextView)findViewById(R.id.txt_time);
        txt_amount = (TextView)findViewById(R.id.txt_amount);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        timeInSecs = bundle.getInt("time");
        amount = bundle.getInt("amount");

        txt_exerciseName.setText(name);
        txt_time.setText("" + String.format("%02d", timeInSecs/60/60) + ":" + String.format("%02d", (timeInSecs/60)%60) + ":" + String.format("%02d", timeInSecs % 60));
        txt_amount.setText("" + Integer.toString(amount));

        // Button to Main
        btn_mainmenu = (Button) findViewById(R.id.btn_mainmenu);
        btn_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ExerResultActivity.this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

        });
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
            Intent homeIntent = new Intent(ExerResultActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(ExerResultActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
