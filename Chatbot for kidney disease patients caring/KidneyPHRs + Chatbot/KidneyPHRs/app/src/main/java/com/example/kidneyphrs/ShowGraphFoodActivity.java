package com.example.kidneyphrs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

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

public class ShowGraphFoodActivity extends AppCompatActivity {

    private Button btn_ok;
    private Spinner spinner_nutrient;
    private EditText txtf_amount;
    private ImageView img_recieve;

    private String[] nutrients = {"พลังงาน", "โปรตีน", "โซเดียม", "โพแทสเซียม", "ฟอสฟอรัส"};

    private String nutrient_select;
    private String amount;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_graph_food);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        spinner_nutrient = (Spinner)findViewById(R.id.spinner_nutrient);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, nutrients);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_nutrient.setAdapter(adapter);
        spinner_nutrient.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                        int position = spinner_nutrient.getSelectedItemPosition();

                        switch (position){
                            case 0:
                                nutrient_select = "calories";
                                break;
                            case 1:
                                nutrient_select = "protein";
                                break;
                            case 2:
                                nutrient_select = "sodium";
                                break;
                            case 3:
                                nutrient_select = "potassium";
                                break;
                            case 4:
                                nutrient_select = "phosphorus";
                                break;
                            default:
                                break;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {

                    }

                });

        txtf_amount = (EditText)findViewById(R.id.txtf_amount);
        amount = txtf_amount.getText().toString();


        btn_ok  =(Button)findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = txtf_amount.getText().toString();
                //save to server
                try {
                    if (GetDataFromServer()){

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        img_recieve = (ImageView)findViewById(R.id.img_recieve);
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
            Intent homeIntent = new Intent(ShowGraphFoodActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(ShowGraphFoodActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/

    public boolean GetDataFromServer() throws UnsupportedEncodingException, JSONException {

//        String url = "http://192.168.1.40/androidPost/appServ.php";
        String url = "http://192.168.1.101:5000/nutrient/chart/image";
//        String userID = "10203057034983253";  // pop
        String userID = "10155270483560362";    // joii
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
        try {
            jsonObject.accumulate("title", nutrient_select);
            jsonObject.accumulate("userid", userID );
            jsonObject.accumulate("appid", appID);
            jsonObject.accumulate("date", formattedDate);
            jsonObject.accumulate("amount", amount);
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
            Log.d("response : ", responseText);

            String image="";
            if(responseText != null){
                JSONObject obj = new JSONObject(responseText);
                image = obj.getString("image");
                Log.d("responseText", obj.toString());
            }
//            byte[] decodedString = Base64.decode(responseText, Base64.DEFAULT);
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            img_recieve.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, img_recieve.getWidth(), img_recieve.getHeight(), false));
            img_recieve.setImageBitmap(decodedByte);

//            inputStream = httpResponse.getEntity().getContent();

        }catch (Exception e){
            Log.d("Error inputstream: ", e.toString());
        }

        return true;
    }
}
