package com.example.ben.iss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public
class MainActivity extends AppCompatActivity {

    EditText myEditText;
    Button myButton;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myEditText = findViewById(R.id.tipValue);
         myButton = findViewById(R.id.getValueButton);
    }
}
