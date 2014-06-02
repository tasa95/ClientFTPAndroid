package com.example.clientftpandroid.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FichierActivity extends Activity{
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.id.rl1);
	        Intent extActivityIntent = getIntent();
	        //TextView TestDisplay = (TextView) findViewById(R.id.rl1);
	        if (extActivityIntent != null) {
	        	//TestDisplay.setText();
	        }
	    }

}
