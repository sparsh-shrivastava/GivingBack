package com.givingback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class DevelopmentActivity extends AppCompatActivity {

    Button a,b,c,d,e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_development);
        a = (Button) findViewById(R.id.dev_login);
        b = (Button) findViewById(R.id.dev_signup);
        c = (Button) findViewById(R.id.dev_view_timeslots);
        d = (Button) findViewById(R.id.dev_create_timeslots);
        e = (Button) findViewById(R.id.dev_edit_preferences);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DevelopmentActivity.this,LoginActivity.class));
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DevelopmentActivity.this,SignUpActivity.class));
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DevelopmentActivity.this,CreateNewSlotActivity.class));
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DevelopmentActivity.this,ViewAllSlotsActivity.class));
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DevelopmentActivity.this,HomeActivity.class));
            }
        });



    }
}
