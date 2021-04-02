package com.example.kidneyphrs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.FoodConcludeContent.CALORY_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.PHOSPHORUS_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.POTASSIUM_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.PROTEIN_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.SODIUM_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.TABLE_NAME_CONCLUDE;
import static com.example.kidneyphrs.PersonalInfoContent.WEIGHT;

public class FoodAnalyzeDetailActivity extends AppCompatActivity {

    private FoodConcludeHelper helper;

    private PersonalInfoHelper personal_helper;

    private TextView txt_energyAnalyze;
    private TextView txt_proteinAnalyze;
    private TextView txt_sodiumAnalyze;
    private TextView txt_potassiumAnalyze;
    private TextView txt_phosphorusAnalyze;
    private Button btn_moreDetail;

    private Integer calory;
    private Double protein;
    private Double sodium;
    private Double potassium;
    private Double phosphorus;
    private String Date;

    private static Integer weight;

    private Double caloryperkg;
    private Double proteinperkg;
    private Double sodiumperday;
    private Double potassiumperkg;
    private Double phosphorusperkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_analyze_detail);

        Bundle bundle = getIntent().getExtras();
        Date = bundle.getString("Date");

        txt_energyAnalyze = (TextView)findViewById(R.id.txt_energyAnalyze);
        txt_proteinAnalyze = (TextView)findViewById(R.id.txt_proteinAnalyze);
        txt_sodiumAnalyze = (TextView)findViewById(R.id.txt_sodiumAnalyze);
        txt_potassiumAnalyze = (TextView)findViewById(R.id.txt_potassiumAnalyze);
        txt_phosphorusAnalyze = (TextView)findViewById(R.id.txt_phosphorusAnalyze);

        btn_moreDetail = (Button)findViewById(R.id.btn_moreDetail);
        btn_moreDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodAnalyzeDetailActivity.this, FoodAnalyzeMoredetailActivity.class);
                intent.putExtra("Date", Date);
                startActivity(intent);
            }
        });

        helper = new FoodConcludeHelper(this);

        try {
            Cursor cursor = getFood();
            showFood(cursor);
        } finally {
            helper.close();
        }

        personal_helper = new PersonalInfoHelper(this);

        try {
            Cursor cursor = getPersonal();
            showPersonal(cursor);
        } finally {
            helper.close();
        }


        // prepare calory
        txt_energyAnalyze = (TextView)findViewById(R.id.txt_energyAnalyze);
        caloryperkg = (calory * 1.0) / (weight * 1.0);

