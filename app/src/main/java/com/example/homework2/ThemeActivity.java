package com.example.homework2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ThemeActivity  extends AppCompatActivity {

    private boolean nightTheme;
    private boolean dayTheme;

    public ThemeActivity() {
        nightTheme =true;
        dayTheme = false;
    }


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_switch_themes);
        }
    }
