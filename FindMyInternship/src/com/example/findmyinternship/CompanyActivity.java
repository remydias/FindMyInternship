package com.example.findmyinternship;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import com.example.findmyinternship.R;
import com.example.findmyinternship.R.layout;

import Model.Company;
import Model.Student;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CompanyActivity extends Activity{
	

	private static final int CODE_DE_MON_ACTIVITE = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_company);
		

        Button buttonReg = (Button)findViewById(R.id.button_Register);
        
        Button Connect = (Button)findViewById(R.id.Connexion_button);
        
        final EditText TFMAIL = (EditText)findViewById(R.id.LN_edit);
        final EditText TFPWD = (EditText)findViewById(R.id.FN_edit);
        
        buttonReg.setOnClickListener(new OnClickListener() {
			
      	  @Override
      	  public void onClick(View v) {
      		Intent intent = new Intent(CompanyActivity.this, RegisteringActivity.class);
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
          	        MainActivity.getCompBdd().open();
          	        
          	    //L'adresse Mail est correcte ?
        	        if(checkEmail(TFMAIL.getText().toString()))
        	        {
        	        	//Est-ce que l'adresse Mail existe
                		  if(MainActivity.getCompBdd().getCompanyByMail(TFMAIL.getText().toString()) != null)
                		  {
                			Company temp = (MainActivity.getCompBdd().getCompanyByMail(TFMAIL.getText().toString()));
                			if(temp.getSchool()==null)
                				Toast.makeText(CompanyActivity.this,"YOPPPPPPP", Toast.LENGTH_LONG).show();
                			else
                			{
                			  
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
                				  Intent intent = new Intent(CompanyActivity.this, ConnectCompanyActivity.class);
                				  Bundle extras = new Bundle();
                				  extras.putString("name", temp.getSchool());
                				  extras.putString("cperson", temp.getLastName()+" "+temp.getFirstName());
                				  extras.putString("desc", temp.getDescription());
                				  intent.putExtras(extras);

                				  startActivity(intent);
                			  }
                			}
                			  
                		  }
                		  else
                		  {
                			  Toast.makeText(CompanyActivity.this,"Enregistrez vous !", Toast.LENGTH_LONG).show();
                		  }
        	        }
        	        else
        	        {
        	        	Toast.makeText(CompanyActivity.this,"Adresse Mail non valide !", Toast.LENGTH_LONG).show();
        	        }
          	        
          		  
          		  
          		  
          		//On ferme la base de données
        	        MainActivity.getCompBdd().close();
          		  
        		}
        	});
	}
        
        public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
  	          "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
  	          "\\@" +
  	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
  	          "(" +
  	          "\\." +
  	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
  	          ")+"
  	      );
  	
  	private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
  }

}