//        txt_temp.setText("" + caloryperkg);

        if (caloryperkg > 35) {
            txt_energyAnalyze.setTextColor(Color.parseColor("#CC3333"));
        }
        else if (caloryperkg < 30) {
            txt_energyAnalyze.setTextColor(Color.parseColor("#FFA500"));
        }
        else {
            txt_energyAnalyze.setTextColor(Color.parseColor("#33CC00"));
        }

        // prepare protein
        txt_proteinAnalyze = (TextView)findViewById(R.id.txt_proteinAnalyze);
        proteinperkg = protein /  weight;

        if (proteinperkg > 1.4) {
            txt_proteinAnalyze.setTextColor(Color.parseColor("#CC3333"));
        }
        else if (caloryperkg < 1.1) {
            txt_proteinAnalyze.setTextColor(Color.parseColor("#FFA500"));
        }
        else {
            txt_proteinAnalyze.setTextColor(Color.parseColor("#33CC00"));
        }

        // prepare sodium
        txt_sodiumAnalyze = (TextView)findViewById(R.id.txt_sodiumAnalyze);
        sodiumperday = sodium / 1000;

        if (sodiumperday > 2) {
            txt_sodiumAnalyze.setTextColor(Color.parseColor("#CC3333"));
        }
        else if (sodiumperday < 3) {
            txt_sodiumAnalyze.setTextColor(Color.parseColor("#FFA500"));
        }
        else {
            txt_sodiumAnalyze.setTextColor(Color.parseColor("#33CC00"));
        }

        // prepare potassium
        txt_potassiumAnalyze = (TextView)findViewById(R.id.txt_potassiumAnalyze);
        potassiumperkg = potassium /  weight;

        if (potassiumperkg > 90) {
            txt_potassiumAnalyze.setTextColor(Color.parseColor("#CC3333"));
        }
        else if (potassiumperkg < 70) {
            txt_potassiumAnalyze.setTextColor(Color.parseColor("#FFA500"));
        }
        else {
            txt_potassiumAnalyze.setTextColor(Color.parseColor("#33CC00"));
        }

        // prepare phosphorus
        txt_phosphorusAnalyze = (TextView)findViewById(R.id.txt_phosphorusAnalyze);
        phosphorusperkg = phosphorus /  weight;

        if (phosphorusperkg > 17) {
            txt_phosphorusAnalyze.setTextColor(Color.parseColor("#CC3333"));
        }
        else if (phosphorusperkg < 17 ) {
            txt_phosphorusAnalyze.setTextColor(Color.parseColor("#FFA500"));
        }
        else {
            txt_phosphorusAnalyze.setTextColor(Color.parseColor("#33CC00"));
        }
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
            Intent homeIntent = new Intent(FoodAnalyzeDetailActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(FoodAnalyzeDetailActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private static String[] COLUMNS = new String[]{_ID, CALORY_CONCLUDE, PROTEIN_CONCLUDE, SODIUM_CONCLUDE, POTASSIUM_CONCLUDE, PHOSPHORUS_CONCLUDE};
    private Cursor getFood(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT _id, SUM(Energy) AS Energy, SUM(Protein) AS Protein, SUM(Sodium) AS Sodium, SUM(Potassium) AS Potassium, SUM(Phosphorus) AS Phosphorus FROM " + TABLE_NAME_CONCLUDE + " WHERE DATE(Date)= '" + Date + "' GROUP BY DATE(" + TABLE_NAME_CONCLUDE + ".Date)";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    private void showFood(Cursor cursor){
        StringBuilder Energy = new StringBuilder("");
        StringBuilder Protein = new StringBuilder("");
        StringBuilder Sodium = new StringBuilder("");
        StringBuilder Potassium = new StringBuilder("");
        StringBuilder Phosphorus = new StringBuilder("");
        while (cursor.moveToNext()){
            calory = cursor.getInt(1);
            Energy.append(calory);
            protein= cursor.getDouble(2);
            Protein.append("" + String.format("%.2f", protein));
            sodium = cursor.getDouble(3);
            Sodium.append("" + String.format("%.2f", sodium));
            potassium = cursor.getDouble(4);
            Potassium.append("" + String.format("%.2f", potassium));
            phosphorus = cursor.getDouble(5);
            Phosphorus.append("" +  String.format("%.2f", phosphorus));
        }

        txt_energyAnalyze = (TextView)findViewById(R.id.txt_energyAnalyze);
        txt_proteinAnalyze = (TextView)findViewById(R.id.txt_proteinAnalyze);
        txt_sodiumAnalyze = (TextView)findViewById(R.id.txt_sodiumAnalyze);
        txt_potassiumAnalyze = (TextView)findViewById(R.id.txt_potassiumAnalyze);
        txt_phosphorusAnalyze = (TextView)findViewById(R.id.txt_phosphorusAnalyze);

        txt_energyAnalyze.setText(Energy);
        txt_proteinAnalyze.setText(Protein);
        txt_sodiumAnalyze.setText(Sodium);
        txt_potassiumAnalyze.setText(Potassium);
        txt_phosphorusAnalyze.setText(Phosphorus);
    }

    private static String[] COLUMNS2 = new String[]{_ID, WEIGHT };
    private Cursor getPersonal(){
        SQLiteDatabase db = personal_helper.getReadableDatabase();
        Cursor cursor = db.query(PersonalInfoContent.TABLE_NAME_INFOR, COLUMNS2, null, null, null, null, null);
        return cursor;
    }

    private void showPersonal(Cursor cursor) {
        while (cursor.moveToNext()) {
            weight = cursor.getInt(1);

        }
    }
}
