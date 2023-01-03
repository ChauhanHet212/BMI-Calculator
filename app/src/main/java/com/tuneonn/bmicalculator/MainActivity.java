package com.tuneonn.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtheight, edtweight;
    Button calculatebtn, clearbtn;
    TextView txtvbmi, txtvbmiresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtheight = findViewById(R.id.edtheight);
        edtweight = findViewById(R.id.edtweight);
        calculatebtn = findViewById(R.id.calculatebtn);
        clearbtn = findViewById(R.id.clearbtn);
        txtvbmi = findViewById(R.id.txtvbmi);
        txtvbmiresult = findViewById(R.id.txtvbmiresult);

        clearbtn.setVisibility(View.INVISIBLE);
        txtvbmi.setVisibility(View.INVISIBLE);
        txtvbmiresult.setVisibility(View.INVISIBLE);

        calculatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtheight.getText().toString().isEmpty() && !edtweight.getText().toString().isEmpty()){
                    float height = Float.parseFloat(edtheight.getText().toString());
                    float weight = Float.parseFloat(edtweight.getText().toString());
                    if (height >= 50 && height <= 250 && weight > 0 && weight <= 200){

                        calculatebtn.setVisibility(View.INVISIBLE);
                        float bmi = bmical(height,weight);
                        txtvbmi.setVisibility(View.VISIBLE);
                        txtvbmi.setText("BMI = " + bmi);

                        if (bmi < 18.5){
                            txtvbmiresult.setVisibility(View.VISIBLE);
                            txtvbmiresult.setText("Under Weight");
                            txtvbmiresult.setBackgroundColor(getResources().getColor(R.color.yellow));
                            clearbtn.setVisibility(View.VISIBLE);
                        } else if (bmi >= 18.5 && bmi <= 24.9){
                            txtvbmiresult.setVisibility(View.VISIBLE);
                            txtvbmiresult.setText("Healthy");
                            txtvbmiresult.setBackgroundColor(getResources().getColor(R.color.green));
                            clearbtn.setVisibility(View.VISIBLE);
                        } else if (bmi >= 24.9 && bmi <= 30){
                            txtvbmiresult.setVisibility(View.VISIBLE);
                            txtvbmiresult.setText("Overweight");
                            txtvbmiresult.setBackgroundColor(getResources().getColor(R.color.orange));
                            clearbtn.setVisibility(View.VISIBLE);
                        } else {
                            txtvbmiresult.setVisibility(View.VISIBLE);
                            txtvbmiresult.setText("Suffering from Obesity");
                            txtvbmiresult.setBackgroundColor(getResources().getColor(R.color.red));
                            clearbtn.setVisibility(View.VISIBLE);
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Enter Proper Input", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Enter Values First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearbtn.setVisibility(View.INVISIBLE);
                calculatebtn.setVisibility(View.VISIBLE);
                txtvbmi.setVisibility(View.INVISIBLE);
                txtvbmiresult.setVisibility(View.INVISIBLE);
                edtweight.setText("");
                edtheight.setText("");
            }
        });
    }

    public float bmical(float height, float weight){
        float heightM = height / 100;
        float bmi = weight / (heightM * heightM);
        return bmi;
    }
}