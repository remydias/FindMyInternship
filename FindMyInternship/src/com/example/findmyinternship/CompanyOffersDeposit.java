package com.example.findmyinternship;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Offer;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyOffersDeposit extends Activity{
	
	String company_name;	
	Bundle b;

	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.company_offers_deposit);
		
		 b= getIntent().getExtras();
	     company_name=b.getString("name");
		
		 final Offer myOffer = new Offer();
		 
		 final EditText title = (EditText)findViewById(R.id.title_textfield_deposit);
		 final EditText desc = (EditText)findViewById(R.id.desc_textfield_deposit);
		 final EditText skills = (EditText)findViewById(R.id.skills_textfield_deposit);
		 final EditText profile = (EditText)findViewById(R.id.profile_textfield_deposit);
		 final EditText duration = (EditText)findViewById(R.id.duration_textfield_deposit);
		 final EditText salary = (EditText)findViewById(R.id.salary_textfield_deposit);
		 
		 myOffer.setCompany(company_name);
		 
		 
		 final Button val = (Button)findViewById(R.id.button_deposit);
		 
		 val.setOnClickListener(new OnClickListener() {
				
       	  @Override
       	  public void onClick(View v) {	
       		  
       		if(!(title.getText().toString().equals("")))
       		{
       			myOffer.setTitle(title.getText().toString());
       		}
	   		 else
	   		 {
	   			 myOffer.setTitle("Sans titre");
	   		 }
	   		 
	   		 if(!(desc.getText().toString()==""))
	   		 {
	   			 myOffer.setDescription(desc.getText().toString());
	   		 }
	   		 else
	   		 {
	   			 myOffer.setDescription("Sans description");
	   		 }
	   		 
	   		 if(!(skills.getText().toString()==""))
	   		 {
	   			 myOffer.setCompetences(skills.getText().toString());
	   		 }
	   		 else
	   		 {
	   			 myOffer.setCompetences("Sans compétences");
	   		 }
	   		 
	   		 if(!(profile.getText().toString()==""))
	   		 {
	   			 myOffer.setProfil(profile.getText().toString());
	   		 }
	   		 else
	   		 {
	   			 myOffer.setProfil("Sans profil");
	   		 }
	   		 
	   		 if(!(duration.getText().toString()==""))
	   		 {
	   			 myOffer.setDuration(duration.getText().toString());
	   		 }
	   		 else
	   		 {
	   			 myOffer.setDuration("Sans durée");
	   		 }
	   		 
	   		 if(!(salary.getText().toString()==""))
	   		 {
	   			 myOffer.setSalary(salary.getText().toString());
	   		 }
	   		 else
	   		 {
	   			 myOffer.setSalary("Sans salaire");
	   		 }
   		 
       		  
       		  
       		  
       		  MainActivity.getOffBdd().open();
       		  //On insère l'offre que l'on vient de créer
    	        try {
					MainActivity.getOffBdd().insertOffer(myOffer);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	        
    	        Toast.makeText(CompanyOffersDeposit.this, "Offre ajoutée", Toast.LENGTH_LONG).show();
      	        
      	        MainActivity.getOffBdd().close();
      	        
      	        finish();
      	        
      	        
      	      
       		}
       	  
       	  
       	});
       
        
    }
}
