package com.example.jieminghu.ozhunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class function extends AppCompatActivity {
    TextView calculator;
    TextView agent;
    TextView shipping;
    TextView discount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function);
        calculator = (TextView) findViewById(R.id.calculator);
        agent = (TextView) findViewById(R.id.agent);
        shipping = (TextView) findViewById(R.id.shipping);
        discount = (TextView) findViewById(R.id.discount);
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(function.this,MainActivity.class);
                startActivity(i);
            }
        });

        discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(function.this,MainActivity2.class);
                startActivity(i);
            }
        });

        shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(function.this,SearchTrackingNumber.class);
                startActivity(i);
            }
        });

        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(function.this,Shipping.class);
                startActivity(i);
            }
        });
    }

}