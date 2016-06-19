package com.example.sucil.sqlitedbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText address = (EditText) findViewById(R.id.address);
        Button store = (Button) findViewById(R.id.store);
        Button read = (Button) findViewById(R.id.button);
        final DBHandler dbHandler = new DBHandler(getApplicationContext());
        assert store != null;
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (address != null && name != null) {
                    Students s = new Students(name.getText().toString(),address.getText().toString());
                    dbHandler.insert(s);
                    Toast.makeText(getApplicationContext(),"inserted into Database",Toast.LENGTH_LONG).show();
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ViewStudentList.class);
                startActivity(i);
            }
        });
    }
}
