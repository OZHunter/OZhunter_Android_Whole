package com.example.jieminghu.ozhunter;

/**
 * Created by jieminghu on 7/5/17.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;


public class Shipping extends AppCompatActivity {
    //
    EditText search_brand;
    EditText search_title;
    Button searchweb;

    String schBrand;
    String schTitle;

    String firstURL="https://www.chemistwarehouse.com.au/search?searchtext=";
    String secondURL="&searchmode=allwords";



    //

    private static final String TAG = "info";


    TextView textView;
    String page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shipping_main);

        textView = (TextView)findViewById(R.id.TextView33);

        //new MyAsyncTask().execute();

        //
        search_brand=(EditText)findViewById(R.id.search_brandET);
        search_title=(EditText)findViewById(R.id.search_titleET);

        searchweb=(Button)findViewById(R.id.searchBT);

        search_brand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                schBrand=search_brand.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        search_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                schTitle=search_title.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String middle="";
                String URL="";
                if(search_brand.getText()!=null&& !search_brand.getText().toString().equals("") ){
                    String[] brands=search_brand.getText().toString().split(" ");
                    if(search_title.getText()!=null&& !search_title.getText().toString().equals("")){
                        if(brands.length>1){
                            for(int i=0;i<brands.length;i++){
                                middle=middle+brands[i];
                                middle=middle+"%20";
                            }
                        }else {
                            middle=middle+brands[0]+"%20";
                        }
                        middle=middle+search_title.getText().toString();

                    }
                }else if(search_brand.getText()!=null&& !search_brand.getText().toString().equals("") ){
                    String[] brands=search_brand.getText().toString().split(" ");

                    if(brands.length>1){
                        for(int i=0;i<brands.length;i++){
                            middle=middle+brands[i];
                            middle=middle+"%20";
                        }
                    }else {
                        middle=middle+brands[0]+"%20";
                    }

                    middle=middle+search_title.getText().toString();
                }else if(search_title.getText()!=null&& !search_title.getText().toString().equals("")){
                    middle=middle+search_title.getText().toString();
                }else{
                    URL="https://www.chemistwarehouse.com.au/";
                }

                URL=firstURL+middle+secondURL;
//                Intent getWebPage=new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
//                startActivity(getWebPage);
//                Log.e(TAG,URL);
                new MyAsyncTask().execute(URL);
            }
        });



        //
    }


    private class MyAsyncTask extends AsyncTask<String,String,String> {

        protected String doInBackground(String... agrs) {

            try {

//                URL url = new URL("http://www.chemistwarehouse.com.au/search?searchtext=swisse%20grape&searchmode=allwords");
                URL url =new URL(agrs[0]);
                URLConnection con = url.openConnection();
                InputStream in = con.getInputStream();
                String encoding = con.getContentEncoding();
                encoding = encoding==null?"UTF-8":encoding;
                String body = IOUtils.toString(in,encoding);

                page = body;

                ArrayList<String> prices;
                ArrayList<String> names;
                prices=findPrice(page);
                names=findName(page);

                page="";




                if(prices!=null && names!=null){
                    for(int i=0;i<prices.size();i++){

                        page=page+"name:\t"+names.get(i)+"  \tprice:\t"+prices.get(i)+"\n\n";

                    }
                }
                if(page.equals("")){
                    page="Not found!";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        protected ArrayList<String> findName(String page){

            String name ="";
            ArrayList<String> names=new ArrayList<>();

            String[] page0;

            page0=page.split("<div class=\"product-name\">");

            if(page0.length!=1) {
                for (int i = 1; i < page0.length; i++) {
                    String temp="";
                    name="";
                    if(page0[i].contains("</div>")){
                        temp=page0[i].split("</div>")[0];
                    }

                    String[] tt = temp.split("<span class=\"searchHighlight\">");

                    ArrayList<String> n = new ArrayList<>(Arrays.asList(tt));
                    for(int j=0;j<tt.length;j++){

                        String[] t = tt[j].split("</span>");

                        for (int k=0;k<t.length;k++){
                            name=name+t[k];
                        }
                    }

                    names.add(name);
                }

            }



            if(names.size()!=0) {
                return names;
            }else{
                return null;
            }
        }


        protected ArrayList<String> findPrice(String page){

            String price="";
            ArrayList<String> prices=new ArrayList<>();

            String[] page0;

            page0=page.split("<div class=\"prices\">");

            if(page0.length!=1) {
                for (int i = 1; i < page0.length; i++) {

                    if(page0[i].contains("<span class=\"Price\">")){
                        price=page0[i].split("<span class=\"Price\">")[1];
                    }
                    if(price.contains("\n")){
                        price=price.split("\n")[0];
                    }

                    prices.add(price);
                }
            }



            if(prices.size()!=0){
                return prices;
            }else {
                return null;

            }
        }


        protected void onPostExecute(String result){

            textView.setText(page);
            search_brand.setText(search_brand.getText());
            search_title.setText(search_title.getText());

        }

    }


}
