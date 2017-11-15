package com.zann1x.asciiboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void enable(View view) {
        startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
    }

    public void select(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.showInputMethodPicker();
        }
    }

}
