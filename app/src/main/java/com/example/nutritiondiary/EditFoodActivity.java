package com.example.nutritiondiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditFoodActivity extends AppCompatActivity {

    String Childid,Parentid;
    String name,unit;
    double cal,carbs,fat,pro,fib,qua,su,weig;

    Toolbar toolbar;

    TextView tname,tcal,tcarbs,tfat,tpro,tfib,tunit,tsu,twei;

    EditText tqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        toolbar = (Toolbar)findViewById(R.id.tool);
        setSupportActionBar(toolbar);


        tcal = (TextView)findViewById(R.id.caledt);
        tcarbs = (TextView)findViewById(R.id.carbsedt);
        tpro = (TextView)findViewById(R.id.proedt);
        tfat = (TextView)findViewById(R.id.fatedt);
        tfib = (TextView)findViewById(R.id.fibedt);
        tunit = (TextView)findViewById(R.id.unitedt);
        tsu = (TextView)findViewById(R.id.suedt);
        tname = (TextView)findViewById(R.id.foodnameedt);
        twei = (TextView)findViewById(R.id.weightedt);

        tqua = (EditText)findViewById(R.id.quaedt);

        Bundle extras = getIntent().getExtras();
        if(extras != null){

            Childid = extras.getString("childid");
            Parentid = extras.getString("parentid");
            name = extras.getString("name");

            cal = extras.getDouble("calories");
            carbs = extras.getDouble("carbs");
            fat = extras.getDouble("fat");
            pro = extras.getDouble("protein");
            fib = extras.getDouble("fiber");
            qua = extras.getDouble("quantity");
            su = extras.getDouble("suger");
            unit = extras.getString("unit");
            weig = extras.getDouble("weight");

        }

        tname.setText(name);
        tcal.setText(String.valueOf(cal));
        tfat.setText(String.valueOf(fat));
        tcarbs.setText(String.valueOf(carbs));
        tpro.setText(String.valueOf(pro));
        tfib.setText(String.valueOf(fib));
        tqua.setText(String.valueOf(qua));
        tsu.setText(String.valueOf(su));
        tunit.setText(unit);
        twei.setText(String.valueOf(weig));


    }
}
