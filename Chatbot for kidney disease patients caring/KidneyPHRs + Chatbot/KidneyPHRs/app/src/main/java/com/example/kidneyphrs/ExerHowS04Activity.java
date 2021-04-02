package com.example.kidneyphrs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExerHowS04Activity extends AppCompatActivity {

    private TextView txt_exerName;
    private Button btn_ok;

    private int position;
    private int backTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer_how_s04);

        txt_exerName = (TextView)findViewById(R.id.txt_exerName);
        Bundle bundle = getIntent().getExtras();
        String temp = bundle.getString("name");
        position = bundle.getInt("position");
        backTo = bundle.getInt("backTo");
        txt_exerName.setText(temp);

        btn_ok = (Button)findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerHowS04Activity.this, ExerciseByTypeActivity.class);
                intent.putExtra("id", position);
                intent.putExtra("backTo", backTo);
                startActivity(intent);
                finish();
            }
        });

        PlayGifView pGif = (PlayGifView) findViewById(R.id.viewGif);
        pGif.setImageResource(R.drawable.s_04);

    }
}
