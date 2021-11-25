package com.example.homework2;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.util.Locale;


public class MainActivity<themeNight> extends AppCompatActivity implements View.OnClickListener {




    private CalculationData mCalculationData = new CalculationData();
    private Numbers mCounters;

    private TextView mInputField;  // пользовательский элемент 1-го счетчика
    private TextView textCounter1;  // пользовательский элемент 2-го счетчика
    Button switchTheme; // Switch theme button
    Button themeNight;
    Button themeDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCounters = new Numbers();
        initView();



    }
//connect
    private void initView() {
        // Получить пользовательские элементы по идентификатору
        mInputField = findViewById(R.id.calcField);
        switchTheme = (Button) findViewById(R.id.switchTheme);
        themeNight = (RadioButton) findViewById(R.id.night);
        themeDay = (RadioButton) findViewById(R.id.day);
    }

/*    private void initButton2ClickListener() {
        Button radioBtn = findViewById(R.id.night);

        radioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(R.style.Base_V22_Theme_AppCompat_Light);
                setTextCounter(mInputField, mCounters.getCounter0());
            }
        });
    }*/



    private void setTextCounter(TextView textCounter, int counter) {
        textCounter.setText(String.format(Locale.getDefault(), "%d", counter));
    }

    public void processOnClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.digit_0:
            case R.id.digit_1:
            case R.id.digit_2:
            case R.id.digit_3:
            case R.id.digit_4:
            case R.id.digit_5:
            case R.id.digit_6:
            case R.id.digit_7:
            case R.id.digit_8:
            case R.id.digit_9:{
                if(mCalculationData.firstArg == null){
                    mCalculationData.firstArg= "";
                    mInputField.setText("");
                }
                if(mCalculationData.secondArg == null){
                    mCalculationData.firstArg += ((Button)v).getText();
                } else {
                    mCalculationData.secondArg += ((Button)v).getText();
                }
                mInputField.setText(mInputField.getText().toString() + ((Button)v).getText().toString());
                break;
            }
            case R.id.plus:
            case R.id.minus:
            case R.id.multiply:
            case R.id.divide:{
                mCalculationData.secondArg = "";
                mCalculationData.operation = ((Button)v).getText().toString();
                mInputField.setText(mInputField.getText().toString() + ((Button)v).getText().toString());
                break;
            }


            case R.id.equal: {
                final int result = calculate();
                mInputField.setText(""+ result);
                clearData();

                break;
            }
        }
    }

    private int calculate() {
        final int first = Integer.parseInt(mCalculationData.firstArg);
        final int second =  Integer.parseInt(mCalculationData.secondArg);
        switch (mCalculationData.operation){
            case "-":
                return first - second;
            case "+":
                return first + second;
            case "%":
                return first % second;
            case "*":
                return first * second;
            default:
                return -1;


        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ActivityThemes.class);
        startActivity(intent);
        Intent intent1 = new Intent(String.valueOf(themeDay));
        setTheme(R.style.Theme_AppCompat_DayNight);


/*        Intent intent1 = new Intent(this, ActivityThemes.class);
        setTheme(
        startActivity(intent1);*/
    }

    private class CalculationData{
        String firstArg;
        String secondArg;
        String operation;

    }

    private void clearData(){
        mCalculationData.firstArg = null;
        mCalculationData.secondArg = null;
        mCalculationData.operation = null;
    }
}

