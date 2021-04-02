package com.example.kidneyphrs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ChatbotActivity extends AppCompatActivity {

    private ImageButton btn_facebook;
    private ImageButton btn_line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        btn_facebook = (ImageButton)findViewById(R.id.btn_facebook);
        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatbotActivity.this);
                builder.setTitle("Messenger")
                .setMessage("หลังจากกดปุ่มแล้ว คุณต้องการให้เราสอนการใช้งานเบื้องต้นหรือไม่")
                        .setNegativeButton("ใช่", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                startActivity(new Intent(ChatbotActivity.this, Pop.class));
                            }
                        })
                        .setPositiveButton("ไม่", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                Uri webpage = Uri.parse("http://pic.sopili.net/l/facebook/page/119538933183318");
                                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                                startActivity(webIntent);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btn_line = (ImageButton)findViewById(R.id.btn_line);
        btn_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatbotActivity.this);
                builder.setTitle("LINE")
                        .setMessage("หลังจากกดปุ่มแล้ว คุณต้องการให้เราสอนการใช้งานเบื้องต้นหรือไม่")
                        .setNegativeButton("ใช่", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                startActivity(new Intent(ChatbotActivity.this, Pop2.class));
                            }
                        })
                        .setPositiveButton("ไม่", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                Uri webpage = Uri.parse("http://line.me/ti/p/%40659vxzaf");
                                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                                startActivity(webIntent);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
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
            Intent homeIntent = new Intent(ChatbotActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        if (id == R.id.action_emergency) {
            Intent intent = new Intent(ChatbotActivity.this, EmergencyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*------------------------------------*/
}
