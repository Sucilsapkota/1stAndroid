package com.example.sucil.sqlitedbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewStudentList extends AppCompatActivity {
    ArrayList<Students> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_list);
         studentList = new ArrayList<Students>();
        DBHandler dbHandler = new DBHandler(getApplicationContext());
        studentList = dbHandler.read();

        ListView lview = (ListView) findViewById(R.id.listView);
        ArrayAdapter aadap = new ArrayAdapter(getApplicationContext(),R.layout.anotherlayout,R.id.name,studentList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = getLayoutInflater().inflate(R.layout.anotherlayout,null);
                TextView name = (TextView) v.findViewById(R.id.name);
                TextView address = (TextView) v.findViewById(R.id.address);
                Students s = studentList.get(position);
                name.setText(s.getName());
                address.setText(s.getAddress());
                return v;
            }
        };
        lview.setAdapter(aadap);
    }

}
