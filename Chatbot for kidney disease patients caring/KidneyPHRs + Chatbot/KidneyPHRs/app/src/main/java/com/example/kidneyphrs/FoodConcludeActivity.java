package com.example.kidneyphrs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodConcludeActivity extends AppCompatActivity {

    private TextView txt_energyConclude;
    private TextView txt_proteinConclude;
    private TextView txt_sodiumConclude;
    private TextView txt_potassiumConclude;
    private TextView txt_phosphorusConclude;

    private Button btn_mainmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_conclude);

        // Button to Main
        btn_mainmenu = (Button) findViewById(R.id.btn_mainmenu);
        btn_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(FoodConcludeActivity.this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

        });

        Bundle bundle = getIntent().getExtras();
        int energyConclude = bundle.getInt("calory");
        double proteinConclude = bundle.getDouble("protein");
        double sodiumConclude = bundle.getDouble("sodium");
        double potassiumConclude = bundle.getDouble("potassium");
        double phosphorusConclude = bundle.getDouble("phosphorus");


        txt_energyConclude = (TextView)findViewById(R.id.txt_energyConclude);
        txt_proteinConclude = (TextView)findViewById(R.id.txt_proteinConclude);
        txt_sodiumConclude = (TextView)findViewById(R.id.txt_sodiumConclude);
        txt_potassiumConclude = (TextView)findViewById(R.id.txt_potassiumConclude);
        txt_phosphorusConclude = (TextView)findViewById(R.id.txt_phosphorusConclude);


        txt_energyConclude.setText(Integer.toString(energyConclude));
        txt_proteinConclude.setText("" + String.format("%.2f", proteinConclude));
        txt_sodiumConclude.setText("" + String.format("%.2f", sodiumConclude));
        txt_potassiumConclude.setText("" + String.format("%.2f", potassiumConclude));
        txt_phosphorusConclude.setText("" + String.format("%.2f", phosphorusConclude));
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
            Intent homeIntent = new Intent(FoodConcludeActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(FoodConcludeActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
