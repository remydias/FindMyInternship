package com.example.findmyinternship;

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
        
        
        final EditText TFFN = (EditText)findViewById(R.id.FN_editt);
        
        
        
        final EditText TFMAIL = (EditText)findViewById(R.id.Mail_Edit);
        
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
      		 //TV1.setVisibility(View.INVISIBLE);
             //TF1.setVisibility(View.INVISIBLE);
      		  TV1.setVisibility(View.VISIBLE);
      		  TV1.setText("Ecole :");
      		  TF1.setVisibility(View.VISIBLE);
      		}
      	});
        
        CB1.setOnClickListener(new OnClickListener() {
			
        	  @Override
        	  public void onClick(View v) {
        		  CB2.setChecked(false);
        		  //TV1.setVisibility(View.INVISIBLE);
        	        //TF1.setVisibility(View.INVISIBLE);
        		  	TV1.setVisibility(View.VISIBLE);
        		  	TV1.setText("Entreprise :");
        		   TF1.setVisibility(View.VISIBLE);
        		}
        	});
        
        buttonVal.setOnClickListener(new OnClickListener() {
			
        	  @Override
        	  public void onClick(View v) {	
        		  
        	        Student stud = new Student(TFLN.getText().toString(), TFFN.getText().toString(), TFMAIL.getText().toString(), TFSCHOOL.getText().toString());
        	        //On ouvre la base de données pour écrire dedans
        	        MainActivity.getStudBdd().open();
        	        
        	        //On insère l'étudiant que l'on vient de créer
        	        MainActivity.getStudBdd().insertStudent(stud);
        	        
        	        Toast.makeText(RegisteringActivity.this, stud.getLastName()+" ajouté", Toast.LENGTH_LONG).show();
        	        
        	        MainActivity.getStudBdd().close();
        	        
        	        Intent intent = new Intent(RegisteringActivity.this, MainActivity.class);
        	        
              		startActivity(intent);
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

}
