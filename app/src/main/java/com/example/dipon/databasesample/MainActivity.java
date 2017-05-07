package com.example.dipon.databasesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button insert ;
    DbHelper dbHelper;
    private static String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = (Button) findViewById(R.id.btn_insert);
        insert.setOnClickListener(this);
        dbHelper = new DbHelper(this) ;

    }

    @Override
    public void onClick(View v) {
       long temp = dbHelper.insert();
        Log.e(TAG, "onClick: " + temp);
    }

}
