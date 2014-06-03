package com.example.clientftp2.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;

import ClientFTP.ParamFTP;
import ClientFTP.ThreadClientFTP;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);


        this.Anonyme();
        this.doConnexion();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Anonyme()
    {
        ((CheckBox)findViewById(R.id.anonyme)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( ( (CheckBox) findViewById(R.id.anonyme)).isChecked() )
                {
                    ((EditText) findViewById(R.id.login)).setEnabled(false);
                    ((EditText) findViewById(R.id.pwd)).setEnabled(false);
                    ((CheckBox) findViewById(R.id.anonyme)).setChecked(true);
                }
                else
                {
                    ((EditText) findViewById(R.id.login)).setEnabled(true);
                    ((EditText) findViewById(R.id.pwd)).setEnabled(true);
                    ((CheckBox) findViewById(R.id.anonyme)).setChecked(false);
                }
            }
        });
    }

    public void doConnexion()
    {
        ((Button)findViewById(R.id.button1)).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((TextView) findViewById(R.id.host)).getText();
                try {
                    ParamFTP paramFTP = new ParamFTP(((TextView) findViewById(R.id.host)).getText().toString(), ((TextView) findViewById(R.id.login)).getText().toString(), ((TextView) findViewById(R.id.pwd)).getText().toString());
                    ThreadClientFTP tcFTP = ThreadClientFTP.getThread(paramFTP);
                    Log.d("wtf","wtf");
                    tcFTP.execute("uhikhu");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Connexion", e.getMessage());
                    Toast.makeText(getApplicationContext(),"Connexion impossible verifiez les paramétres",Toast.LENGTH_SHORT);

                }

            }
        });

    }
}
