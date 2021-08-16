package com.example.multiselectdropdown;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity { EditText e;
    Button b1,b2;
TextView textView;boolean[] select;
ArrayList<Integer> day=new ArrayList<>();
String[] dayArray={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e=(EditText)findViewById(R.id.editTextTextPersonName);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=e.getText().toString();
                if(value.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please Insert Data !!!",Toast.LENGTH_SHORT).show();
                }else {
                    ClipboardManager clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData=ClipData.newPlainText("Data",value);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(getApplicationContext(),"Copied To Clipboard",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text= e.getText().toString();
                if (text.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Already Empty !!!",Toast.LENGTH_SHORT).show();
                }else {
                    e.setText("");
                }
            }
        });
        textView=findViewById(R.id.tt);
        select=new boolean[dayArray.length];
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Day");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(dayArray, select, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            day.add(which);
                            Collections.sort(day);
                        }else {
                            day.remove(which);
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder=new StringBuilder();
                        for (int j=0;j<day.size();j++){
                            stringBuilder.append(dayArray[day.get(j)]);
                            if (j!=day.size()-1){
                                stringBuilder.append(" , ");
                            }
                        }
                        textView.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j=0;j<select.length;j++){
                            select[j]=false;
                            day.clear();
                            textView.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

    }
}