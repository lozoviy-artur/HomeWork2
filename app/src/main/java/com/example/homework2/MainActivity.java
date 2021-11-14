package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private CalculationData mCalculationData = new CalculationData();
    private Numbers mCounters;

    private TextView mInputField;  // пользовательский элемент 1-го счетчика
    private TextView textCounter1;  // пользовательский элемент 2-го счетчика



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCounters = new Numbers();
        initView();



    }

    private void initView() {
        // Получить пользовательские элементы по идентификатору
        mInputField = findViewById(R.id.calcField);
    }

    private void initButton2ClickListener() {
        Button button0 = findViewById(R.id.digit_0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounters.incrementCounter0();
                setTextCounter(mInputField, mCounters.getCounter0());
            }
        });
    }

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

