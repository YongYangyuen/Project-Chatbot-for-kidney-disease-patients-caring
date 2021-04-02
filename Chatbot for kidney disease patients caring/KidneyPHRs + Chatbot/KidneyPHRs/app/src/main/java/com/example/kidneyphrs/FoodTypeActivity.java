package com.example.kidneyphrs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.provider.BaseColumns._ID;
import static com.example.kidneyphrs.FoodConcludeContent.AMOUNT;
import static com.example.kidneyphrs.FoodConcludeContent.CALORY_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.FOOD_NAME_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.MEAL;
import static com.example.kidneyphrs.FoodConcludeContent.PHOSPHORUS_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.POTASSIUM_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.PROTEIN_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.SODIUM_CONCLUDE;
import static com.example.kidneyphrs.FoodConcludeContent.TABLE_NAME_CONCLUDE;
import static com.example.kidneyphrs.FoodContent.CALORY;
import static com.example.kidneyphrs.FoodContent.FOOD_NAME;
import static com.example.kidneyphrs.FoodContent.PHOSPHORUS;
import static com.example.kidneyphrs.FoodContent.POTASSIUM;
import static com.example.kidneyphrs.FoodContent.PROTEIN;
import static com.example.kidneyphrs.FoodContent.SODIUM;

public class FoodTypeActivity extends AppCompatActivity {

    private String[] foodUnit = {"จาน", "ถ้วย", "แก้ว"};
    private Spinner foodUnit_spinner;

    private TextView txt_foodName;
    private TextView txt_energy;
    private TextView txt_protein;
    private TextView txt_sodium;
    private TextView txt_potassium;
    private TextView txt_phosphorus;
    private EditText txtf_foodAmount;

    private FoodHelper helper;
    private FoodConcludeHelper conclude;

    private String name;
    private Integer calory;
    private Double protein;
    private Double sodium;
    private Double potassium;
    private Double phosphorus;
    private Integer amount;
    private String meal = "";
    private String meal_server = "";

