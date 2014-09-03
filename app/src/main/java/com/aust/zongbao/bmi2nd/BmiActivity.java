package com.aust.zongbao.bmi2nd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.textservice.SuggestionsInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import android.content.DialogInterface;
import android.widget.Toast;


public class BmiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        find_view();
        setlistener();
    }

    //define and find elment by id  that could save some time
    private EditText field_height;
    private EditText field_weight;
    private Button button_submit;
    private TextView result;
    private TextView suggestion;


    public void find_view()
    {
        //define and find elment by id  that could save some time
        field_height = (EditText)findViewById(R.id.height);
        field_weight = (EditText)findViewById(R.id.weight);
        button_submit = (Button)findViewById(R.id.btn_submit);
        result = (TextView)findViewById(R.id.result);
        suggestion =(TextView)findViewById(R.id.suggestion);

    }
    private void setlistener()
    {
        button_submit.setOnClickListener(calcBMI);
    }

    private OnClickListener calcBMI = new OnClickListener() {
        @Override
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
           try{
               double height = Double.parseDouble(field_height.getText().toString())/100;
               double weight = Double.parseDouble(field_weight.getText().toString());

               double BMI = weight / (height * height);

               result.setText(getText(R.string.your_bmi) + nf.format(BMI));
               if(BMI<18.5){
                   suggestion.setText(R.string.suggestion_light);
               }else if(BMI>=18.5 & BMI<24){
                   suggestion.setText(R.string.suggestion_good);
               }else if(BMI>=24 & BMI <27)
               {
                   suggestion.setText(R.string.suggestion_heavy1);
               }else if(BMI>=27 & BMI<30){
                   suggestion.setText(R.string.suggestion_heavy2);
               }else if(BMI>=30 & BMI<35){
                   suggestion.setText(R.string.suggestion_heavy3);
               }else{
                   suggestion.setText(R.string.suggestion_heavy4);
               }

//               openAboutDialog();
           } catch(Exception obj) {
               Toast.makeText(BmiActivity.this,R.string.error_input_tips,Toast.LENGTH_SHORT).show();
           }
        }
    };


    private void openAboutDialog()
    {
        new AlertDialog.Builder(BmiActivity.this)
                .setTitle(R.string.about_title)
                .setMessage(R.string.about_content)
                .setPositiveButton(R.string.about_button_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialogInterface, int i) {
                            }
                        }
                )
                .setNegativeButton(R.string.label_homepage,
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialogInterface, int i) {
                                // go to url
                                Uri uri = Uri.parse("http://www.baidu.com");
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        }
                )
                .show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bmi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            finish();
            return true;
        }else if(id == R.id.action_about){
            openAboutDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
