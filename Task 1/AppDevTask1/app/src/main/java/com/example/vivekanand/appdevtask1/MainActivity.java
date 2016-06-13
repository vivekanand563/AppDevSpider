package com.example.vivekanand.appdevtask1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText edittext;
    EditText editText2;
    CheckBox checkbox;
    CheckBox checkbox2;
    CheckBox checkbox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Dept_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        edittext = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);

        button = (Button)findViewById(R.id.button);
    }

    public void onClick(View v) {
        String str1, str2;
        checkbox = (CheckBox)findViewById(R.id.checkBox);
        checkbox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkbox3 = (CheckBox)findViewById(R.id.checkBox3);

        str1 = edittext.getText().toString();
        str2 = editText2.getText().toString();


        if((Objects.equals(str1, "Name") || Objects.equals(str1, ""))||(Objects.equals(str2, "Roll No") || Objects.equals(str2, ""))
                ||(!(checkbox.isChecked())&&!(checkbox2.isChecked())&&!(checkbox3.isChecked())))
        {
            if (Objects.equals(str1, "Name") || Objects.equals(str1, "") )
            {
                Toast.makeText(getApplicationContext(), "Name field is blank", Toast.LENGTH_SHORT).show();
            }

            if( Objects.equals(str2, "Roll No") || Objects.equals(str2, ""))
            {
                Toast.makeText(getApplicationContext(),"Roll No field is blank", Toast.LENGTH_SHORT).show();

            }
            if(!(checkbox.isChecked())&&!(checkbox2.isChecked())&&!(checkbox3.isChecked()))
            {
                Toast.makeText(getApplicationContext(),"checkbox is not chosen",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Intent i = new Intent(this, Main2Activity.class);
            i.putExtra("name",str1);
            startActivity(i);
        }
    }
}
