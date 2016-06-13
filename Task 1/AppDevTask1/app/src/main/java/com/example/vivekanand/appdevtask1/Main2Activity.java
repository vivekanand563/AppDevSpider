package com.example.vivekanand.appdevtask1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.sql.Timestamp;


public class Main2Activity extends AppCompatActivity {
    TextView textview,textview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textview = (TextView)findViewById(R.id.textView2);
        Bundle data = getIntent().getExtras();
        String name = data.getString("name");
        StringBuilder s = new StringBuilder(100);
        s.append("Thank you ");
        s.append(name);
        s.append(" for your response");
        textview.setText(s);

        java.util.Date date= new java.util.Date();
        Timestamp timeStamp = new Timestamp(date.getTime());

        textview3 = (TextView) findViewById(R.id.textView3);
        textview3.setText(timeStamp.toString());
    }

    public void onClick(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
