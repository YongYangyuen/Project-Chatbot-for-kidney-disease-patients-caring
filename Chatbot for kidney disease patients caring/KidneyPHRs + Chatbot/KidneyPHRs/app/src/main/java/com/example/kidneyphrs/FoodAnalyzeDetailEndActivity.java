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
import static com.example.kidneyphrs.FoodConcludeContent.CALORY_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.FOOD_NAME_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.PHOSPHORUS_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.POTASSIUM_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.PROTEIN_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.SODIUM_CONCLUDE;

public class FoodAnalyzeDetailEndActivity extends AppCompatActivity {

    private TextView txt_foodName;
    private TextView txt_energy;
    private TextView txt_protein;
    private TextView txt_sodium;
    private TextView txt_potassium;
    private TextView txt_phosphorus;
    private TextView txt_amount;

    private Button btn_mainmenu;

    private String name;
    private Integer calory;
    private Double protein;
    private Double sodium;
    private Double potassium;
    private Double phosphorus;
    private Integer amount;

    private String Date;

    private FoodConcludeHelper conclude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_analyze_detail_end);

        // Button to Main
        btn_mainmenu = (Button) findViewById(R.id.btn_mainmenu);
        btn_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(FoodAnalyzeDetailEndActivity.this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

        });

        Bundle bundle = getIntent().getExtras();
        Date = bundle.getString("Date");

        conclude = new FoodConcludeHelper(FoodAnalyzeDetailEndActivity.this);

        int temp = bundle.getInt("id");

        conclude = new FoodConcludeHelper(this);

        try {
            Cursor cursor = getFood(temp);
            showFood(cursor);
        } finally {
            conclude.close();
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
            Intent homeIntent = new Intent(FoodAnalyzeDetailEndActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(FoodAnalyzeDetailEndActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private static String[] COLUMNS = new String[]{_ID, FOOD_NAME_CONCLUDE, CALORY_CONCLUDE, PROTEIN_CONCLUDE, SODIUM_CONCLUDE, POTASSIUM_CONCLUDE, PHOSPHORUS_CONCLUDE};
    private Cursor getFood(int id){
        SQLiteDatabase db = conclude.getReadableDatabase();
        Cursor cursor = db.query(FoodConcludeContent.TABLE_NAME_CONCLUDE, COLUMNS, "_ID = " + id, null, null, null, null);
        return cursor;
    }

    private void showFood(Cursor cursor) {
        StringBuilder FoodName = new StringBuilder("");
        StringBuilder Energy = new StringBuilder("");
        StringBuilder Protein = new StringBuilder("");
        StringBuilder Sodium = new StringBuilder("");
        StringBuilder Potassium = new StringBuilder("");
        StringBuilder Phosphorus = new StringBuilder("");
//        StringBuilder Amount = new StringBuilder("");
        while (cursor.moveToNext()) {
            name = cursor.getString(1);
            FoodName.append(name);
            calory = cursor.getInt(2);
            Energy.append(calory);
            protein = cursor.getDouble(3);
            Protein.append(protein);
            sodium = cursor.getDouble(4);
            Sodium.append(sodium);
            potassium = cursor.getDouble(5);
            Potassium.append(potassium);
            phosphorus = cursor.getDouble(6);
            Phosphorus.append(phosphorus);
//            amount = cursor.getInt(7);
//            Amount.append(amount);
        }

        txt_foodName = (TextView)findViewById(R.id.txt_foodName);
        txt_energy = (TextView)findViewById(R.id.txt_energy);
        txt_protein = (TextView)findViewById(R.id.txt_protein);
        txt_sodium = (TextView)findViewById(R.id.txt_sodium);
        txt_potassium = (TextView)findViewById(R.id.txt_potassium);
        txt_phosphorus = (TextView)findViewById(R.id.txt_phosphorus);
//        txt_amount = (TextView)findViewById(R.id.txt_amount);


        txt_foodName.setText(FoodName);
        txt_energy.setText(Integer.toString(calory));
        txt_protein.setText("" + String.format("%.2f", protein));
        txt_sodium.setText("" + String.format("%.2f", sodium));
        txt_potassium.setText("" + String.format("%.2f", potassium));
        txt_phosphorus.setText("" + String.format("%.2f", phosphorus));
//        txt_amount.setText(Integer.toString(amount));
    }
}
