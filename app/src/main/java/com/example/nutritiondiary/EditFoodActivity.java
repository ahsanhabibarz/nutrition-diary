package com.example.nutritiondiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class EditFoodActivity extends AppCompatActivity {

    String Childid,Parentid;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        Bundle extras = getIntent().getExtras();
        if(extras != null){

            Childid = extras.getString("childid");
            Parentid = extras.getString("parentid");
            name = extras.getString("name");
        }


        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

    }
}