    private Button btn_morning;
    private Button btn_noon;
    private Button btn_evening;
    private Button btn_foodSave;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);

        txtf_foodAmount =(EditText)findViewById(R.id.txtf_foodAmount);
        conclude = new FoodConcludeHelper(FoodTypeActivity.this);

        /* -----------------------------FOOD SPINNER------------------------------- */
        foodUnit_spinner = (Spinner)findViewById(R.id.foodUnit_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, foodUnit);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        foodUnit_spinner.setAdapter(adapter);
        foodUnit_spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        int position = foodUnit_spinner.getSelectedItemPosition();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }
                });
        /* ------------------------------------------------------------------------- */

        Bundle bundle = getIntent().getExtras();
        int temp = bundle.getInt("id");

        helper = new FoodHelper(this);
        try {
            Cursor cursor = getFood(temp);
            showFood(cursor);
        } finally {
            helper.close();
        }

        addListenerOnButton();

        btn_foodSave = (Button) findViewById(R.id.btn_foodSave);
        btn_foodSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //save to DB
                amount = Integer.parseInt(txtf_foodAmount.getText().toString());
                addFoodConclude(name, calory, protein, sodium, potassium, phosphorus, amount, meal);

                //save to server
                try {
                    if (SaveDataToServer()){

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //go to next page with intent
                Intent intent = new Intent(FoodTypeActivity.this, FoodConcludeActivity.class);
                intent.putExtra("calory", calory * amount);
                intent.putExtra("protein", protein * amount);
                intent.putExtra("sodium", sodium * amount);
                intent.putExtra("potassium", potassium * amount);
                intent.putExtra("phosphorus", phosphorus * amount);

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
            Intent homeIntent = new Intent(FoodTypeActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(FoodTypeActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    private static String[] COLUMNS = new String[]{_ID, FOOD_NAME, CALORY, PROTEIN, SODIUM, POTASSIUM, PHOSPHORUS};
    private Cursor getFood(int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(FoodContent.TABLE_NAME, COLUMNS, "_ID = " + id, null, null, null, null);
        return cursor;
    }

    private void showFood(Cursor cursor){
        StringBuilder foodName = new StringBuilder("");
        StringBuilder Energy = new StringBuilder("");
        StringBuilder Protein = new StringBuilder("");
        StringBuilder Sodium = new StringBuilder("");
        StringBuilder Potassium = new StringBuilder("");
        StringBuilder Phosphorus = new StringBuilder("");
        while (cursor.moveToNext()){
            name = cursor.getString(1);
            foodName.append(name);
            calory = cursor.getInt(2);
            Energy.append(calory);
            protein= cursor.getDouble(3);
            Protein.append(protein);
            sodium = cursor.getDouble(4);
            Sodium.append(sodium);
            potassium = cursor.getDouble(5);
            Potassium.append(potassium);
            phosphorus = cursor.getDouble(6);
            Phosphorus.append(phosphorus);
        }

        txt_foodName = (TextView)findViewById(R.id.txt_foodName);
        txt_foodName.setText(foodName);
        txt_energy = (TextView)findViewById(R.id.txt_energy);
        txt_energy.setText(Energy);
        txt_protein = (TextView)findViewById(R.id.txt_protein);
        txt_protein.setText(Protein);
        txt_sodium = (TextView)findViewById(R.id.txt_sodium);
        txt_sodium.setText(Sodium);
        txt_potassium = (TextView)findViewById(R.id.txt_potassium);
        txt_potassium.setText(Potassium);
        txt_phosphorus = (TextView)findViewById(R.id.txt_phosphorus);
        txt_phosphorus.setText(Phosphorus);
    }

    public void addListenerOnButton() {
        btn_morning = (Button) findViewById(R.id.btn_morning);
        btn_morning.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn_morning.setBackgroundResource(R.drawable.btn_3);
                btn_morning.setTextColor(getResources().getColor(R.color.colorWhite));
                btn_noon.setBackgroundResource(R.drawable.btn_1);
                btn_noon.setTextColor(getResources().getColor(R.color.colorPlainText));
                btn_evening.setBackgroundResource(R.drawable.btn_1);
                btn_evening.setTextColor(getResources().getColor(R.color.colorPlainText));
                meal = "เช้า";
                meal_server = "breakfast";
            }
        });

        btn_noon = (Button) findViewById(R.id.btn_noon);
        btn_noon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn_noon.setBackgroundResource(R.drawable.btn_3);
                btn_noon.setTextColor(getResources().getColor(R.color.colorWhite));
                btn_morning.setBackgroundResource(R.drawable.btn_1);
                btn_morning.setTextColor(getResources().getColor(R.color.colorPlainText));
                btn_evening.setBackgroundResource(R.drawable.btn_1);
                btn_evening.setTextColor(getResources().getColor(R.color.colorPlainText));
                meal = "กลางวัน";
                meal_server = "lunch";
            }
        });

        btn_evening = (Button) findViewById(R.id.btn_evening);
        btn_evening.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn_evening.setBackgroundResource(R.drawable.btn_3);
                btn_evening.setTextColor(getResources().getColor(R.color.colorWhite));
                btn_noon.setBackgroundResource(R.drawable.btn_1);
                btn_noon.setTextColor(getResources().getColor(R.color.colorPlainText));
                btn_morning.setBackgroundResource(R.drawable.btn_1);
                btn_morning.setTextColor(getResources().getColor(R.color.colorPlainText));
                meal = "เย็น";
                meal_server = "dinner";
            }
        });
    }

    private void addFoodConclude(String namefood, int calory, Double protein, Double sodium, Double potassium, Double phosphorus, int amount, String meal){
        SQLiteDatabase db = conclude.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FOOD_NAME_CONCLUDE, namefood);
        values.put(CALORY_CONCLUDE, calory  * amount);
        values.put(PROTEIN_CONCLUDE, protein  * amount);
        values.put(SODIUM_CONCLUDE, sodium  * amount);
        values.put(POTASSIUM_CONCLUDE, potassium  * amount);
        values.put(PHOSPHORUS_CONCLUDE, phosphorus  * amount);
        values.put(AMOUNT, amount);
        values.put(MEAL, meal);
        db.insertOrThrow(TABLE_NAME_CONCLUDE, null, values);
    }

    public boolean SaveDataToServer() throws UnsupportedEncodingException, JSONException {

        // Dialog
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle("Error! ");
        ad.setIcon(android.R.drawable.btn_star_big_on);
        ad.setPositiveButton("Close", null);

//        String url = "http://192.168.1.40/androidPost/appServ.php";
        String url = "http://192.168.1.101:5000/nutrient/meal";
//        String userID = "10203057034983253"; // pop
        String userID = "10155270483560362";  // joii
        String appID = "PHRapp";

        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = new DefaultHttpClient();
        InputStream inputStream = null;
        HttpResponse httpResponse = null;
        String json = "";

        Calendar cal = Calendar.getInstance();
        System.out.println("Current time => " + cal.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(cal.getTime());

        JSONObject jsonObject = new JSONObject();
        JSONObject nutrient = new JSONObject();
        try {

            jsonObject.accumulate("date", formattedDate);

            nutrient.accumulate("sodium", sodium * amount * 0.001);
            nutrient.accumulate("potassium", potassium * amount * 0.001);
            nutrient.accumulate("calories", calory * amount);
            nutrient.accumulate("fat", 0);
            nutrient.accumulate("carbohydrate", 0);
            nutrient.accumulate("protein", protein * amount);
            nutrient.accumulate("phosphorus", phosphorus * amount * 0.001);

            jsonObject.accumulate("nutrients", nutrient);
            jsonObject.accumulate("userid", userID);
            jsonObject.accumulate("meal", meal_server);
            jsonObject.accumulate("appid", appID);

        }catch (Exception e){
            Log.d("Error json: ", e.toString());
        }

        json = jsonObject.toString();

        StringEntity se = new StringEntity(json);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        httpPost.setEntity(se);

        try {
            httpResponse = httpClient.execute(httpPost);
            Log.d("Send execute: ", json);
        }catch (Exception e){
            Log.d("Error execute: ", e.toString());
        }

        try {
            String responseText = EntityUtils.toString(httpResponse.getEntity());
//            inputStream = httpResponse.getEntity().getContent();
            if(responseText != null){
                JSONObject obj = new JSONObject(responseText);
                Log.d("responseText", obj.toString());
            }
        }catch (Exception e){
            Log.d("Error inputstream: ", e.toString());
        }

        return true;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        btn_foodSave = (Button) findViewById(R.id.btn_foodSave);
//        btn_foodSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //save to DB
//                amount = Integer.parseInt(txtf_foodAmount.getText().toString());
//                addFoodConclude(name, calory, protein, sodium, potassium, phosphorus, amount, meal);
//
//                //save to server
//                try {
//                    if (SaveDataToServer()){
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                //go to next page with intent
//                Intent intent = new Intent(FoodTypeActivity.this, FoodConcludeActivity.class);
//                intent.putExtra("calory", calory * amount);
//                intent.putExtra("protein", protein * amount);
//                intent.putExtra("sodium", sodium * amount);
//                intent.putExtra("potassium", potassium * amount);
//                intent.putExtra("phosphorus", phosphorus * amount);
//
//                startActivity(intent);
//
//            }
//        });
//    }
}
