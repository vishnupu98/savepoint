package com.example.disastermngmnt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private RadioGroup radioGroup;
    private EditText username;
    private SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        if(sharedpreferences.getString("Name","NOT").equals("NOT")) {
            setContentView(R.layout.activity_main2);
            setTitle("Create Profile");
            radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            username = findViewById(R.id.editTextTextPersonName);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton rb=radioGroup.findViewById(i);
                    if(rb.getText().toString().equalsIgnoreCase("RESCUER"))
                    {
                        username.setVisibility(View.INVISIBLE);
                    }
                    else
                        {
                        username.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        else
            moveToNext();
    }
    public void onSubmit(View v) {
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        SharedPreferences.Editor editor = sharedpreferences.edit();
        int type = rb.getText().toString().equalsIgnoreCase("Victim") ? 0 : 1;
        if(type==1) {
            editor.putString("Name", "RESCUER");
            editor.putInt("Type", type);
            editor.commit();
            moveToNext();
        }
        else {
            if ((username.getText().toString() == null) || (username.getText().toString().equals("")))
                Toast.makeText(this, "Enter a Valid Username", Toast.LENGTH_SHORT).show();
            else {
                editor.putString("Name", username.getText().toString());
                editor.putInt("Type", type);
                editor.commit();
                moveToNext();
            }
        }
    }
    public void moveToNext()
    {
        Intent i=new Intent(getApplicationContext(),MainActivity3.class);
        startActivity(i);
    }
}