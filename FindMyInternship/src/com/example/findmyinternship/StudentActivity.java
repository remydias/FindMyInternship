package com.example.findmyinternship;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.findmyinternship.R;
import com.example.findmyinternship.R.layout;

import Model.Student;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class StudentActivity extends Activity{
	
	private static final int CODE_DE_MON_ACTIVITE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student);
		
        Button buttonReg = (Button)findViewById(R.id.button_Register);
        Button Connect = (Button)findViewById(R.id.Connexion_button);
        
        final EditText TFMAIL = (EditText)findViewById(R.id.LN_edit);
        final EditText TFPWD = (EditText)findViewById(R.id.FN_edit);
        
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
        		//Intent intent = new Intent(StudentActivity.this, RegisteringActivity.class);
        		//startActivity(intent);
        		  //Ici on va test si les utilisateurs sont dans la Bdd
        		  
        		  //On ouvre la base de données pour écrire dedans
        	        MainActivity.getStudBdd().open();
        	        
        		  
        		  //Est-ce que l'adresse Mail existe
        		  if(MainActivity.getStudBdd().getStudentByMail(TFMAIL.getText().toString()) != null)
        		  {
        			  Student temp = (MainActivity.getStudBdd().getStudentByMail(TFMAIL.getText().toString()));
        			  
        			  MessageDigest messageDigest = null;
        			  try {
        				  messageDigest = MessageDigest.getInstance("SHA-256");
        			  } catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
        				  e.printStackTrace();
        			  }	
      					messageDigest.update(TFPWD.getText().toString().getBytes());
      					String encryptedString = new String(messageDigest.digest());
      				
        			  
        			  if(temp.getPassword().equals(encryptedString))
        			  {
        				  Intent intent = new Intent(StudentActivity.this, ConnectStudentActivity.class);
        		      	  startActivity(intent);
        			  }
        			  
        		  }
        		  else
        		  {
        			  Toast.makeText(StudentActivity.this,"Enregistrez vous !", Toast.LENGTH_LONG).show();
        		  }
        		  
        		//On ferme la base de données
      	        MainActivity.getStudBdd().close();
        		  
        		}
        	});
      

		
	}

}
