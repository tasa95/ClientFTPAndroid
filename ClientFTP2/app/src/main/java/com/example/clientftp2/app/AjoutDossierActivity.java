package com.example.clientftp2.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AjoutDossierActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_dossier_main);
        Intent extActivityIntent = getIntent();
        //TextView TestDisplay = (TextView) findViewById(R.id.rl1);
        if (extActivityIntent != null) {
        	//TestDisplay.setText();
        }
    }

}
