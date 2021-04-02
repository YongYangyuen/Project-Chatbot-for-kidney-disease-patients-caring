package com.example.kidneyphrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class FoodActivity extends AppCompatActivity {

    private ImageButton btn_fastFood;
    private ImageButton btn_dishes;
    private ImageButton btn_dessert;
    private ImageButton btn_fruit;
    private ImageButton btn_drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        btn_fastFood = (ImageButton)findViewById(R.id.btn_fastFood);
        btn_fastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this, FoodClass1Activity.class);
                startActivity(intent);
            }
        });

        btn_dishes = (ImageButton)findViewById(R.id.btn_dishes);
        btn_dishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this, FoodClass2Activity.class);
                startActivity(intent);
            }
        });

        btn_dessert = (ImageButton)findViewById(R.id.btn_dessert);
        btn_dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this, FoodClass3Activity.class);
                startActivity(intent);
            }
        });

        btn_fruit = (ImageButton)findViewById(R.id.btn_fruit);
        btn_fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this, FoodClass4Activity.class);
                startActivity(intent);
            }
        });

        btn_drink = (ImageButton)findViewById(R.id.btn_drink);
        btn_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this, FoodClass5Activity.class);
                startActivity(intent);
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
            Intent homeIntent = new Intent(FoodActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(FoodActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
