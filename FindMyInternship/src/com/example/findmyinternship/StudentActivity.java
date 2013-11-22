package com.example.findmyinternship;


import com.example.findmyinternship.R;
import com.example.findmyinternship.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class StudentActivity extends Activity{
	
	private static final int CODE_DE_MON_ACTIVITE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student);
		
        Button buttonReg = (Button)findViewById(R.id.button_Register);
        Button Connect = (Button)findViewById(R.id.Connexion_button);
        
        buttonReg.setOnClickListener(new OnClickListener() {
			
      	  @Override
      	  public void onClick(View v) {
      		Intent intent = new Intent(StudentActivity.this, RegisteringActivity.class);
      		startActivity(intent);
      		}
      	});
        
        Connect.setOnClickListener(new OnClickListener() {
			
        	  @Override
        	  public void onClick(View v) {
        		Intent intent = new Intent(StudentActivity.this, RegisteringActivity.class);
        		startActivity(intent);
        		}
        	});
      

		
	}

}
