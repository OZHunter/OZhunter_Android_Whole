package com.example.jieminghu.ozhunter;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * Created by jieminghu on 7/5/17.
 */

public class ContactMessage extends AppCompatActivity {
    Button send;
    Button cancel;
    TextView showsender;
    EditText content;
    EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_message);
        send=(Button)findViewById(R.id.button_send);
        cancel=(Button)findViewById(R.id.button_cancel);

        showsender=(TextView)findViewById(R.id.TextView_emailaddress);
        title=(EditText) findViewById(R.id.editText_emailcontent);
        content=(EditText)findViewById(R.id.editText_sender);


        final String add=getIntent().getExtras().getString("email").toString();
        showsender.setText(add);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailintent=new Intent(Intent.ACTION_SEND);
                emailintent.setType("message/rfc822");
                //emailintent.putExtra(Intent.EXTRA_EMAIL,new String[]{"bradkate2016@gmail.com"});
                emailintent.putExtra(Intent.EXTRA_EMAIL,new String[]{add});
                emailintent.putExtra(Intent.EXTRA_SUBJECT, title.getText().toString());
                emailintent.putExtra(Intent.EXTRA_TEXT, content.getText().toString());
                emailintent.setType("text/plain");
                startActivity(emailintent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(ContactMessage.this, MainActivity.class);
                startActivity(back);
            }
        });

    }
}
