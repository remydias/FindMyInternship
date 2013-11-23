package com.example.findmyinternship;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

import Model.Company;
import Model.Student;
import Model.StudentBDD;
import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisteringActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
        final EditText TFLN = (EditText)findViewById(R.id.LN_edit);
        
        
        final EditText TFFN = (EditText)findViewById(R.id.FN_edit);
        
        
        
        final EditText TFMAIL = (EditText)findViewById(R.id.Mail_Edit);
        
        final EditText TFPWD = (EditText)findViewById(R.id.Pwd_Edit);
        
        final EditText TFCONF = (EditText)findViewById(R.id.TFCONFPWD);
        
        final EditText TFSCHOOL = (EditText)findViewById(R.id.TextField_unvisible);
		
        final RadioButton CB1 = (RadioButton)findViewById(R.id.checkBoxStudent);
        final RadioButton CB2 = (RadioButton)findViewById(R.id.checkBoxCompany);
        
        final TextView TV1 = (TextView)findViewById(R.id.textView_unvisible);
        final EditText TF1 = (EditText)findViewById(R.id.TextField_unvisible);
        
        Button buttonVal = (Button)findViewById(R.id.buttonValidRegister);
        Button buttonCanc = (Button)findViewById(R.id.buttonCancelRegister);
        
        TV1.setVisibility(View.INVISIBLE);
        TF1.setVisibility(View.INVISIBLE);
        
        CB2.setOnClickListener(new OnClickListener() {
			
      	  @Override
      	  public void onClick(View v) {	
      		  CB1.setChecked(false);
      		  TV1.setVisibility(View.VISIBLE);
      		  TV1.setText("Ecole :");
      		  TF1.setVisibility(View.VISIBLE);
      		}
      	});
        
        CB1.setOnClickListener(new OnClickListener() {
			
        	  @Override
        	  public void onClick(View v) {
        		  CB2.setChecked(false);
        		  	TV1.setVisibility(View.VISIBLE);
        		  	TV1.setText("Entreprise :");
        		   TF1.setVisibility(View.VISIBLE);
        		}
        	});
        
        buttonVal.setOnClickListener(new OnClickListener() {
			
        	  @Override
        	  public void onClick(View v) {	
        		  
        		  if(TFLN.getText().toString() == "" || TFFN.getText().toString() == "" || checkEmail(TFMAIL.getText().toString()) == false || (CB1.isChecked()==false && CB2.isChecked()==false) || TF1.getText().toString().length()<1 || TFPWD.getText().toString()=="" || TFCONF.getText().toString()=="" || !TFPWD.getText().toString().equals(TFCONF.getText().toString()))
        		  {
        			  Toast.makeText(RegisteringActivity.this,"Vérifiez tous les champs", Toast.LENGTH_LONG).show();
        		  }
        		  else if(CB2.isChecked()==true)
        		  {

          	        Student stud = new Student(TFLN.getText().toString(), TFFN.getText().toString(), TFMAIL.getText().toString(), TFSCHOOL.getText().toString(),TFPWD.getText().toString());
          	        //On ouvre la base de données pour écrire dedans
          	        MainActivity.getStudBdd().open();
          	        
          	        //On insère l'étudiant que l'on vient de créer
          	        try {
						MainActivity.getStudBdd().insertStudent(stud);
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
          	        
          	        Toast.makeText(RegisteringActivity.this, stud.getLastName()+" ajouté", Toast.LENGTH_LONG).show();
          	        
          	        MainActivity.getStudBdd().close();
          	        
          	        Intent intent = new Intent(RegisteringActivity.this, MainActivity.class);
          	        
                	startActivity(intent);
        			  
        		  }
        		  else
        		  {
        			    Company comp = new Company(TFLN.getText().toString(), TFFN.getText().toString(), TFMAIL.getText().toString(), TFSCHOOL.getText().toString(),TFPWD.getText().toString());
            	        //On ouvre la base de données pour écrire dedans
            	        MainActivity.getCompBdd().open();
            	        
            	        //On insère l'entreprise que l'on vient de créer
            	        try {
							MainActivity.getCompBdd().insertCompany(comp);
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            	        
            	        Toast.makeText(RegisteringActivity.this, comp.getLastName()+" ajouté", Toast.LENGTH_LONG).show();
            	        
            	        MainActivity.getCompBdd().close();
            	        
            	        Intent intent = new Intent(RegisteringActivity.this, MainActivity.class);
            	        
            	        startActivity(intent);
        		  }
        		  
        		}
        	});
        

        buttonCanc.setOnClickListener(new OnClickListener() {
			
        	  @Override
        	  public void onClick(View v) {	
        		  if(CB1.isChecked()==true)
        		  {
        			  Intent intent = new Intent(RegisteringActivity.this, StudentActivity.class);
                	  startActivity(intent);
        		  }
        		  else
        		  {
        			  Intent intent = new Intent(RegisteringActivity.this, CompanyActivity.class);
                	  startActivity(intent);
        		  }
        		  
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
