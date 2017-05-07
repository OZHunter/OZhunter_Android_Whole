package com.example.jieminghu.ozhunter;

/**
 * Created by jieminghu on 7/5/17.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button visitourwebsite;
    ImageView websitepic;
    Button discountinformation;
    String addressforwebsite="http://www.ozhunter.com";
    ImageButton email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        visitourwebsite=(Button)findViewById(R.id.button2);
        websitepic=(ImageView)findViewById(R.id.imageView2);
        discountinformation=(Button)findViewById(R.id.button3);
        email=(ImageButton)findViewById(R.id.imageButton_email);

        visitourwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visitourwebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(addressforwebsite));
                startActivity(visitourwebsite);
            }
        });

        final Intent show=new Intent(this,discountpart.class);
        discountinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(show);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toemailpage=new Intent(MainActivity2.this,ChatWithPeople.class);
                startActivity(toemailpage);
            }
        });
    }

}
