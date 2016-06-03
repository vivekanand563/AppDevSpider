package com.example.vivekanand.counter;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;




public class MainActivity extends AppCompatActivity {
    int c;
    Button viveksbutton;
    TextView vivekstext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viveksbutton = (Button) findViewById(R.id.viveksbutton);
        vivekstext = (TextView) findViewById(R.id.vivekstext);


        viveksbutton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        count();
                        vivekstext.setText(Integer.toString(c));

                    }
                }
        );

    }


    public void count() {
        c = c + 1;

    }
}
